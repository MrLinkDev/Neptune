package ru.linkstuff.neptune.OpenGL;

public class Sprite {
    protected final float u1, v1;
    protected final float u2, v2;

    public final int width, height;

    private final int textureSlot;

    public Sprite(Texture texture, float x, float y, int width, int height){
        this.u1 = x / texture.width;
        this.v1 = y / texture.height;
        this.u2 = this.u1 + (float) width / texture.width;
        this.v2 = this.v1 + (float) height / texture.height;

        this.width = width;
        this.height = height;

        textureSlot = texture.slot;
    }

    public int getTextureSlot() {
        return textureSlot;
    }
}
