package ru.linkstuff.neptune.Framework.Android;

import android.content.res.AssetManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileIO implements ru.linkstuff.neptune.Framework.FileIO {
    AssetManager assetManager;

    public FileIO(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }
}
