package ru.linkstuff.neptune.OpenGL;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import ru.linkstuff.neptune.Framework.Graphics;

public class GLGraphics {
    private final GLSurfaceView glView;
    private GLES20 gl;

    GLGraphics(GLSurfaceView glView){
        this.glView = glView;
    }

    public GLES20 getGl() {
        return gl;
    }

    public void setGl(GLES20 gl) {
        this.gl = gl;
    }

    public int getWidth(){
        return glView.getWidth();
    }

    public int getHeight(){
        return glView.getHeight();
    }
}
