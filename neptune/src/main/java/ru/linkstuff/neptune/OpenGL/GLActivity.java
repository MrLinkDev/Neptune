package ru.linkstuff.neptune.OpenGL;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.linkstuff.neptune.Framework.Android.Audio;
import ru.linkstuff.neptune.Framework.Android.FileIO;
import ru.linkstuff.neptune.Framework.Android.Input;
import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Scene;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;

public abstract class GLActivity extends Activity implements Game, GLSurfaceView.Renderer{
    enum GLGameState{
        Initialized,
        Running,
        Paused,
        Resumed,
        Finished,
        Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;

    Audio audio;
    Input input;
    FileIO fileIO;
    Scene scene;

    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();

    long startTime = System.nanoTime();

    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        glView = new GLSurfaceView(this);
        glView.setEGLContextClientVersion(2);
        glView.setPreserveEGLContextOnPause(true);
        glView.setContentDescription(null);
        glView.setRenderer(this);
        glView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);

        setContentView(glView);

        glGraphics = new GLGraphics(glView);

        fileIO = new FileIO(getAssets());
        audio = new Audio(this);
        input = new Input(this, glView, 1, 1);

        PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Neptune:wakeLock");
    }

    @Override
    protected void onResume() {
        synchronized (stateChanged){
            if (state == GLGameState.Paused) {
                state = GLGameState.Resumed;
                stateChanged.notifyAll();
            }

        }

        glView.onResume();
        wakeLock.acquire(10 * 60 * 1000L);

        super.onResume();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        ProgramManager.create(getApplicationContext());
        ProgramManager.defaultTextureProgram().use();

        TextureManager.loadDebugTextures(this);

        synchronized (stateChanged){
            if (state == GLGameState.Initialized) {
                scene = getStartScene();
                state = GLGameState.Running;

                if (scene != null) scene.resume();

                startTime = System.nanoTime();
            }
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        GLGameState state = null;

        synchronized (stateChanged){
            state = this.state;
        }

        if (state == GLGameState.Resumed){
            synchronized (stateChanged){
                this.state = GLGameState.Running;
                stateChanged.notifyAll();

                scene.resume();
            }
        }

        if (state == GLGameState.Running){
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            scene.update(deltaTime);
            scene.present(deltaTime);
        }

        if (state == GLGameState.Paused){
            scene.pause();

            /**synchronized (stateChanged){
             this.state = GLGameState.Idle;
             stateChanged.notifyAll();
             }*/
        }

        if (state == GLGameState.Finished){
            scene.pause();
            scene.dispose();

            synchronized (stateChanged){
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
    }

    @Override
    public void onPause() {
        synchronized (stateChanged){
            if (isFinishing()) state = GLGameState.Finished;
            else state = GLGameState.Paused;

            /**while (true){
             try {
             stateChanged.wait();
             break;
             } catch (InterruptedException e) {
             e.printStackTrace();
             }
             }*/
        }

        wakeLock.release();
        glView.onPause();

        super.onPause();
    }

    public GLGraphics getGlGraphics(){
        return glGraphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public void setScene(Scene scene) {
        if (scene == null) throw new IllegalArgumentException("SCENE MUST BE NOT NULL!");
        this.scene.pause();
        this.scene.dispose();
        scene.resume();
        scene.update(0);
        this.scene = scene;
    }

    public Scene getCurrentScene(){
        return scene;
    }
}
