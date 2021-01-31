package ru.linkstuff.neptune.UI.Widgets;

import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.Neptune;
import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.UI.TouchableWidget;
import ru.linkstuff.neptune.UI.Utils.UIAssets;

import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DOWN;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DRAGGED;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_UP;
import static ru.linkstuff.neptune.Neptune.BUTTON_CANCELED;
import static ru.linkstuff.neptune.Neptune.BUTTON_CLICKED;
import static ru.linkstuff.neptune.Neptune.BUTTON_NULL;
import static ru.linkstuff.neptune.Neptune.BUTTON_PRESSED;
import static ru.linkstuff.neptune.Neptune.STATE_DISABLED;
import static ru.linkstuff.neptune.Neptune.STATE_NORMAL;
import static ru.linkstuff.neptune.Neptune.STATE_PRESSED;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.disabledButtonSprites;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.normalButtonSprites;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.pressedButtonSprites;

public class Button extends TouchableWidget {
    private Artist artist;

    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);

        UIAssets.loadDebugButtonSprites();
        artist = new Artist(1);
    }

    @Override
    public int checkTouch(Input.TouchEvent event) {
        if (!touchable || event.pointer != 0) return BUTTON_NULL;

        switch (event.type){
            case TOUCH_DOWN:
                if (event.x >= x1 && event.y >= y1 && event.x <= x2 && event.y <= y2){
                    state = STATE_PRESSED;
                    //TODO: Добавить изменение координат для текста и изображений
                    return BUTTON_PRESSED;
                }
                break;

            case TOUCH_UP:
                if (state == STATE_PRESSED){
                    state = STATE_NORMAL;
                    //TODO: Добавить изменение координат для текста и изображений
                    return BUTTON_CLICKED;
                }
                break;

            case TOUCH_DRAGGED:
                if (state == STATE_PRESSED && !(event.x >= x1 && event.y >= y1 && event.x <= x2 && event.y <= y2)){
                    state = STATE_NORMAL;
                    //TODO: Добавить изменение координат для текста и изображений
                    return BUTTON_CANCELED;
                }
                break;
        }

        return BUTTON_NULL;
    }

    @Override
    public void draw() {
        if (!visible) return;

        switch (state){
            case STATE_NORMAL:
                artist.begin(normalButtonSprites.get(0).getTextureSlot());
                artist.draw(x, y, width, height, normalButtonSprites.get(0));
                artist.end();
                break;

            case STATE_PRESSED:
                artist.begin(pressedButtonSprites.get(0).getTextureSlot());
                artist.draw(x, y, width, height, pressedButtonSprites.get(0));
                artist.end();
                break;

            case STATE_DISABLED:
                artist.begin(disabledButtonSprites.get(0).getTextureSlot());
                artist.draw(x, y, width, height, disabledButtonSprites.get(0));
                artist.end();
                break;
        }
    }
}
