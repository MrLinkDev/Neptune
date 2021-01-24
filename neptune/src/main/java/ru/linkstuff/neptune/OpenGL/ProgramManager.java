package ru.linkstuff.neptune.OpenGL;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import static ru.linkstuff.neptune.Neptune.SHADER_TYPE_COLOR;
import static ru.linkstuff.neptune.Neptune.SHADER_TYPE_TEXTURE;

public class ProgramManager {
    public static List<Program> programs;

    public static void create(Context context) {
        programs = new ArrayList<>();

        createDefaultPrograms(context);
    }

    private static void createDefaultPrograms(Context context) {
        programs.add(new Program(context, SHADER_TYPE_COLOR));
        programs.add(new Program(context, SHADER_TYPE_TEXTURE));
    }

    public static int createProgram(Context context, int vertexShader, int fragmentShader) {
        programs.add(new Program(context, vertexShader, fragmentShader));
        return programs.size() - 1;
    }

    public static Program defaultColorProgram() {
        return programs.get(0);
    }

    public static Program defaultTextureProgram() {
        return programs.get(1);
    }

    public static Program program(int index) {
        return programs.get(index);
    }
}
