package ru.linkstuff.neptune.UI;

public abstract class Widget {
    protected float x, y;
    protected float width, height;

    protected float scale;
    protected float angle;

    protected boolean visible;

    public Widget(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        scale = 1.0f;
        angle = 0;

        visible = true;
    }

    public abstract void draw(float deltaTime);

    public void addX(float dX){
        x += dX;
    }

    public void addY(float dY){
        y += dY;
    }

    public void addScale(float dS){
        width += (width / scale) * dS;
        height += (height / scale) * dS;

        scale += dS;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
