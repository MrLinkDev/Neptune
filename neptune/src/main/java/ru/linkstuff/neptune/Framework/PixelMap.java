package ru.linkstuff.neptune.Framework;

public interface PixelMap {
    public int getWidth();
    public int getHeight();

    public Graphics.PixelMapFormats getFormats();

    public void dispose();
}
