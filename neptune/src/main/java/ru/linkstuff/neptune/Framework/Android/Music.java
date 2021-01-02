package ru.linkstuff.neptune.Framework.Android;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Music implements ru.linkstuff.neptune.Framework.Music, MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;

    boolean isPrepared = false;
    boolean isPaused = false;

    public Music(AssetFileDescriptor assetFileDescriptor){
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e){
            throw new RuntimeException("CAN'T LOAD MUSIC!");
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void play() {
        if (mediaPlayer.isPlaying()) return;

        if (isPaused) isPaused = false;

        try {
            synchronized (this){
                if (!isPrepared) mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();

        synchronized (this) {
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer.release();
    }
}
