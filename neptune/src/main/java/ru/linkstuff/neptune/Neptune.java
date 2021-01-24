package ru.linkstuff.neptune;

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
}
