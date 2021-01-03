package ru.linkstuff.neptune.OpenGL;

import android.content.Context;
import android.opengl.GLES20;

public class Program {
    private Shader shader;

    private int programId;

    public Program(Context context, int shaderType) {
        shader = new Shader(context, shaderType);

        createProgram();
    }

    public Program(Context context, int vertexShader, int fragmentShader) {
        shader = new Shader(context, vertexShader, fragmentShader);

        createProgram();
    }

    private void createProgram() {
        programId = GLES20.glCreateProgram();

        if (programId != 0) {
            attachShader();
        }
    }

    public void use() {
        GLES20.glUseProgram(programId);
    }

    private void attachShader() {
        GLES20.glAttachShader(programId, shader.getVertexShaderId());
        GLES20.glAttachShader(programId, shader.getFragmentShaderId());

        GLES20.glLinkProgram(programId);

        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) GLES20.glDeleteProgram(programId);
    }

    public int getAPositionLocation() {
        return GLES20.glGetAttribLocation(programId, "a_Position");
    }

    public int getAColorLocation() {
        return GLES20.glGetAttribLocation(programId, "a_Color");
    }

    public int getATextureLocation() {
        return GLES20.glGetAttribLocation(programId, "a_Texture");
    }

    public int getUTextureUnitLocation() {
        return GLES20.glGetUniformLocation(programId, "u_TextureUnit");
    }

    public int getUMatrixLocation() {
        return GLES20.glGetUniformLocation(programId, "u_Matrix");
    }

}
