package ru.linkstuff.neptune.OpenGL.Utils;

public class SpriteAttributes {
    private float x, y;
    private float width, height;

    public SpriteAttributes(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public SpriteAttributes(float x, float y, float size){
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
}
