package ru.linkstuff.neptune;

import ru.linkstuff.neptune.UI.Utils.UIAssets;

public class Neptune {

    //Позиция счётчика кадров в секунду
    public static final int FPS_POSITION_TOP_LEFT = 0;
    public static final int FPS_POSITION_TOP_RIGHT = 1;
    public static final int FPS_POSITION_BOTTOM_LEFT = 2;
    public static final int FPS_POSITION_BOTTOM_RIGHT = 3;

    //Размеры debug-спрайтов
    public static final int DEBUG_SPRITE_WIDTH = 6;
    public static final int DEBUG_SPRITE_HEIGHT = 8;
    public static final int DEBUG_FPS_LABEL_WIDTH = 16;

    //Расположение стандартных шейдеров для обработки цвета
    public final static int DEFAULT_VERTEX_COLOR_SHADER = R.raw.vertex_color_shader;
    public final static int DEFAULT_FRAGMENT_COLOR_SHADER = R.raw.fragment_color_shader;

    //Расположение стандартных шейдеров для обработки текстур
    public final static int DEFAULT_VERTEX_TEXTURE_SHADER = R.raw.vertex_texture_shader;
    public final static int DEFAULT_FRAGMENT_TEXTURE_SHADER = R.raw.fragment_texture_shader;

    //Тип шейдера
    public final static int SHADER_TYPE_COLOR = 0;
    public final static int SHADER_TYPE_TEXTURE = 1;

    //Состояние TouchableWidget
    public final static int STATE_NORMAL = 0;
    public final static int STATE_PRESSED = 1;
    public final static int STATE_DISABLED = 2;

    //Состояние Button
    public static final int BUTTON_NULL = -1;
    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_CLICKED = 1;
    public static final int BUTTON_CANCELED = 2;

    //Константы для Button
    public static final int BUTTON_SPRITE_SIZE = 8;
    public static final int BUTTON_STATE_DELTA = 1;
    public static final int BUTTON_BORDER = 6;
    public static final int BUTTON_FLEXIBLE_SPRITE_COUNT = 9;

    //Типы для Button
    public static final int BUTTON_TYPE_FIXED = 0;
    public static final int BUTTON_TYPE_FLEXIBLE = 1;
    public static final int BUTTON_TYPE_CUSTOM_FIXED = 2;
    public static final int BUTTON_TYPE_CUSTOM_FLEXIBLE = 3;

    //Изменяемые глобальные переменные
    public static boolean debug = false;

    public static void setDebug(boolean isDebug){
        debug = isDebug;

        UIAssets.loadDebugButtonSprites();
    }
}
