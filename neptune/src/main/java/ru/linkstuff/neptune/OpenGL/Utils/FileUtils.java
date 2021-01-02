package ru.linkstuff.neptune.OpenGL.Utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String readTextFromRaw(Context context, int resourceId){
        StringBuilder stringBuilder = new StringBuilder();

        try{
            BufferedReader bufferedReader = null;
            try {
                InputStream inputStream = context.getResources().openRawResource(resourceId);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\r\n");
                }
            } finally {
                if (bufferedReader != null) bufferedReader.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Resources.NotFoundException nfe){
            nfe.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
