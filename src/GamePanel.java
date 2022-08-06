import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class GamePanel extends JPanel implements KeyListener {
    static boolean isLeft1Pressed, isRight2Pressed, isLeft2Pressed, isRight1Pressed;

    GamePanel(){
        setPreferredSize(new Dimension(1000,1000));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(0, 0, 0, 1000);
        g2d.drawLine(1000, 0, 1000, 1000);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(400, 400, 200, 200);
        g2d.drawLine(0, 500, 1000, 500);
        g2d.fillOval(490, 490, 20, 20);

        g2d.setPaint(Color.darkGray);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3,5}, 0));
        g2d.drawLine(0, 45, 1000,45);
        g2d.drawLine(0, 954, 1000,954);

        g2d.drawImage(Assets.RACKET, GameLogic.p1.getX(), GameLogic.p1.getY(), null);
        g2d.drawImage(Assets.RACKET, GameLogic.p2.getX(), GameLogic.p2.getY(), null);

        g2d.drawImage(Assets.BALL, GameLogic.ball.getX(), GameLogic.ball.getY(), null);

        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.format("%d - %d", GameLogic.p2.getScore(), GameLogic.p1.getScore()), 450, -775);
        g2d.setTransform(old_at);

        if(MyFrame.gameLogic.isFinished()){
            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
            g2d.drawString(MyFrame.gameLogic.getFinish(), 100, 525);
        }

        g2d.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A -> isLeft1Pressed = true;
            case KeyEvent.VK_D -> isRight1Pressed = true;
            case KeyEvent.VK_LEFT -> isLeft2Pressed = true;
            case KeyEvent.VK_RIGHT -> isRight2Pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A -> isLeft1Pressed = false;
            case KeyEvent.VK_D -> isRight1Pressed = false;
            case KeyEvent.VK_LEFT -> isLeft2Pressed = false;
            case KeyEvent.VK_RIGHT -> isRight2Pressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && MyFrame.gameLogic.isFinished()){
            MyFrame.gameLogic = new GameLogic();
        }
    }
}
