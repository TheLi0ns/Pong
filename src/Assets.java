import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Assets {
    static final Image RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/racchetta.png"))).getImage();
    static final Image SPEEDY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/racchetta veloce v2.png"))).getImage();
    static final Image LARGE_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/racchetta lunga.png"))).getImage();

    static final Image BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pallina.png"))).getImage();
    private static final Image FIRE_BALL_UP = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pallina di fuoco up.png"))).getImage();
    private static final Image FIRE_BALL_DOWN = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pallina di fuoco down.png"))).getImage();
    static final Image[] FIRE_BALL = {FIRE_BALL_UP, FIRE_BALL_DOWN};

    static final Image PAUSE_MENU = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pause Menu.png"))).getImage();
}
