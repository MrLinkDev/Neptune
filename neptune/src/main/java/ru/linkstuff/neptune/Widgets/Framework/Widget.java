package ru.linkstuff.neptune.Widgets.Framework;

import ru.linkstuff.neptune.OpenGL.Texture;

public interface Widget {
    public void draw(Texture texture);

    public void setCoords(float x, float y);
    public void addCoords(float dX, float dY);

    public void setVisibility(boolean isVisible);
    public void setScale(float scale);

    public boolean isVisible();
}
