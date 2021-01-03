package ru.linkstuff.neptune.OpenGL;

import android.content.Context;
import android.opengl.GLES20;

import ru.linkstuff.neptune.OpenGL.Utils.FileUtils;
import ru.linkstuff.neptune.R;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;

public class Shader {
    public final static int DEFAULT_VERTEX_COLOR_SHADER = R.raw.vertex_color_shader;
    public final static int DEFAULT_FRAGMENT_COLOR_SHADER = R.raw.fragment_color_shader;

    public final static int DEFAULT_VERTEX_TEXTURE_SHADER = R.raw.vertex_texture_shader;
    public final static int DEFAULT_FRAGMENT_TEXTURE_SHADER = R.raw.fragment_texture_shader;

    public final static int SHADER_TYPE_COLOR = 0;
    public final static int SHADER_TYPE_TEXTURE = 1;

    private int vertexShaderId;
    private int fragmentShaderId;

    public Shader(Context context, int shaderType) {
        switch (shaderType){
            case SHADER_TYPE_COLOR:
                vertexShaderId = createShader(context, GL_VERTEX_SHADER, DEFAULT_VERTEX_COLOR_SHADER);
                fragmentShaderId = createShader(context, GL_FRAGMENT_SHADER, DEFAULT_FRAGMENT_COLOR_SHADER);
                break;
            case SHADER_TYPE_TEXTURE:
                vertexShaderId = createShader(context, GL_VERTEX_SHADER, DEFAULT_VERTEX_TEXTURE_SHADER);
                fragmentShaderId = createShader(context, GL_FRAGMENT_SHADER, DEFAULT_FRAGMENT_TEXTURE_SHADER);
                break;
        }
    }

    public Shader(Context context, int vertexShader, int fragmentShader) {
        vertexShaderId = createShader(context, GL_VERTEX_SHADER, vertexShader);
        fragmentShaderId = createShader(context, GL_FRAGMENT_SHADER, fragmentShader);
    }

    private int createShader(Context context, int type, int shaderRawId) {
        String shaderSource = FileUtils.readTextFromRaw(context, shaderRawId);

        final int shaderId = GLES20.glCreateShader(type);
        if (shaderId == 0) return 0;

        GLES20.glShaderSource(shaderId, shaderSource);
        GLES20.glCompileShader(shaderId);

        final int[] compileStatus = new int[1];

        GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        if (compileStatus[0] == 0){
            GLES20.glDeleteShader(shaderId);
            return 0;
        }

        return shaderId;
    }

    public int getVertexShaderId() {
        return vertexShaderId;
    }

    public int getFragmentShaderId() {
        return fragmentShaderId;
    }
}
