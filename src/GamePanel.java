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

        drawField(g2d);

        drawPowerUpP1(g2d);

        drawPowerUpP2(g2d);

        drawGameObjects(g2d);

        drawScore(g2d);

        if(MyFrame.gameLogic.isFinished()){
            drawFinishScreen(g2d);
        }

        g2d.dispose();
    }

    void drawField(Graphics2D g2d){
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
    }

    void drawPowerUpP1(Graphics2D g2d){

        //Speed PowerUp
        g2d.setPaint(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(50, 820, 70, 125);
        g2d.drawLine(50, 860, 120, 860);
        g2d.drawLine(50, 900, 120, 900);

        g2d.setPaint(Color.GREEN);
        if(GameLogic.p1.getChargeSpeedPowerUp() > 0){
            g2d.fillRect(55, 905, 60, 35);
        }
        if(GameLogic.p1.getChargeSpeedPowerUp() > 1){
            g2d.fillRect(55, 865, 60, 30);
        }
        if(GameLogic.p1.getChargeSpeedPowerUp() > 2){
            g2d.fillRect(55, 825, 60,30);
            g2d.setPaint(Color.CYAN);
            g2d.drawRect(45, 815, 80, 135);
        }

        //FireShot PowerUp
        g2d.setPaint(Color.gray);
        g2d.drawRect(880, 820, 70, 125);
        g2d.drawLine(880, 860, 950, 860);
        g2d.drawLine(880, 900, 950, 900);

        g2d.setPaint(Color.GREEN);
        if(GameLogic.p1.getChargeFireShotPowerUp() > 0){
            g2d.fillRect(885, 905, 30, 35);
        }
        if(GameLogic.p1.getChargeFireShotPowerUp() > 1){
            g2d.fillRect(915, 905, 30, 35);
        }
        if(GameLogic.p1.getChargeFireShotPowerUp() > 2){
            g2d.fillRect(885, 865, 30, 30);
        }
        if(GameLogic.p1.getChargeFireShotPowerUp() > 3){
            g2d.fillRect(915, 865, 30, 30);
        }
        if(GameLogic.p1.getChargeFireShotPowerUp() > 4){
            g2d.fillRect(885, 825, 30,30);
        }
        if(GameLogic.p1.getChargeFireShotPowerUp() > 5){
            g2d.fillRect(915, 825, 30, 30);
            g2d.setPaint(Color.RED);
            g2d.drawRect(875, 815, 80, 135);
        }
    }

    void drawPowerUpP2(Graphics2D g2d){

        //Speed PowerUp
        g2d.setPaint(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(880, 55, 70, 125);
        g2d.drawLine(880, 95, 950, 95);
        g2d.drawLine(885, 135, 950, 135);

        g2d.setPaint(Color.GREEN);
        if(GameLogic.p2.getChargeSpeedPowerUp() > 0){
            g2d.fillRect(885, 60, 60, 30);
        }
        if(GameLogic.p2.getChargeSpeedPowerUp() > 1){
            g2d.fillRect(885, 100, 60, 30);
        }
        if(GameLogic.p2.getChargeSpeedPowerUp() > 2){
            g2d.fillRect(885, 140, 60,35);
            g2d.setPaint(Color.CYAN);
            g2d.drawRect(875, 50, 80, 135);
        }

        //FireShot PowerUp
        g2d.setPaint(Color.gray);
        g2d.drawRect(50, 55, 70, 125);
        g2d.drawLine(50, 95, 120, 95);
        g2d.drawLine(50, 135, 120, 135);

        g2d.setPaint(Color.GREEN);
        if(GameLogic.p2.getChargeFireShotPowerUp() > 0){
            g2d.fillRect(55, 60, 30, 30);
        }
        if(GameLogic.p2.getChargeFireShotPowerUp() > 1){
            g2d.fillRect(85, 60, 30, 30);
        }
        if(GameLogic.p2.getChargeFireShotPowerUp() > 2){
            g2d.fillRect(55, 100, 30, 30);
        }
        if(GameLogic.p2.getChargeFireShotPowerUp() > 3){
            g2d.fillRect(85, 100, 30, 30);
        }
        if(GameLogic.p2.getChargeFireShotPowerUp() > 4){
            g2d.fillRect(55, 140, 30,35);
        }
        if(GameLogic.p2.getChargeFireShotPowerUp() > 5){
            g2d.fillRect(85, 140, 30, 35);
            g2d.setPaint(Color.RED);
            g2d.drawRect(45, 50, 80, 135);
        }
    }

    void drawGameObjects(Graphics2D g2d){
        g2d.drawImage(Assets.RACKET, GameLogic.p1.getX(), GameLogic.p1.getY(), null);
        g2d.drawImage(Assets.RACKET, GameLogic.p2.getX(), GameLogic.p2.getY(), null);

        g2d.drawImage(Assets.BALL, GameLogic.ball.getX(), GameLogic.ball.getY(), null);
    }

    void drawScore(Graphics2D g2d){
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.format("%d - %d", GameLogic.p2.getScore(), GameLogic.p1.getScore()), 450, -775);
        g2d.setTransform(old_at);
    }

    void drawFinishScreen(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        g2d.drawString(MyFrame.gameLogic.getFinish(), 100, 525);
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

        if(e.getKeyCode() == KeyEvent.VK_S && GameLogic.p1.isSpeedPowerUpCharged()){
            GameLogic.p1.activateSpeedPowerUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_W && GameLogic.p1.isFireShotPowerUpCharged()){
            GameLogic.p1.activateFireShotPowerUp();
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN && GameLogic.p2.isSpeedPowerUpCharged()){
            GameLogic.p2.activateSpeedPowerUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && GameLogic.p2.isFireShotPowerUpCharged()){
            GameLogic.p2.activateFireShotPowerUp();
        }
    }
}
