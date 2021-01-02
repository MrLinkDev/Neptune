package ru.linkstuff.neptune.Framework.Android;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import ru.linkstuff.neptune.Framework.Android.Handlers.*;

public class Input implements ru.linkstuff.neptune.Framework.Input {
    AccelerometerHandler accelerometerHandler;
    KeyboardHandler keyboardHandler;
    TouchHandler touchHandler;

    public Input(Context context, View view, float scaleX, float scaleY){
        accelerometerHandler = new AccelerometerHandler(context);
        keyboardHandler = new KeyboardHandler(view);
        touchHandler = new MultiTouchHandler(view, scaleX, scaleY);

        //if (Integer.parseInt(Build.VERSION.SDK) < 5) touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        //else touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public float getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public float getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelerometerHandler.getX();
    }

    @Override
    public float getAccelY() {
        return accelerometerHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelerometerHandler.getAccelZ();
    }

    @Override
    public ArrayList<KeyEvent> getKeyEvents() {
        return keyboardHandler.getKeyEvents();
    }

    @Override
    public ArrayList<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
