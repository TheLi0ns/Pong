import javax.swing.*;

public class MyFrame extends JFrame {
    JFrame frame = new JFrame();
    static GamePanel gamePanel = new GamePanel();

    Assets assets = new Assets();
    Sound sound = new Sound();
    static GameLogic gameLogic = new GameLogic();
    MyFrame(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
    }
}
