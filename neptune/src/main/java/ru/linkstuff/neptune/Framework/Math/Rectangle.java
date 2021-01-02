package ru.linkstuff.neptune.Framework.Math;

public class Rectangle {
    public final Vector vector;
    public float width, height;

    public Rectangle(float x, float y, float width, float height){
        this.vector = new Vector(x, y);
        this.width = width;
        this.height = height;
    }
}
