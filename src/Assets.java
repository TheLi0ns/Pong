import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Assets {
    static final Image RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Racket.png"))).getImage();
    static final Image SPEEDY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Speedy racket.png"))).getImage();
    static final Image LARGE_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Long racket.png"))).getImage();
    static final Image INVERTED_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Inverted racket.png"))).getImage();
    static final Image SHRUNK_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Shrunk racket.png"))).getImage();
    static final Image PARRY_RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Parry racket.png"))).getImage();

    static final Image BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Ball.png"))).getImage();
    private static final Image FIRE_BALL_UP = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Fire ball up.png"))).getImage();
    private static final Image FIRE_BALL_DOWN = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/Fire ball down.png"))).getImage();
    static final Image[] FIRE_BALL = {FIRE_BALL_UP, FIRE_BALL_DOWN};

    static final Image PAUSE_MENU = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pause Menu.png"))).getImage();
}
