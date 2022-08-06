import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Assets {
    static final Image RACKET = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/racchetta.png"))).getImage();
    static final Image BALL = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pallina.png"))).getImage();
    static final Image PAUSE_MENU = new ImageIcon(Objects.requireNonNull(Assets.class.getResource("/ASSETS/pause Menu.png"))).getImage();
}
