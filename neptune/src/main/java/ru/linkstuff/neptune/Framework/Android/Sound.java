package ru.linkstuff.neptune.Framework.Android;

import android.media.SoundPool;

public class Sound implements ru.linkstuff.neptune.Framework.Sound {
    int soundId;
    SoundPool soundPool;

    public Sound(SoundPool soundPool, int soundId){
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
