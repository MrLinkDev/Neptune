package ru.linkstuff.neptune.OpenGL.Utils;

public class Color {
    public float r;
    public float g;
    public float b;

    public Color(float[] color){
        this.r = color[0];
        this.g = color[1];
        this.b = color[2];
    }

    public Color(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setColor(float[] color){
        this.r = color[0];
        this.g = color[1];
        this.b = color[2];
    }

    public void setColor(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
