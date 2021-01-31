package ru.linkstuff.neptune.OpenGL;

import java.util.ArrayList;
import java.util.List;

import ru.linkstuff.neptune.UI.Utils.UIAssets;

public class TextureManager {
    private static final String DEBUG_TEXTURE_FILENAME = "debug.png";
    private static final int MAX_TEXTURE_SLOT = 31;

    private static List<Texture> textureList = new ArrayList<>();

    private static int slot = 0;

    public static void loadDebugTextures(GLActivity glActivity){
        textureList.add(new Texture(glActivity, DEBUG_TEXTURE_FILENAME, slot));

        ++slot;
    }

    /**
     * Загружает запрашиваемую текстуру в массив текстур, готовых для работы
     * @param glActivity
     * @param filename имя файла текстуры
     * @return Слот загруженной текстуры
     */
    public static int loadTexture(GLActivity glActivity, String filename){
        if (slot == MAX_TEXTURE_SLOT) return -1;
        textureList.add(new Texture(glActivity, filename, slot));

        return slot++;
    }

    public static void bindTexture(int slot){
        textureList.get(slot).bind();
    }

    /**
     * Метод возвращает текстуру по запрашиваемому номеру слота
     * @param slot номер слота запрашиваемой текстуры
     * @return текстуру
     */
    public static Texture get(int slot){
        if (slot < 0 || slot > MAX_TEXTURE_SLOT) return null;
        else return textureList.get(slot);
    }

    /**
     * Возвращает debug-текстуру
     * @return debug-текстура
     */
    public static Texture getDebug(){
        return textureList.get(0);
    }
}
