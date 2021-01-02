package ru.linkstuff.neptune.Framework;

public abstract class Scene {
    protected final Game game;

    protected Scene(Game game){
        this.game = game;
    }

    public abstract void present(float deltaTime);
    public abstract void update(float deltaTime);

    public abstract void resume();
    public abstract void pause();
    public abstract void dispose();
}
