package ru.linkstuff.neptune.UI;

import ru.linkstuff.neptune.Framework.Input;

import static ru.linkstuff.neptune.Neptune.STATE_DISABLED;
import static ru.linkstuff.neptune.Neptune.STATE_NORMAL;

public abstract class TouchableWidget extends Widget {
    protected float x1, y1, x2, y2;
    protected int state;

    protected boolean touchable;

    public TouchableWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        x1 = x - (width / 2);
        y1 = y - (height / 2);
        x2 = x + (width / 2);
        y2 = y + (height / 2);

        state = STATE_NORMAL;
        touchable = true;
    }

    public abstract int checkTouch(Input.TouchEvent event);

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public boolean isTouchable() {
        return touchable;
    }

    public void setTouchable(boolean touchable) {
        this.touchable = touchable;

        state = touchable ? STATE_NORMAL : STATE_DISABLED;
    }

    @Override
    public void addX(float dX) {
        super.addX(dX);

        x1 += dX;
        x2 += dX;
    }

    @Override
    public void addY(float dY) {
        super.addY(dY);

        y1 += dY;
        y2 += dY;
    }

    //TODO: Подумать, можно ли что-то оптимизировать в данном месте
    @Override
    public void addScale(float dS) {
        super.addScale(dS);

        x1 = x - (width / 2);
        y1 = y - (height / 2);
        x2 = x + (width / 2);
        y2 = y + (height / 2);
    }

    @Override
    public void setX(float x) {
        float dX = x - this.x;
        x1 += dX;
        x2 += dX;

        super.setX(x);
    }

    @Override
    public void setY(float y) {
        float dY = y - this.y;
        y1 += dY;
        y2 += dY;

        super.setY(y);
    }

    @Override
    public void setWidth(float width) {
        float dW = width - this.width;
        x1 -= dW;
        x2 += dW;

        super.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        float dH = height - this.height;
        y1 -= dH;
        y2 += dH;

        super.setHeight(height);
    }

    //TODO: Подумать, можно ли что-то оптимизировать в данном месте
    @Override
    public void setScale(float scale) {
        super.setScale(scale);

        x1 = x - (width / 2);
        y1 = y - (height / 2);
        x2 = x + (width / 2);
        y2 = y + (height / 2);
    }

    @Override
    public void setAngle(float angle) {
        super.setAngle(angle);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) touchable = false;
    }
}
