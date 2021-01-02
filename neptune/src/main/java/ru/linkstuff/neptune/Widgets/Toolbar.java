package ru.linkstuff.neptune.Widgets;

import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.Widgets.Framework.AssetManager;
import ru.linkstuff.neptune.Widgets.Framework.TouchableWidget;

import static ru.linkstuff.neptune.Widgets.Framework.AssetManager.toolbarButton;
import static ru.linkstuff.neptune.Widgets.Framework.Unit.ratio;

public class Toolbar implements TouchableWidget {
    private final int DEFAULT_CONTENT_BORDER = 7;
    private final int DEFAULT_STATE_DELTA = 3;

    private final int NULL_BUTTON_ID = -1;

    private float x, y;
    private float width, height;

    protected float x1, y1, x2, y2;

    protected float scale;
    protected float delta;

    protected boolean visible = true;
    protected boolean clickable = true;

    private int numOfButtons;
    private float buttonSize;
    private float startX;

    private int pressedButtonId = NULL_BUTTON_ID;

    private Artist artist;
    private ImageView[] toolbarIcons;

    public Toolbar(float x, float y, float width, float height, int numOfButtons){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.numOfButtons = numOfButtons;

        buttonSize = height;
        if (buttonSize * numOfButtons > width) buttonSize = width / numOfButtons;
        startX = x - buttonSize * numOfButtons / 2 + buttonSize / 2;

        artist = new Artist(numOfButtons);
    }

    public void setIcon(int buttonId, Sprite icon){
        toolbarIcons[buttonId] = new ImageView(
                startX + buttonId * buttonSize,
                y,
                buttonSize - 2 * DEFAULT_CONTENT_BORDER * ratio,
                buttonSize - 2 * DEFAULT_CONTENT_BORDER * ratio,
                icon);
    }

    @Override
    public int onClick(Input.TouchEvent event) {
        if (event.pointer == 0 && event.type == Input.TouchEvent.TOUCH_DOWN){
            if (event.y >= y1 && event.y <= y2 && event.x >= x1 && event.x <= x2){

                for (int i = 0; i < numOfButtons; ++i){
                    if (event.x <= startX + buttonSize * (i + 1)) {
                        if (toolbarIcons[pressedButtonId] != null) toolbarIcons[pressedButtonId].addCoords(0, DEFAULT_STATE_DELTA * ratio);
                        if (toolbarIcons[i] != null) toolbarIcons[i].addCoords(0, -DEFAULT_STATE_DELTA * ratio);
                        return pressedButtonId = i;
                    }
                }
            }
        }
        return NULL_BUTTON_ID;
    }

    @Override
    public void setClickable(boolean isClickable) {

    }

    @Override
    public boolean isClickable() {
        return false;
    }

    @Override
    public void draw(Texture texture) {
        artist.begin(texture);
        for (int i = 0; i < numOfButtons; ++i){
            artist.draw(startX + i * buttonSize, y, buttonSize, buttonSize, toolbarButton[(i == pressedButtonId ? 1 : 0)]);
        }
        artist.end();

        for (ImageView icon : toolbarIcons){
            if (icon != null) icon.draw(texture);
        }
    }

    @Override
    public void setCoords(float x, float y) {

    }

    @Override
    public void addCoords(float dX, float dY) {

    }

    @Override
    public void setVisibility(boolean isVisible) {

    }

    @Override
    public void setScale(float scale) {

    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
