package ru.linkstuff.neptune.Framework.Android;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class Audio implements ru.linkstuff.neptune.Framework.Audio {
    AssetManager assetManager;
    SoundPool soundPool;

    public Audio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assetManager = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(fileName);
            return new Music(assetFileDescriptor);
        } catch (IOException e){
            throw new RuntimeException("CAN'T LOAD MUSIC!!!");
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(fileName);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new Sound(soundPool, soundId);
        } catch (IOException e){
            throw new RuntimeException("CAN'T LOAD SOUND!!!");
        }
    }
}
