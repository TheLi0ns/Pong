package com.TheLi0ns.Utility;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Sound {

    static Clip clip;

    static Clip backgroundMusic_clip;

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

    public static URL LASER_SOUND;

    public static URL GIGALASER_SOUND;

    public static URL FIGHTER_DAMAGED_SOUND;

    public static URL BOSS_DAMAGED_SOUND;

    public static URL GAME_OVER_SOUND;

    public static URL YOU_WIN_SOUND;

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

    /**
     * Music by Marllon Silva (xDeviruchi)
     */
    public static URL BOSS_FIGHT_MUSIC;

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

        LASER_SOUND = getClass().getResource("/SFX/bossfights/theShrinker/laser.wav");

        GIGALASER_SOUND = getClass().getResource("/SFX/bossfights/theShrinker/gigalaser.wav");

        PARRY_SOUND = getClass().getResource("/SFX/powers/parry.wav");

        FIGHTER_DAMAGED_SOUND = getClass().getResource("/SFX/bossfights/Fighter damaged.wav");
        BOSS_DAMAGED_SOUND = getClass().getResource("/SFX/bossfights/Boss damaged.wav");

        BOSS_FIGHT_MUSIC = getClass().getResource("/SFX/bossfights/music/xDeviruchi - Decisive Battle (Loop).wav");

        GAME_OVER_SOUND = getClass().getResource("/SFX/bossfights/Game Over.wav");

        YOU_WIN_SOUND = getClass().getResource("/SFX/bossfights/You win.wav");

        OPTION_SELECTION = getClass().getResource("/SFX/GUI/option_selection.wav");
        OPTION_CLICK  = getClass().getResource("/SFX/GUI/option_click.wav");

        QUIT = getClass().getResource("/SFX/GUI/quit.wav");
    }

    public static void play(URL url) {
        setSound(url);
        playSound();
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

    private static void playSound() {
        clip.start();
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

    public static void playBackgroundMusic(URL url) {
        AudioInputStream ais;
        try {
            ais = AudioSystem.getAudioInputStream(url);
            backgroundMusic_clip = AudioSystem.getClip();
            backgroundMusic_clip.open(ais);
            gain_fc = (FloatControl) backgroundMusic_clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain_fc.setValue(-20f);
            backgroundMusic_clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        backgroundMusic_clip.start();
    }

    public static void stopBackgroundMusic(){
        stopping = true;

        if (backgroundMusic_clip.isRunning()) {
            backgroundMusic_clip.stop();
            backgroundMusic_clip.flush();
            backgroundMusic_clip.close();
        }

        stopping = false;
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


