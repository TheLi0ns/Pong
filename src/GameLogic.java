import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameLogic implements Runnable{

    Player p1;
    Player p2;
    Ball ball;

    private int pointToWin = 15;

    private final int FPS = 90;

    private boolean isFinished = false;
    private boolean isPaused = false;

    private boolean arePowersEnabled = true;
    private final boolean isSpeedPowerRechargeable;
    private final boolean isFireShotPowerRechargeable;

    String finish = null;

    GameLogic(){
        setPointToWin();

        isFireShotPowerRechargeable = pointToWin > 6;
        isSpeedPowerRechargeable = pointToWin > 3;

        p1 = new Player(391, 909, 6, 218, 36);
        p2 = new Player(391, 54, 6, 218, 36);
        ball = new Ball(472, 468, genRandomxVelocity(), 6, 56, 64);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!hasSomeoneWins()) {
            if(!isPaused) {
                update();
            }

            MyFrame.gamePanel.repaint();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update(){

        p1.update();

        p2.update();

        ball.update(p1, p2);

        scoreUpdate();
    }

    private void scoreUpdate(){
        switch(ball.checkScored()){
            case "UP" -> {
                p1.hasScored();
                if(arePowersEnabled) p2.ChargingPowerUps();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), 5, 56, 64);
                }else{
                    finish();
                }
            }
            case "DOWN" -> {
                p2.hasScored();
                if(arePowersEnabled) p1.ChargingPowerUps();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), -5, 56, 64);
                }else{
                    finish();
                }
            }
        }
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean hasSomeoneWins(){
        return p1.hasWon() || p2.hasWon();
    }

    public void finish(){
        isFinished = true;
        if(p1.hasWon()){
            finish = "PLAYER 1\n  WINS";
        }else{
            finish = "PLAYER 2\n  WINS";
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getFinish() {
        return finish;
    }

    private int genRandomxVelocity(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(3, 5) * (random.nextBoolean() ? 1 : -1);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean arePowersEnabled() {
        return arePowersEnabled;
    }

    public void togglePowers() {
        this.arePowersEnabled = !this.arePowersEnabled;
    }

    public void setPointToWin(){
        togglePause();

        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(30);
        slider.setValue(1);
        slider.setPreferredSize(new Dimension(750, 100));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setFont(new Font("CALIBRI CORPO", Font.PLAIN, 15));
        slider.setValue(10);

        JOptionPane.showConfirmDialog(null, slider, "   POINTS TO WIN    ", JOptionPane.DEFAULT_OPTION);
        pointToWin = slider.getValue();

        togglePause();
    }

    public int getPointToWin() {
        return pointToWin;
    }

    public boolean isSpeedPowerRechargeable() {
        return isSpeedPowerRechargeable;
    }

    public boolean isFireShotPowerRechargeable() {
        return isFireShotPowerRechargeable;
    }

}
