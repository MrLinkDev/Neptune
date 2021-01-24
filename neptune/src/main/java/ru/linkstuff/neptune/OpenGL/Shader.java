package ru.linkstuff.neptune.OpenGL;

import android.content.Context;
import android.opengl.GLES20;

import ru.linkstuff.neptune.OpenGL.Utils.FileUtils;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static ru.linkstuff.neptune.Neptune.DEFAULT_FRAGMENT_COLOR_SHADER;
import static ru.linkstuff.neptune.Neptune.DEFAULT_FRAGMENT_TEXTURE_SHADER;
import static ru.linkstuff.neptune.Neptune.DEFAULT_VERTEX_COLOR_SHADER;
import static ru.linkstuff.neptune.Neptune.DEFAULT_VERTEX_TEXTURE_SHADER;
import static ru.linkstuff.neptune.Neptune.SHADER_TYPE_COLOR;
import static ru.linkstuff.neptune.Neptune.SHADER_TYPE_TEXTURE;

public class Shader {
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
