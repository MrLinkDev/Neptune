package ru.linkstuff.neptune.Framework;

public interface Game {
    public Audio getAudio();
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();

    public void setScene(Scene scene);

    public Scene getStartScene();
    public Scene getCurrentScene();
}
