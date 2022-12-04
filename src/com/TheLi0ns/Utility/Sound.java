package com.TheLi0ns.Utility;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.ArrayList;

public class Sound {

    static Clip clip;

    /**
     * List of playing clips
     */
    static ArrayList<Clip> clips = new ArrayList<>();

    static boolean stopping = false;

    public static URL PLAYER_HIT_SOUND;

    public static URL WALL_HIT_SOUND;

    public static URL SCORE_SOUND;

    public static URL FIRESHOT_SOUND;

    public static URL PARRY_SOUND;

    /**
     * 0 ends 1 starts
     */
    public static URL[] SPEED_SOUND = new URL[2];

    /**
     * 0 ends 1 starts
     */
    public static URL[] LARGE_SOUND = new URL[2];

    /**
     * 0 ends 1 starts
     */
    public static URL[] SHRINK_SOUND = new URL[2];

    public static URL INVERTED_CONTROLS_SOUND;

    public static URL OPTION_SELECTION;

    public static URL OPTION_CLICK;

    public static URL QUIT;

    //SET VOLUME VARIABLES
    static FloatControl gain_fc;
    static float volume;
    private static int volumeScale = 3;
    public static final int MAX_VOLUME_SCALE = 5;

    public Sound() {
        PLAYER_HIT_SOUND = getClass().getResource("/SFX/Player hit.wav");

        WALL_HIT_SOUND = getClass().getResource("/SFX/Wall hit.wav");

        FIRESHOT_SOUND = getClass().getResource("/SFX/powers/FireShot.wav");

        SPEED_SOUND[0] = getClass().getResource("/SFX/powers/end Speed powerup.wav");
        SPEED_SOUND[1] = getClass().getResource("/SFX/powers/Speed powerup.wav");

        LARGE_SOUND[0] = getClass().getResource("/SFX/powers/end Large racket powerup.wav");
        LARGE_SOUND[1] = getClass().getResource("/SFX/powers/Large racket powerup.wav");

        SHRINK_SOUND[0] = getClass().getResource("/SFX/powers/end Shrink.wav");
        SHRINK_SOUND[1] = getClass().getResource("/SFX/powers/Shrink.wav");

        INVERTED_CONTROLS_SOUND = getClass().getResource("/SFX/powers/Inverted controls powerup.wav");

        SCORE_SOUND = getClass().getResource("/SFX/point score.wav");

        PARRY_SOUND = getClass().getResource("/SFX/powers/parry.wav");

        OPTION_SELECTION = getClass().getResource("/SFX/GUI/option_selection.wav");
        OPTION_CLICK  = getClass().getResource("/SFX/GUI/option_click.wav");

        QUIT = getClass().getResource("/SFX/GUI/quit.wav");
    }

    public static void play(URL url) {
        setSound(url);
        playSound();
    }

    public static void stop(){
        stopping = true;
        for (Clip i : clips) {
            i.stop();
            i.flush();
            i.close();
        }
        clips.clear();
        stopping = false;
    }

    private static void playSound() {
        clip.start();
    }

    private static void setSound(URL url) {

        AudioInputStream ais;
        try {
            ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
            gain_fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain_fc.setValue(volume);
            clips.add(clip);

            //Make the clip self-removing from the clips ArrayList when it stops
            clip.addLineListener(event -> {
                if(event.getType() == LineEvent.Type.STOP && !stopping) {
                    clips.remove((Clip) event.getLine());
                }
            });

        } catch (Exception ignored) {}
    }

    public static void increaseVolume(){
        volumeScale++;
        if(volumeScale > MAX_VOLUME_SCALE) volumeScale = 0;
        setVolume();
    }

    private static void setVolume(){
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
    }

    public static int getVolumeScale() {
        return volumeScale;
    }
    public static void setVolumeScale(int volumeScale) {
        Sound.volumeScale = volumeScale;
        setVolume();
    }
}


