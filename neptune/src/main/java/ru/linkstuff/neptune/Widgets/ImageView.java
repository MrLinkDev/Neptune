package ru.linkstuff.neptune.Widgets;

import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.Widgets.Framework.Widget;

public class ImageView implements Widget {
    float x, y;
    float width, height;
    float imageWidth, imageHeight;

    float scale;

    boolean visible = true;
    boolean keepProps = true;

    Sprite image;
    Artist artist;

    public ImageView(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        scale = 1;
        artist = new Artist(1);
    }

    public ImageView(float x, float y, float width, float height, Sprite image){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        scale = 1;

        this.image = image;

        if (keepProps){
            imageHeight = height;
            imageWidth = image.width * (imageHeight / image.height);
            if (imageWidth > width){
                imageWidth = width;
                imageHeight = image.height * (imageWidth / image.width);
            }
        } else {
            imageWidth = width;
            imageHeight = height;
        }

        artist = new Artist(1);
    }

    @Override
    public void draw(Texture texture) {
        if (visible){
            artist.begin(texture);
            artist.draw(x, y, imageWidth * scale, imageHeight * scale, image);
            artist.end();
        }
    }

    public void setImage(Sprite image){
        this.image = image;

        if (keepProps){
            imageHeight = height;
            imageWidth = image.width * (imageHeight / image.height);
            if (imageWidth > width){
                imageWidth = width;
                imageHeight = image.height * (imageWidth / image.width);
            }
        } else {
            imageWidth = width;
            imageHeight = height;
        }
    }

    @Override
    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void addCoords(float dX, float dY) {
        x += dX;
        y += dY;
    }

    @Override
    public void setVisibility(boolean isVisible) {
        visible = isVisible;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
}
