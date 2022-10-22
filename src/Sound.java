import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    static Clip clip;

    public static URL PLAYER_HIT_SOUND;

    public static URL WALL_HIT_SOUND;

    public static URL FIRESHOT_SOUND;

    public static URL[] SPEED_SOUND = new URL[2];

    public static URL[] LARGE_SOUND = new URL[2];

    public static URL INVERTED_CONTROLS_SOUND;

    Sound() {
        PLAYER_HIT_SOUND = getClass().getResource("/SFX/Player hit.wav");
        WALL_HIT_SOUND = getClass().getResource("/SFX/Wall hit.wav");
        FIRESHOT_SOUND = getClass().getResource("/SFX/FireShot.wav");
        SPEED_SOUND[0] = getClass().getResource("/SFX/end Speed powerup.wav");
        SPEED_SOUND[1] = getClass().getResource("/SFX/Speed powerup.wav");
        LARGE_SOUND[0] = getClass().getResource("/SFX/Large racket powerup.wav");
        LARGE_SOUND[1] = getClass().getResource("/SFX/end Large racket powerup.wav");
        INVERTED_CONTROLS_SOUND = getClass().getResource("/SFX/Inverted controls powerup.wav");
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


