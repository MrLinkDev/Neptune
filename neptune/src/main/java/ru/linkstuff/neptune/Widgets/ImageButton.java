package ru.linkstuff.neptune.Widgets;

import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;

public class ImageButton extends Button {
    private ImageView image;

    public ImageButton(float x, float y, float width, float height){
        super(x, y, width, height);
    }

    public ImageButton(float x, float y, Sprite image) {
        super(x, y);

        this.image = new ImageView(x, y, width, height, image);
    }

    public ImageButton(float x, float y, float width, float height, Sprite image) {
        super(x, y, width, height);

        this.image = new ImageView(x, y, width, height, image);
    }

    public void setImage(Sprite image){
        this.image.setImage(image);
    }

    public void draw(Texture buttonTexture, Texture imageTexture) {
        super.draw(buttonTexture);
        image.draw(imageTexture);
    }

    @Override
    public int onClick(Input.TouchEvent event) {
        return super.onClick(event);
    }

    @Override
    public void setCoords(float x, float y) {
        super.setCoords(x, y);
        image.setCoords(x, y);
    }

    @Override
    public void addCoords(float dX, float dY) {
        super.addCoords(dX, dY);
        image.addCoords(dX, dY);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        image.setScale(scale);
    }
}
