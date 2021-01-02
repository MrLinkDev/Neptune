package ru.linkstuff.neptune.Widgets.Framework;

public class Symbol {
    public float x, y;
    public float width, height;

    public int id;

    public Symbol(){}

    public Symbol(float x, float y, float width, float height, int id){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.id = id;
    }
}
