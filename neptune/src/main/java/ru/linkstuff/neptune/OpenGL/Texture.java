package ru.linkstuff.neptune.OpenGL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import ru.linkstuff.neptune.Framework.Android.FileIO;
import ru.linkstuff.neptune.OpenGL.GLActivity;

import static android.opengl.GLES20.GL_TEXTURE_2D;

public class Texture {
    public int id;
    public int slot;
    public String fileName;

    public Bitmap bitmap;

    public int width;
    public int height;

    public int minFilter;
    public int magFilter;

    public Texture(GLActivity glActivity, String fileName, int slot) {
        this.slot = slot;
        this.fileName = fileName;

        load(glActivity);
    }

    public void load(GLActivity glActivity){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        FileIO fileIO = glActivity.getFileIO();

        int[] ids = new int[1];

        GLES20.glGenTextures(1, ids, 0);
        id = ids[0];

        InputStream inputStream = null;
        try {
            inputStream = fileIO.readAsset(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);

            width = bitmap.getWidth();
            height = bitmap.getHeight();

            GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);

            GLES20.glActiveTexture(slot);
            GLES20.glBindTexture(GL_TEXTURE_2D, id);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            setFilters(GLES20.GL_NEAREST_MIPMAP_NEAREST, GLES20.GL_NEAREST);

            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GLES20.glGenerateMipmap(GL_TEXTURE_2D);
    }

    public void bind(){
        GLES20.glActiveTexture(slot);
        GLES20.glBindTexture(GL_TEXTURE_2D, id);
        GLES20.glUniform1i(ProgramManager.defaultTextureProgram().getUTextureUnitLocation(), slot - GLES20.GL_TEXTURE0);
    }

    public void reload(GLActivity glActivity){
        load(glActivity);
    }

    private void setFilters(int minFilter, int magFilter){
        this.minFilter = minFilter;
        this.magFilter = magFilter;

        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, minFilter);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, magFilter);
    }
}
