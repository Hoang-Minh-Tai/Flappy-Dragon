package main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private String backgroundFilePath = "resource/audio/main.wav";
    private String folderPath = "resource/audio/";
    private Clip backgroundClip;

    public AudioManager() {
        loadBackgroundClip();
    }

    private void loadBackgroundClip() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(backgroundFilePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip = clip;
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void playBackgroundMusic() {
        backgroundClip.start();
    }

    public void pauseBackgroundMusic() {
        backgroundClip.stop();
    }

    public void playSound(String name) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(folderPath + name + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


}
