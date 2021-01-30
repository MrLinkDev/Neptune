package ru.linkstuff.neptune.OpenGL;

import ru.linkstuff.neptune.Framework.Math.Vector;
import ru.linkstuff.neptune.OpenGL.Utils.SpriteAttributes;

/**
 * Отрисовщик спрайтов
 */
public class Artist {
    public final static int TYPE_COLOR = 0;
    public final static int TYPE_TEXTURE = 1;

    private float[] verticesBuffer;
    private Vertices vertices;

    private int bufferIndex;

    private int numSprites;
    public int maxSprites;

    public Artist(int maxSprites, int type){
        this.maxSprites = maxSprites;
        this.verticesBuffer = new float[maxSprites * 4 * (type == TYPE_COLOR ? 5 : 4)];
        vertices = new Vertices(maxSprites * 4, maxSprites * 6, type);

        short[] indices = new short[maxSprites * 6];
        int length = indices.length;

        short j = 0;
        for (int i = 0; i < length; i += 6, j += 4){
            indices[i] = j;
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = j;
        }
        vertices.setIndices(indices, 0, indices.length);
    }

    public Artist(int maxSprites){
        this.maxSprites = maxSprites;
        this.verticesBuffer = new float[maxSprites * 4 * 4];
        vertices = new Vertices(maxSprites * 4, maxSprites * 6, TYPE_TEXTURE);

        short[] indices = new short[maxSprites * 6];
        int length = indices.length;

        short j = 0;
        for (int i = 0; i < length; i += 6, j += 4){
            indices[i] = j;
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = j;
        }
        vertices.setIndices(indices, 0, indices.length);
    }

    public void begin(Texture texture){
        texture.bind();

        numSprites = 0;
        bufferIndex = 0;
    }

    public void begin(int slot){
        TextureManager.bindTexture(slot);

        numSprites = 0;
        bufferIndex = 0;
    }

    public void end(){
        if (numSprites > 0){
            vertices.setVertices(verticesBuffer, bufferIndex);
            vertices.bind();
            vertices.draw(numSprites);
        }
    }

    public void draw(float x, float y, float width, float height, Sprite sprite){
        float halfWidth = width / 2;
        float halfHeight = height / 2;

        float x1 = x - halfWidth;
        float y1 = y - halfHeight;
        float x2 = x + halfWidth;
        float y2 = y + halfHeight;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v1;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v1;

        numSprites++;
    }

    public void draw(SpriteAttributes attributes, Sprite sprite){
        float halfWidth = attributes.getWidth() / 2;
        float halfHeight = attributes.getHeight() / 2;

        float x1 = attributes.getX() - halfWidth;
        float y1 = attributes.getY() - halfHeight;
        float x2 = attributes.getX() + halfWidth;
        float y2 = attributes.getY() + halfHeight;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v1;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v1;

        numSprites++;
    }

    public void draw(float x, float y, float width, float height, float angle, Sprite sprite){
        float halfWidth = width / 2;
        float halfHeight = height / 2;

        float rad = angle * Vector.TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float x1 = -halfWidth * cos - (-halfHeight) * sin;
        float y1 = -halfWidth * sin + (-halfHeight) * cos;
        float x2 = halfWidth * cos - (-halfHeight) * sin;
        float y2 = halfWidth * sin + (-halfHeight) * cos;
        float x3 = halfWidth * cos - halfHeight * sin;
        float y3 = halfWidth * sin + halfHeight * cos;
        float x4 = -halfWidth * cos - halfHeight * sin;
        float y4 = -halfWidth * sin + halfHeight * cos;

        x1 += x;
        y1 += y;
        x2 += x;
        y2 += y;
        x3 += x;
        y3 += y;
        x4 += x;
        y4 += y;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x3;
        verticesBuffer[bufferIndex++] = y3;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v1;

        verticesBuffer[bufferIndex++] = x4;
        verticesBuffer[bufferIndex++] = y4;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v1;

        numSprites++;
    }

    public void draw(SpriteAttributes attributes, float angle, Sprite sprite){
        float halfWidth = attributes.getWidth() / 2;
        float halfHeight = attributes.getHeight() / 2;

        float rad = angle * Vector.TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float x1 = -halfWidth * cos - (-halfHeight) * sin;
        float y1 = -halfWidth * sin + (-halfHeight) * cos;
        float x2 = halfWidth * cos - (-halfHeight) * sin;
        float y2 = halfWidth * sin + (-halfHeight) * cos;
        float x3 = halfWidth * cos - halfHeight * sin;
        float y3 = halfWidth * sin + halfHeight * cos;
        float x4 = -halfWidth * cos - halfHeight * sin;
        float y4 = -halfWidth * sin + halfHeight * cos;

        x1 += attributes.getX();
        y1 += attributes.getY();
        x2 += attributes.getX();
        y2 += attributes.getY();
        x3 += attributes.getX();
        y3 += attributes.getY();
        x4 += attributes.getX();
        y4 += attributes.getY();

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v2;

        verticesBuffer[bufferIndex++] = x3;
        verticesBuffer[bufferIndex++] = y3;
        verticesBuffer[bufferIndex++] = sprite.u2;
        verticesBuffer[bufferIndex++] = sprite.v1;

        verticesBuffer[bufferIndex++] = x4;
        verticesBuffer[bufferIndex++] = y4;
        verticesBuffer[bufferIndex++] = sprite.u1;
        verticesBuffer[bufferIndex++] = sprite.v1;

        numSprites++;
    }
}
