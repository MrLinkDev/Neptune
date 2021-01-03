package ru.linkstuff.neptune.OpenGL;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ProgramManager {
    public static List<Program> programs;

    public static void create(Context context) {
        programs = new ArrayList<>();

        createDefaultProgram(context);
    }

    private static void createDefaultProgram(Context context) {
        programs.add(new Program(context));
    }

    public static int createProgram(Context context, int vertexShader, int fragmentShader) {
        programs.add(new Program(context, vertexShader, fragmentShader));
        return programs.size() - 1;
    }

    public static Program defaultProgram() {
        return programs.get(0);
    }

    public static Program program(int index) {
        return programs.get(index);
    }
}
