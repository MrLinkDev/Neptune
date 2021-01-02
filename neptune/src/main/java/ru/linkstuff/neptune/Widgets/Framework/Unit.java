package ru.linkstuff.neptune.Widgets.Framework;

public class Unit {
    private static int PIXELS_PER_UNIT = 32;

    public static float unitSize;
    public static float ratio;

    public static void setUnitSize(float uWidth, float uHeight, int sWidth, int sHeight){
        float size = (float) sWidth / uWidth;

        if (size * uHeight > sHeight){
            size = (float) sHeight / uHeight;
        }

        unitSize = size;
        ratio = unitSize / PIXELS_PER_UNIT;
    }

    public static float toFloat(float units){
        return units * unitSize;
    }
}
