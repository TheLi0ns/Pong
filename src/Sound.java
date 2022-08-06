import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    static Clip clip;

    public static URL PLAYER_HIT_SOUND;

    public static URL WALL_HIT_SOUND;

    Sound() {
        PLAYER_HIT_SOUND = getClass().getResource("/SFX/Player hit.wav");
        WALL_HIT_SOUND = getClass().getResource("/SFX/Wall hit.wav");
    }

    public static void play(URL url) {
        setSound(url);
        playSound();
    }

    private static void playSound() {
        clip.start();
    }

    private static void setSound(URL url) {
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception exception) {}
    }
}


