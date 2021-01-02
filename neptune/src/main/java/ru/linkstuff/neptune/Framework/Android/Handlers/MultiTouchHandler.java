package ru.linkstuff.neptune.Framework.Android.Handlers;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import ru.linkstuff.neptune.Framework.Android.Pool;
import ru.linkstuff.neptune.Framework.Input;

public class MultiTouchHandler implements TouchHandler {
    boolean[] isTouched = new boolean[20];
    float[] touchX = new float[20];
    float[] touchY = new float[20];
    float scaleX;
    float scaleY;

    Pool<Input.TouchEvent> touchEventPool;
    ArrayList<Input.TouchEvent> touchEvents = new ArrayList<>();
    ArrayList<Input.TouchEvent> touchEventsBuffer = new ArrayList<>();

    public MultiTouchHandler(View view, float scaleX, float scaleY){
        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool = new Pool<>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if (pointer < 0 || pointer >= 20) return false;
            else return isTouched[pointer];
        }
    }

    @Override
    public float getTouchX(int pointer) {
        synchronized (this){
            if (pointer < 0 || pointer >= 20) return 0;
            else return touchX[pointer];
        }
    }

    @Override
    public float getTouchY(int pointer) {
        synchronized (this){
            if (pointer < 0 || pointer >= 20) return 0;
            else return touchY[pointer];
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
        synchronized (this){
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            Input.TouchEvent touchEvent;

            switch (action){
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = touchX[pointerId] = (event.getX(pointerIndex) * scaleX) - (float) v.getWidth() / 2;
                    touchEvent.y = touchY[pointerId] = (float) v.getHeight() / 2 - (event.getY(pointerIndex) * scaleY);
                    isTouched[pointerId] = true;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = touchX[pointerId] = (event.getX(pointerIndex) * scaleX) - (float) v.getWidth() / 2;
                    touchEvent.y = touchY[pointerId] = (float) v.getHeight() / 2 - (event.getY(pointerIndex) * scaleY);
                    isTouched[pointerId] = false;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for (int i = 0; i < pointerCount; i++){
                        pointerIndex = i;
                        pointerId = event.getPointerId(pointerIndex);
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[pointerId] = (event.getX(pointerIndex) * scaleX) - (float) v.getWidth() / 2;
                        touchEvent.y = touchY[pointerId] = (float) v.getHeight() / 2 - (event.getY(pointerIndex) * scaleY);
                        touchEventsBuffer.add(touchEvent);
                    }
                    break;
            }

            return true;
        }
    }
}
