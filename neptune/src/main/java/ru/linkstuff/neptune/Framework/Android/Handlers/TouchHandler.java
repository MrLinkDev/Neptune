package ru.linkstuff.neptune.Framework.Android.Handlers;

import android.view.View;

import java.util.ArrayList;

import ru.linkstuff.neptune.Framework.Input;

public interface TouchHandler extends View.OnTouchListener{
    public boolean isTouchDown(int pointer);

    public float getTouchX(int pointer);
    public float getTouchY(int pointer);

    public ArrayList<Input.TouchEvent> getTouchEvents();
}
