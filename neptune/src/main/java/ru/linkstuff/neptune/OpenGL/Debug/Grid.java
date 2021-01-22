package ru.linkstuff.neptune.OpenGL.Debug;


import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;

public class Grid {
    Line[] linesX;
    Line[] linesY;

    Sprite lineSprite;

    Artist artist;
    //Fixme: Поправить создание сетки в ландшафтном решиме
    public Grid(float x, float y, int width, int height, float uWidth, float uHeight){
        float delta = width / uWidth;
        if (delta * uHeight > height) delta = height / uHeight;

        float startX = x - delta * (float) Math.round(uWidth) / 2;
        float startY = y - delta * uHeight / 2;

        linesX = new Line[(int) (width / delta) + 1];
        linesY = new Line[(int) (height / delta) + 1];

        for (int i = 0; i < linesX.length; ++i)
            linesX[i] = new Line(startX + i * delta, y, 1, height);

        for (int i = 0; i < linesY.length; ++i)
            linesY[i] = new Line(x, startY + i * delta, width, 1);

        lineSprite = new Sprite(TextureManager.getDebug(), 31, 31, 1, 1);
        artist = new Artist(linesX.length + linesY.length, Artist.TYPE_TEXTURE);
    }

    public void draw(){
        artist.begin(TextureManager.getDebug());
        for (Line line : linesX) artist.draw(line.x, line.y, line.width, line.height, lineSprite);
        for (Line line : linesY) artist.draw(line.x, line.y, line.width, line.height, lineSprite);
        artist.end();
    }
}

class Line {
    public float x, y;
    public float width, height;

    public Line(float x, float y, float width, float height){
        this.x= x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
