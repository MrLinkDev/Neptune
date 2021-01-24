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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
