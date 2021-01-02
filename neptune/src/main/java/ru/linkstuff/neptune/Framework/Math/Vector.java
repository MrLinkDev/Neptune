package ru.linkstuff.neptune.Framework.Math;

public class Vector {
    public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180.0f;
    public float x, y;

    public Vector(){}

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector copy(){
        return new Vector(x, y);
    }

    public Vector set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector set(Vector vector){
        this.x = vector.x;
        this.y = vector.y;
        return this;
    }

    public Vector add(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector sub(float x, float y){
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector sub(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
        return this;
    }

    public Vector mul(float scalar){
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public float length(){
        return (float) Math.sqrt(x * x + y * y);
    }

    public float length(Vector vector2){
        return (float) Math.sqrt((x - vector2.x) * (x - vector2.x) + (y - vector2.y) * (y - vector2.y));
    }

    public Vector nor(){
        float length = length();
        if (length != 0){
            this.x /= length;
            this.y /= length;
        }

        return this;
    }

    public float angle(){
        float angle = (float) Math.atan2(y, x) * TO_DEGREES;
        if (angle < 0) angle += 360;
        return angle;
    }

    public Vector rotate(float angle){
        float rad = angle * TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin - this.y * cos;

        this.x = newX;
        this.y = newY;

        return this;
    }

    public float distance(Vector vector){
        float distX = this.x - vector.x;
        float distY = this.y - vector.y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }

    public float distance(float x, float y){
        float distX = this.x - x;
        float distY = this.y - y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }

    public float distanceSquared(float x, float y){
        float distX = this.x - x;
        float distY = this.y - y;
        return distX * distX + distY * distY;
    }

    public float distanceSquared(Vector vector){
        float distX = this.x - vector.x;
        float distY = this.y - vector.y;
        return distX * distX + distY * distY;
    }
}
