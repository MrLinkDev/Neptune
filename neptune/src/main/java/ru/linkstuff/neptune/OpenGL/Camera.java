package ru.linkstuff.neptune.OpenGL;

import android.opengl.Matrix;

import static android.opengl.GLES20.glUniformMatrix4fv;

public class Camera {
    private float[] mProjectionMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mMatrix = new float[16];

    // точка положения камеры
    private float eyeX;
    private float eyeY;
    private float eyeZ;

    // точка направления камеры
    private float centerX;
    private float centerY;
    private float centerZ;

    // up-вектор
    private float upX;
    private float upY;
    private float upZ;

    private int width, height;

    public Camera(int width, int height){
        this.width = width;
        this.height = height;

        createProjectionMatrix();
        createViewMatrix();
    }

    private void createProjectionMatrix(){
        float left = (float) -width / 2;
        float right = (float) width / 2;
        float bottom = (float) -height / 2;
        float top = (float) height / 2;
        float near = 1;
        float far = 5;

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    private void createViewMatrix() {
        eyeX = 0;
        eyeY = 0;
        eyeZ = 1;

        centerX = 0;
        centerY = 0;
        centerZ = 0;

        upX = 0;
        upY = 1;
        upZ = 0;

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public void setCamera() {
        Matrix.multiplyMM(mMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        glUniformMatrix4fv(ProgramManager.defaultProgram().getUMatrixLocation(), 1, false, mMatrix, 0);
    }

    public float getEyeX() {
        return eyeX;
    }

    public float getEyeY() {
        return eyeY;
    }

    public float getEyeZ() {
        return eyeZ;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getCenterZ() {
        return centerZ;
    }

    public float getUpX() {
        return upX;
    }

    public float getUpY() {
        return upY;
    }

    public float getUpZ() {
        return upZ;
    }
}
