package ru.linkstuff.neptune.Framework.Android.Handlers;

import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

import ru.linkstuff.neptune.Framework.Android.Pool;
import ru.linkstuff.neptune.Framework.Input;

public class KeyboardHandler implements View.OnKeyListener {
    private final int CHAR_LENGTH = 1280;
    //TODO: Добавить поддержку других языков
    boolean[] pressedKeys = new boolean[CHAR_LENGTH];
    Pool<Input.KeyEvent> keyEventPool;
    ArrayList<Input.KeyEvent> keyEventsBuffer = new ArrayList<>();
    ArrayList<Input.KeyEvent> keyEvents = new ArrayList<>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<Input.KeyEvent> factory = new Pool.PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };

        keyEventPool = new Pool<Input.KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //if (event.getAction() == KeyEvent.ACTION_MULTIPLE) return false;

        synchronized (this){
            Input.KeyEvent keyEvent = keyEventPool.newObject();

            try{
                keyEvent.keyCode = (int) event.getCharacters().charAt(0);
                keyEvent.keyChar = event.getCharacters().charAt(0);
            } catch (NullPointerException n){
                keyEvent.keyCode = keyCode;
                keyEvent.keyChar = (char) event.getUnicodeChar();
            }

            if (event.getAction() == KeyEvent.ACTION_DOWN){
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if (keyCode > 0 && keyCode < CHAR_LENGTH - 1) pressedKeys[keyCode] = true;
            }
            if (event.getAction() == KeyEvent.ACTION_UP){
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if (keyCode > 0 && keyCode < CHAR_LENGTH - 1) pressedKeys[keyCode] = false;
            }
            keyEventsBuffer.add(keyEvent);
        }

        return false;
    }

    public boolean isKeyPressed(int keyCode){
        if (keyCode < 0 || keyCode > CHAR_LENGTH - 1) return false;
        return pressedKeys[keyCode];
    }

    public ArrayList<Input.KeyEvent> getKeyEvents(){
        synchronized (this) {
            int length = keyEvents.size();
            for (int i = 0; i < length; ++i) keyEventPool.free(keyEvents.get(i));
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }

    }
}
