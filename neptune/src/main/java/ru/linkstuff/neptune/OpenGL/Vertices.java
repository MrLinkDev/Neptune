package ru.linkstuff.neptune.OpenGL;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

public class Vertices {
    public final static int TYPE_COLOR = 0;
    public final static int TYPE_TEXTURE = 1;

    private final int POSITION_COUNT = 2;
    private final int COLOR_COUNT = 3;
    private final int TEXTURE_COUNT = 2;

    private final int STRIDE;

    private IntBuffer vertices;
    private ShortBuffer indices;
    private int[] tempBuffer;

    private final int type;

    public Vertices(int maxVertices, int maxIndices, int type) {
        this.type = type;

        STRIDE = (POSITION_COUNT + (type == TYPE_COLOR ? COLOR_COUNT : TEXTURE_COUNT)) * 4;

        tempBuffer = new int[maxVertices * STRIDE / 4];
        vertices = ByteBuffer.allocateDirect(maxVertices * STRIDE).order(ByteOrder.nativeOrder()).asIntBuffer();

        if (maxIndices > 0){
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(maxIndices * Short.SIZE / 8);
            byteBuffer.order(ByteOrder.nativeOrder());
            indices = byteBuffer.asShortBuffer();
        } else {
            indices = null;
        }
    }

    public void setVertices(float[] vertices, int length){
        this.vertices.clear();

        for (int i = 0, j = 0; i < length; ++i, ++j) {
            tempBuffer[j] = Float.floatToRawIntBits(vertices[i]);
        }

        this.vertices.put(tempBuffer, 0, length);
        this.vertices.flip();
    }

    public void setIndices(short[] indices, int offset, int length){
        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }

    public void bind(){
        switch (type){
            case TYPE_COLOR:
                vertices.position(0);

                glVertexAttribPointer(ProgramManager.defaultColorProgram().getAPositionLocation(), POSITION_COUNT, GL_FLOAT, true, STRIDE, vertices);
                glEnableVertexAttribArray(ProgramManager.defaultColorProgram().getAPositionLocation());

                vertices.position(POSITION_COUNT);

                glVertexAttribPointer(ProgramManager.defaultColorProgram().getAColorLocation(), COLOR_COUNT, GL_FLOAT, true, STRIDE, vertices);
                glEnableVertexAttribArray(ProgramManager.defaultColorProgram().getAColorLocation());
                break;

            case TYPE_TEXTURE:
                vertices.position(0);

                glVertexAttribPointer(ProgramManager.defaultTextureProgram().getAPositionLocation(), POSITION_COUNT, GL_FLOAT, true, STRIDE, vertices);
                glEnableVertexAttribArray(ProgramManager.defaultTextureProgram().getAPositionLocation());

                vertices.position(POSITION_COUNT);

                glVertexAttribPointer(ProgramManager.defaultTextureProgram().getATextureLocation(), TEXTURE_COUNT, GL_FLOAT, true, STRIDE, vertices);
                glEnableVertexAttribArray(ProgramManager.defaultTextureProgram().getATextureLocation());
                break;
        }
    }

    public void draw(int numSprites){
        if (indices != null){
            indices.position(0);
            GLES20.glDrawElements(GL_TRIANGLES, numSprites * 6, GLES20.GL_UNSIGNED_SHORT, indices);
        } else glDrawArrays(GL_TRIANGLES, 0, numSprites * 6);
    }
}
