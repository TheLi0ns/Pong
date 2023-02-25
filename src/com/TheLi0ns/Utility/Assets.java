package com.TheLi0ns.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Assets {
    public static final Image RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Racket.png"))).getImage();
    public static final Image SPEEDY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Speedy racket.png"))).getImage();
    public static final Image LARGE_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Long racket.png"))).getImage();
    public static final Image INVERTED_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Inverted racket.png"))).getImage();
    public static final Image SHRUNK_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Shrunk racket.png"))).getImage();
    public static final Image PARRY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/player/Parry racket.png"))).getImage();

    public static final Image BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Ball.png"))).getImage();
    private static final Image FIRE_BALL_UP = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Fire ball up.png"))).getImage();
    private static final Image FIRE_BALL_DOWN = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Fire ball down.png"))).getImage();

    /**
     * 0 UP 1 DOWN
     */
    public static final Image[] FIRE_BALL = {FIRE_BALL_UP, FIRE_BALL_DOWN};
    public static final Image LARGE_BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/ball/Large ball.png"))).getImage();

    public static final Image GAME_PREVIEW = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Game preview.png"))).getImage();
}
