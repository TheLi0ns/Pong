package com.TheLi0ns.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Assets {
    public static final Image RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Racket.png"))).getImage();
    public static final Image SPEEDY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Speedy racket.png"))).getImage();
    public static final Image LARGE_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Long racket.png"))).getImage();
    public static final Image INVERTED_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Inverted racket.png"))).getImage();
    public static final Image SHRUNK_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Shrunk racket.png"))).getImage();
    public static final Image MEDIUM_SHRUNK_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Medium shrunk racket.png"))).getImage();
    public static final Image PARRY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Parry racket.png"))).getImage();

    public static final Image THE_PYROMANCER = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/ThePyromancer/The Pyromancer.png"))).getImage();

    public static final Image[] THE_PYROMANCER_CUTSCENE = new Image[20];

    {
        Image THE_PYROMANCER_CUTSCENE_SHEET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/ThePyromancer/The Pyromancer cutscene sheet.png"))).getImage();
        BufferedImage sheet = new BufferedImage(THE_PYROMANCER_CUTSCENE_SHEET.getWidth(null), THE_PYROMANCER_CUTSCENE_SHEET.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sheet.createGraphics();
        g2d.drawImage(THE_PYROMANCER_CUTSCENE_SHEET, 0, 0, null);
        g2d.dispose();

        int k = 0;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++) {
                THE_PYROMANCER_CUTSCENE[k] = sheet.getSubimage(j*1000, i*1000, 1000, 1000);
                k++;
            }
        }
    }

    private static final Image THE_SHRINKER_FIRST_PHASE_NORMAL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheShrinker/The Shrinker first phase base.png"))).getImage();
    public static final Image[] THE_SHRINKER_CUTSCENE = new Image[20];

    {
        Image THE_SHRINKER_CUTSCENE_SHEET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheShrinker/The Shrinker cutscene sheet.png"))).getImage();
        BufferedImage sheet = new BufferedImage(THE_SHRINKER_CUTSCENE_SHEET.getWidth(null), THE_SHRINKER_CUTSCENE_SHEET.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sheet.createGraphics();
        g2d.drawImage(THE_SHRINKER_CUTSCENE_SHEET, 0, 0, null);
        g2d.dispose();

        int k = 0;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++) {
                THE_SHRINKER_CUTSCENE[k] = sheet.getSubimage(j*1000, i*1000, 1000, 1000);
                k++;
            }
        }
    }

    private static final Image THE_SHRINKER_FIRST_PHASE_SKILL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheShrinker/The Shrinker first phase smallball.png"))).getImage();
    /**
     * 0 normal
     * 1 skill
     */
    public static final Image[] THE_SHRINKER_FIRST_PHASE = {THE_SHRINKER_FIRST_PHASE_NORMAL, THE_SHRINKER_FIRST_PHASE_SKILL};
    public static final Image THE_SHRINKER_SECOND_PHASE = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheShrinker/The Shrinker second phase.png"))).getImage();

    public static final Image THE_DISORIENTATOR = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheDisorientator/The Disorientator.png"))).getImage();
    private static final Image THE_DISORIENTATOR_EYES_OPENING_F1 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheDisorientator/eyesOpening/The disorientator open f1.png"))).getImage();
    private static final Image THE_DISORIENTATOR_EYES_OPENING_F2 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheDisorientator/eyesOpening/The disorientator open f2.png"))).getImage();
    public static final Image[] THE_DISORIENTATOR_SKILL_ACTIVATION = {THE_DISORIENTATOR,THE_DISORIENTATOR_EYES_OPENING_F1, THE_DISORIENTATOR_EYES_OPENING_F2};
    public static final Image THE_DISORIENTATOR_SKILL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheDisorientator/The disorientator skill.gif"))).getImage();

    public static final Image[] THE_DISORIENTATOR_CUTSCENE = new Image[20];

    {
        Image THE_DISORIENTATOR_CUTSCENE_SHEET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/bosses/TheDisorientator/The Disorientator cutscene sheet.png"))).getImage();
        BufferedImage sheet = new BufferedImage(THE_DISORIENTATOR_CUTSCENE_SHEET.getWidth(null), THE_DISORIENTATOR_CUTSCENE_SHEET.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sheet.createGraphics();
        g2d.drawImage(THE_DISORIENTATOR_CUTSCENE_SHEET, 0, 0, null);
        g2d.dispose();

        int k = 0;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++) {
                THE_DISORIENTATOR_CUTSCENE[k] = sheet.getSubimage(j*1000, i*1000, 1000, 1000);
                k++;
            }
        }
    }


    private static final Image HEART = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Heart.png"))).getImage();
    private static final Image HEART_EMPTY = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Heart empty.png"))).getImage();

    /**
     * 0 empty heart
     * 1 normal heart
     */
    public static final Image[] HEARTS = {HEART_EMPTY, HEART};

    public static final Image BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Ball.png"))).getImage();
    private static final Image FIRE_BALL_UP = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Fire ball up.png"))).getImage();
    private static final Image FIRE_BALL_DOWN = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Fire ball down.png"))).getImage();

    /**
     * 0 UP 1 DOWN
     */
    public static final Image[] FIRE_BALL = {FIRE_BALL_UP, FIRE_BALL_DOWN};
    public static final Image LARGE_BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Large ball.png"))).getImage();
    public static final Image SMALL_BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Small ball.png"))).getImage();

    private static final Image GAME_OVER_PT1 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/gameOver/Game over pt1.png"))).getImage();
    private static final Image GAME_OVER_PT2 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/gameOver/Game over pt2.png"))).getImage();
    /**
     * 0 pt1
     * 1 pt2
     */
    public static final Image[] GAME_OVER = {GAME_OVER_PT1, GAME_OVER_PT2};

    private static final Image YOU_WIN_PT1 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/youWin/You win pt1.png"))).getImage();
    private static final Image YOU_WIN_PT2 = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/youWin/You win pt2.png"))).getImage();
    /**
     * 0 pt1
     * 1 pt2
     */
    public static final Image[] YOU_WIN = {YOU_WIN_PT1, YOU_WIN_PT2};

    public static final Image GAME_PREVIEW = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Game preview.png"))).getImage();
}
