package ru.linkstuff.neptune.Widgets.Framework;

import android.opengl.GLES20;

import androidx.annotation.Nullable;

import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.GLActivity;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;

public class Font {
    private final int FONT_ARRAY_SIZE = 81;
    private final String DEFAULT_FONT_FILENAME = "font.png";
    
    private static Texture fontTexture;
    private static Sprite font[];

    public Font(GLActivity glActivity, @Nullable String fileName){
        fontTexture = new Texture(glActivity, fileName == null ? DEFAULT_FONT_FILENAME : fileName, GLES20.GL_TEXTURE0);
        font = new Sprite[FONT_ARRAY_SIZE];

        minimalLoad();
        loadEnglish();
        loadRussian();
    }

    public static void draw(Symbol[] symbols){
        Artist artist = new Artist(symbols.length);

        artist.begin(fontTexture);
        for (Symbol s : symbols) artist.draw(s.x, s.y, s.width, s.height, font[s.id]);
        artist.end();
    }
    
    private void minimalLoad(){
        font[0] = new Sprite(fontTexture, 0, 110, 10, 18);
        font[1] = new Sprite(fontTexture, 102, 110, 4, 18);
        font[2] = new Sprite(fontTexture, 0, 0, 14, 18);
        font[3] = new Sprite(fontTexture, 16, 0, 12, 18);
        font[4] = new Sprite(fontTexture, 30, 0, 14, 18);
        font[5] = new Sprite(fontTexture, 46, 0, 14, 18);
        font[6] = new Sprite(fontTexture, 62, 0, 14, 18);
        font[7] = new Sprite(fontTexture, 78, 0, 14, 18);
        font[8] = new Sprite(fontTexture, 94, 0, 14, 18);
        font[9] = new Sprite(fontTexture, 110, 0, 14, 18);
        font[10] = new Sprite(fontTexture, 0, 18, 14, 18);
        font[11] = new Sprite(fontTexture, 16, 18, 14, 18);
        font[12] = new Sprite(fontTexture, 32, 18, 4, 18);
        font[13] = new Sprite(fontTexture, 38, 18, 14, 18);
        font[14] = new Sprite(fontTexture, 54, 18, 6, 18);
        font[15] = new Sprite(fontTexture, 62, 18, 4, 18);
        font[16] = new Sprite(fontTexture, 68, 18, 8, 18);
        font[17] = new Sprite(fontTexture, 78, 18, 8, 18);
        font[18] = new Sprite(fontTexture, 88, 18, 14, 18);
        font[19] = new Sprite(fontTexture, 104, 18, 12, 18);
        font[20] = new Sprite(fontTexture, 118, 18, 4, 18);
        font[21] = new Sprite(fontTexture, 0, 36, 14, 18);
    }
    
    private void loadEnglish(){
        font[22] = new Sprite(fontTexture, 128, 0, 14, 18);
        font[23] = new Sprite(fontTexture, 144, 0, 14, 18);
        font[24] = new Sprite(fontTexture, 160, 0, 14, 18);
        font[25] = new Sprite(fontTexture, 176, 0, 14, 18);
        font[26] = new Sprite(fontTexture, 192, 0, 14, 18);
        font[27] = new Sprite(fontTexture, 208, 0, 14, 18);
        font[28] = new Sprite(fontTexture, 224, 0, 14, 18);
        font[29] = new Sprite(fontTexture, 240, 0, 14, 18);
        font[30] = new Sprite(fontTexture, 128, 18, 12, 18);
        font[31] = new Sprite(fontTexture, 142, 18, 14, 18);
        font[32] = new Sprite(fontTexture, 158, 18, 14, 18);
        font[33] = new Sprite(fontTexture, 174, 18, 12, 18);
        font[34] = new Sprite(fontTexture, 188, 18, 14, 18);
        font[35] = new Sprite(fontTexture, 204, 18, 14, 18);
        font[36] = new Sprite(fontTexture, 220, 18, 14, 18);
        font[37] = new Sprite(fontTexture, 236, 18, 14, 18);
        font[38] = new Sprite(fontTexture, 128, 36, 14, 18);
        font[39] = new Sprite(fontTexture, 144, 36, 14, 18);
        font[40] = new Sprite(fontTexture, 160, 36, 14, 18);
        font[41] = new Sprite(fontTexture, 176, 36, 12, 18);
        font[42] = new Sprite(fontTexture, 190, 36, 14, 18);
        font[43] = new Sprite(fontTexture, 206, 36, 14, 18);
        font[44] = new Sprite(fontTexture, 222, 36, 14, 18);
        font[45] = new Sprite(fontTexture, 238, 36, 14, 18);
        font[46] = new Sprite(fontTexture, 128, 54, 12, 18);
        font[47] = new Sprite(fontTexture, 142, 54, 14, 18);
    }
    
    private void loadRussian(){
        font[48] = new Sprite(fontTexture, 256, 0, 14, 18);
        font[49] = new Sprite(fontTexture, 272, 0, 14, 18);
        font[50] = new Sprite(fontTexture, 288, 0, 14, 18);
        font[51] = new Sprite(fontTexture, 304, 0, 12, 18);
        font[52] = new Sprite(fontTexture, 318, 0, 16, 18);
        font[53] = new Sprite(fontTexture, 336, 0, 14, 18);
        font[54] = new Sprite(fontTexture, 352, 0, 14, 18);
        font[55] = new Sprite(fontTexture, 368, 0, 14, 18);
        font[56] = new Sprite(fontTexture, 256, 18, 14, 18);
        font[57] = new Sprite(fontTexture, 272, 18, 14, 18);
        font[58] = new Sprite(fontTexture, 288, 18, 14, 18);
        font[59] = new Sprite(fontTexture, 304, 18, 14, 18);
        font[60] = new Sprite(fontTexture, 320, 18, 14, 18);
        font[61] = new Sprite(fontTexture, 336, 18, 14, 18);
        font[62] = new Sprite(fontTexture, 352, 18, 14, 18);
        font[63] = new Sprite(fontTexture, 368, 18, 14, 18);
        font[64] = new Sprite(fontTexture, 256, 36, 14, 18);
        font[65] = new Sprite(fontTexture, 272, 36, 14, 18);
        font[66] = new Sprite(fontTexture, 288, 36, 14, 18);
        font[67] = new Sprite(fontTexture, 304, 36, 12, 18);
        font[68] = new Sprite(fontTexture, 318, 36, 14, 18);
        font[69] = new Sprite(fontTexture, 334, 36, 14, 18);
        font[70] = new Sprite(fontTexture, 350, 36, 14, 18);
        font[71] = new Sprite(fontTexture, 366, 36, 14, 18);
        font[72] = new Sprite(fontTexture, 256, 54, 14, 18);
        font[73] = new Sprite(fontTexture, 272, 54, 14, 18);
        font[74] = new Sprite(fontTexture, 288, 54, 16, 18);
        font[75] = new Sprite(fontTexture, 306, 54, 14, 18);
        font[76] = new Sprite(fontTexture, 322, 54, 18, 18);
        font[77] = new Sprite(fontTexture, 342, 54, 14, 18);
        font[78] = new Sprite(fontTexture, 358, 54, 14, 18);
        font[79] = new Sprite(fontTexture, 256, 72, 16, 18);
        font[80] = new Sprite(fontTexture, 274, 72, 14, 18);
    }
}
