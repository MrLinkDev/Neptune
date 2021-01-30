package ru.linkstuff.neptune.UI.Widgets;

import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;
import ru.linkstuff.neptune.UI.Widget;

public class Image extends Widget {
    private Sprite image;
    private final Artist artist;

    public Image(float x, float y, float width, float height, Sprite image){
        super(x, y, width, height);

        this.image = image;
        artist = new Artist(1);
    }

    public Image(float x, float y, float width, float height){
        super(x, y, width, height);

        image = new Sprite(TextureManager.getDebug(), 1, 30, 1, 1);
        artist = new Artist(1);
    }

    @Override
    public void draw(float deltaTime) {
        artist.begin(image.getTextureSlot());
        artist.draw(x, y, width, height, image);
        artist.end();
    }

    public Sprite getImage() {
        return image;
    }

    public void setImage(Sprite image) {
        this.image = image;
    }
}
