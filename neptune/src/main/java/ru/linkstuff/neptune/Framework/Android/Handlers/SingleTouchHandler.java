package ru.linkstuff.neptune.Framework.Android.Handlers;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import ru.linkstuff.neptune.Framework.Android.Pool;
import ru.linkstuff.neptune.Framework.Input;

public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;
    float scaleX;
    float scaleY;

    Pool<Input.TouchEvent> touchEventPool;
    ArrayList<Input.TouchEvent> touchEvents = new ArrayList<>();
    ArrayList<Input.TouchEvent> touchEventsBuffer = new ArrayList<>();

    public SingleTouchHandler(View view, float scaleX, float scaleY){
        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };

        touchEventPool = new Pool<Input.TouchEvent>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if (pointer == 0) return isTouched;
            else return false;
        }
    }

    @Override
    public float getTouchX(int pointer) {
        synchronized (this) {
            return touchX;
        }
    }

    @Override
    public float getTouchY(int pointer) {
        synchronized (this) {
            return touchY;
        }
    }

    @Override
    public ArrayList<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int length = touchEvents.size();
            for (int i = 0; i < length; i++) touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            Input.TouchEvent touchEvent = touchEventPool.newObject();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }

            touchEvent.x = touchX = (int)(event.getX() * scaleX);
            touchEvent.y = touchY = (int)(event.getY() * scaleY);

            touchEventsBuffer.add(touchEvent);

            return true;
        }
    }
}
