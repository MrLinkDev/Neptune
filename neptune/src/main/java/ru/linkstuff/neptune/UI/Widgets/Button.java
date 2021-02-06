package ru.linkstuff.neptune.UI.Widgets;

import java.util.List;

import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Utils.SpriteAttributes;
import ru.linkstuff.neptune.UI.TouchableWidget;

import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DOWN;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DRAGGED;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_UP;
import static ru.linkstuff.neptune.Neptune.BUTTON_CANCELED;
import static ru.linkstuff.neptune.Neptune.BUTTON_CLICKED;
import static ru.linkstuff.neptune.Neptune.BUTTON_FLEXIBLE_SPRITE_COUNT;
import static ru.linkstuff.neptune.Neptune.BUTTON_NULL;
import static ru.linkstuff.neptune.Neptune.BUTTON_PRESSED;
import static ru.linkstuff.neptune.Neptune.BUTTON_TYPE_CUSTOM_FIXED;
import static ru.linkstuff.neptune.Neptune.BUTTON_TYPE_CUSTOM_FLEXIBLE;
import static ru.linkstuff.neptune.Neptune.BUTTON_TYPE_FIXED;
import static ru.linkstuff.neptune.Neptune.BUTTON_TYPE_FLEXIBLE;
import static ru.linkstuff.neptune.Neptune.STATE_DISABLED;
import static ru.linkstuff.neptune.Neptune.STATE_NORMAL;
import static ru.linkstuff.neptune.Neptune.STATE_PRESSED;
import static ru.linkstuff.neptune.Neptune.debug;
import static ru.linkstuff.neptune.UI.Utils.Metric.unit;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.disabledButtonSprites;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.normalButtonSprites;
import static ru.linkstuff.neptune.UI.Utils.UIAssets.pressedButtonSprites;

public class Button extends TouchableWidget {
    private List<Sprite> customNormalButtonSprites = null;
    private List<Sprite> customPressedButtonSprites = null;
    private List<Sprite> customDisabledButtonSprites = null;

    private SpriteAttributes[] spriteAttributes = null;

    private final int type;
    private float halfScale;

    private final Artist artist;

    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);

        if (debug){
            artist = new Artist(1);
        } else {
            artist = new Artist(BUTTON_FLEXIBLE_SPRITE_COUNT);
        }

        if (normalButtonSprites.size() > 1) {
            type = BUTTON_TYPE_FLEXIBLE;
            setSpriteAttributes();
        }
        else type = BUTTON_TYPE_FIXED;
    }

    public Button(float x, float y, float width, float height, List<Sprite> normal, List<Sprite> pressed, List<Sprite> disabled){
        super(x, y, width, height);

        customNormalButtonSprites = normal;
        customPressedButtonSprites = pressed;
        customDisabledButtonSprites = disabled;

        artist = new Artist(normal.size());

        if (normal.size() > 1) {
            type = BUTTON_TYPE_CUSTOM_FLEXIBLE;
            setSpriteAttributes();
        }
        else type = BUTTON_TYPE_CUSTOM_FIXED;
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

        switch (type){
            case BUTTON_TYPE_FIXED:
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

                break;

            case BUTTON_TYPE_CUSTOM_FIXED:
                switch (state){
                    case STATE_NORMAL:
                        artist.begin(customNormalButtonSprites.get(0).getTextureSlot());
                        artist.draw(x, y, width, height, customNormalButtonSprites.get(0));
                        artist.end();
                        break;

                    case STATE_PRESSED:
                        artist.begin(customPressedButtonSprites.get(0).getTextureSlot());
                        artist.draw(x, y, width, height, customPressedButtonSprites.get(0));
                        artist.end();
                        break;

                    case STATE_DISABLED:
                        artist.begin(customDisabledButtonSprites.get(0).getTextureSlot());
                        artist.draw(x, y, width, height, customDisabledButtonSprites.get(0));
                        artist.end();
                        break;
                }

                break;

            case BUTTON_TYPE_FLEXIBLE:
                switch (state){
                    case STATE_NORMAL:
                        artist.begin(normalButtonSprites.get(0).getTextureSlot());
                        for (int i = 0; i < BUTTON_FLEXIBLE_SPRITE_COUNT; ++i){
                            artist.draw(spriteAttributes[i], normalButtonSprites.get(i));
                        }
                        artist.end();
                        break;

                    case STATE_PRESSED:
                        artist.begin(pressedButtonSprites.get(0).getTextureSlot());
                        for (int i = 0; i < BUTTON_FLEXIBLE_SPRITE_COUNT; ++i){
                            artist.draw(spriteAttributes[i], pressedButtonSprites.get(i));
                        }
                        artist.end();
                        break;

                    case STATE_DISABLED:
                        artist.begin(disabledButtonSprites.get(0).getTextureSlot());
                        for (int i = 0; i < BUTTON_FLEXIBLE_SPRITE_COUNT; ++i){
                            artist.draw(spriteAttributes[i], disabledButtonSprites.get(i));
                        }
                        artist.end();
                        break;
                }

                break;

            case BUTTON_TYPE_CUSTOM_FLEXIBLE:
                switch (state){
                    case STATE_NORMAL:
                        artist.begin(customNormalButtonSprites.get(0).getTextureSlot());

                        artist.end();
                        break;

                    case STATE_PRESSED:
                        artist.begin(customPressedButtonSprites.get(0).getTextureSlot());

                        artist.end();
                        break;

                    case STATE_DISABLED:
                        artist.begin(customDisabledButtonSprites.get(0).getTextureSlot());

                        artist.end();
                        break;
                }

                break;
        }
    }

    private void setSpriteAttributes(){
        halfScale = scale / 2;
        spriteAttributes = new SpriteAttributes[BUTTON_FLEXIBLE_SPRITE_COUNT];

        spriteAttributes[0] = new SpriteAttributes(x - (width - unit) * halfScale, y + (height - unit) * halfScale, unit * scale);
        spriteAttributes[1] = new SpriteAttributes(x, y + (height - unit) * halfScale, width - unit * scale * 2, unit * scale);
        spriteAttributes[2] = new SpriteAttributes(x + (width - unit) * halfScale, y + (height - unit) * halfScale, unit * scale);

        spriteAttributes[3] = new SpriteAttributes(x - (width - unit) * halfScale, y, unit * scale, height - unit * scale * 2);
        spriteAttributes[4] = new SpriteAttributes(x, y, width - unit * scale * 2, height - unit * scale * 2);
        spriteAttributes[5] = new SpriteAttributes(x + (width - unit) * halfScale, y, unit * scale, height - unit * scale * 2);

        spriteAttributes[6] = new SpriteAttributes(x - (width - unit) * halfScale, y - (height - unit) * halfScale, unit * scale);
        spriteAttributes[7] = new SpriteAttributes(x, y - (height - unit) * halfScale, width - unit * scale * 2, unit * scale);
        spriteAttributes[8] = new SpriteAttributes(x + (width - unit) * halfScale, y - (height - unit) * halfScale, unit * scale);
    }
}
