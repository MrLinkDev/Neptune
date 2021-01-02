package ru.linkstuff.neptune.Widgets;

import ru.linkstuff.neptune.Framework.Android.Input;
import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.OpenGL.Utils.SpriteAttributes;
import ru.linkstuff.neptune.Widgets.Framework.TouchableWidget;
import ru.linkstuff.neptune.Widgets.Framework.Unit;

import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DOWN;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_UP;
import static ru.linkstuff.neptune.Framework.Input.TouchEvent.TOUCH_DRAGGED;

import static ru.linkstuff.neptune.Widgets.Framework.AssetManager.button;
import static ru.linkstuff.neptune.Widgets.Framework.AssetManager.customButton;
import static ru.linkstuff.neptune.Widgets.Framework.Unit.ratio;

public class Button implements TouchableWidget {
    private final int DEFAULT_STATE_DELTA = 1;
    private final int CONTENT_BORDER = 6;

    public static final int DEFAULT_WIDTH = 4;
    public static final int DEFAULT_HEIGHT = 1;

    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_CLICKED = 1;
    public static final int BUTTON_CANCELED = 2;
    public static final int BUTTON_NULL = 3;

    public static final int STATE_DEFAULT = 0;
    public static final int STATE_PRESSED = 1;
    public static final int STATE_DISABLED = 2;

    protected float x, y, x1, y1, x2, y2;
    protected float width, height;

    protected float scale;
    protected float delta;

    protected int state;

    protected boolean visible = true;
    protected boolean clickable = true;

    private boolean customSize = false;

    private SpriteAttributes[] attributes;
    protected Artist artist;

    protected TextView buttonText;

    public Button(float x, float y){
        this.x = x;
        this.y = y;

        width = Unit.toFloat(DEFAULT_WIDTH);
        height = Unit.toFloat(DEFAULT_HEIGHT);

        scale = 1;

        state = STATE_DEFAULT;

        x1 = x - width / 2;
        x2 = x + width / 2;
        y1 = y - height / 2;
        y2 = y + height / 2;

        artist = new Artist(button.length);
        delta = DEFAULT_STATE_DELTA * ratio;
    }

    public Button(float x, float y, float width, float height){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        scale = 1;

        state = STATE_DEFAULT;

        customSize = true;

        x1 = x - width / 2;
        x2 = x + width / 2;
        y1 = y - height / 2;
        y2 = y + height / 2;

        artist = new Artist(customButton.length);
        delta = DEFAULT_STATE_DELTA * ratio;
        createSpriteAttributes();
    }

    public void createSpriteAttributes(){
        float spriteSize = customButton[0][0].width * ratio;
        float halfScale = scale / 2;

        attributes = new SpriteAttributes[9];

        attributes[0] = new SpriteAttributes(
                x - (width - spriteSize) * halfScale,
                y + (height - spriteSize) * halfScale,
                spriteSize * scale);

        attributes[1] = new SpriteAttributes(
                x,
                y + (height - spriteSize) * halfScale,
                (width - spriteSize * 2) * scale,
                spriteSize * scale);

        attributes[2] = new SpriteAttributes(
                x + (width - spriteSize) * halfScale,
                y + (height - spriteSize) * halfScale,
                spriteSize * scale);

        attributes[3] = new SpriteAttributes(
                x - (width - spriteSize) * halfScale,
                y,
                spriteSize * scale,
                (height - spriteSize * 2) * scale);

        attributes[4] = new SpriteAttributes(
                x,
                y,
                (width - spriteSize * 2) * scale,
                (height - spriteSize * 2) * scale);

        attributes[5] = new SpriteAttributes(
                x + (width - spriteSize) * halfScale,
                y,
                spriteSize * scale,
                (height - spriteSize * 2) * scale);

        attributes[6] = new SpriteAttributes(
                x - (width - spriteSize) * halfScale,
                y - (height - spriteSize) * halfScale,
                spriteSize * scale);

        attributes[7] = new SpriteAttributes(
                x,
                y - (height - spriteSize) * halfScale,
                (width - spriteSize * 2) * scale,
                spriteSize * scale);

        attributes[8] = new SpriteAttributes(
                x + (width - spriteSize) * halfScale,
                y - (height - spriteSize) * halfScale,
                spriteSize * scale);
    }

    public void setText(String text){
        if (buttonText == null){
            buttonText = new TextView(x, y + delta * scale, width - CONTENT_BORDER * 2 * ratio, height - CONTENT_BORDER * 2 * ratio, text);
        } else buttonText.setText(text);
    }

    @Override
    public void draw(Texture texture) {
        if (visible){
            artist.begin(texture);
            if (customSize){
                for (int i = 0; i < customButton.length; ++i){
                    artist.draw(attributes[i], customButton[i][state]);
                }
            } else {
                artist.draw(x, y, width * scale, height * scale, button[state]);
            }
            artist.end();

            buttonText.draw();
        }
    }

    @Override
    public int onClick(Input.TouchEvent event) {
        if (clickable && event.pointer == 0){
            switch (event.type){
                case TOUCH_DOWN:
                    if (event.x >= x1 && event.y >= y1 && event.x <= x2 && event.y <= y2){
                        state = STATE_PRESSED;
                        if (buttonText != null) buttonText.addCoords(0, -delta * scale);
                        return BUTTON_PRESSED;
                    }
                    break;
                case TOUCH_UP:
                    if (state == STATE_PRESSED){
                        state = STATE_DEFAULT;
                        if (buttonText != null) buttonText.addCoords(0, delta * scale);
                        return BUTTON_CLICKED;
                    }
                    break;
                case TOUCH_DRAGGED:
                    if (state == STATE_PRESSED && !(event.x >= x1 && event.y >= y1 && event.x <= x2 && event.y <= y2)){
                        state = STATE_DEFAULT;
                        if (buttonText != null) buttonText.addCoords(0, delta * scale);
                        return BUTTON_CANCELED;
                    }
                    break;
            }
        }

        return BUTTON_NULL;
    }

    @Override
    public void setClickable(boolean isClickable) {
        clickable = isClickable;

        if (clickable) state = STATE_DEFAULT;
        else state = STATE_DISABLED;
    }

    @Override
    public boolean isClickable() {
        return clickable;
    }

    @Override
    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;

        if (customSize) createSpriteAttributes();
        if (buttonText != null) buttonText.setCoords(x, y);
    }

    @Override
    public void addCoords(float dX, float dY) {
        x += dX;
        y += dY;

        if (customSize) createSpriteAttributes();
        if (buttonText != null) buttonText.addCoords(dX, dY);
    }

    @Override
    public void setVisibility(boolean isVisible) {
        visible = isVisible;
        clickable = visible;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;

        if (customSize) createSpriteAttributes();
        if (buttonText != null) buttonText.setScale(scale);
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
