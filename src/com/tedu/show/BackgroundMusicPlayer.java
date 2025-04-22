package com.tedu.show;

/**
 * @description:播放背景音乐
 * @author: ManolinCoder
 */
import javax.sound.sampled.*;
import java.io.*;

public class BackgroundMusicPlayer {
    private Clip clip;

    public BackgroundMusicPlayer(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // 循环播放
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}
