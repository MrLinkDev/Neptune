package ru.linkstuff.neptune.UI.Utils;

public class Metric {
    public static float unit = 0;

    /**
     * Адаптирует размер юнита под размер экрана
     * @param uWidth    Минимальное количество юнитов по ширине
     * @param uHeight   Минимальное количество юнитов по высоте
     * @param sWidth    Ширина экрана
     * @param sHeight   Высота экрана
     */
    public static void setUnitSize(float uWidth, float uHeight, int sWidth, int sHeight){
        if ((uWidth > uHeight && sWidth > sHeight) || (uWidth < uHeight && sWidth < sHeight)){
            if ((sWidth / uWidth) * uHeight > sHeight) unit = sHeight / uHeight;
            else unit =  sWidth / uWidth;
        } else {
            //TODO: Что делать в другом случае
        }
    }
}
