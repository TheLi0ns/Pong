import java.util.Random;

public class GameLogic implements Runnable{

    static Player p1;
    static Player p2;
    static Ball ball;

    static int pointToWin = 10;
    final int FPS = 60;

    boolean isFinished = false;

    String finish = null;

    GameLogic(){
        p1 = new Player(391, 909, 6, 218, 36);
        p2 = new Player(391, 54, 6, 218, 36);
        ball = new Ball(472, 468, genRandomxVelocity(), 5, 56, 64);
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
        while(!hasSomeoneWin()) {
            update();

            MyFrame.gamePanel.repaint();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update(){
        if(GamePanel.isLeft1Pressed && p1.isMovementEnabled()){
            p1.move(Player.DIRECTION.LEFT);
        }
        if(GamePanel.isRight1Pressed && p1.isMovementEnabled()){
            p1.move(Player.DIRECTION.RIGHT);
        }
        if(GamePanel.isLeft2Pressed && p2.isMovementEnabled()){
            p2.move(Player.DIRECTION.LEFT);
        }
        if(GamePanel.isRight2Pressed && p2.isMovementEnabled()){
            p2.move(Player.DIRECTION.RIGHT);
        }

        ball.checkCollisions(p1, p2);
        ball.move();

        switch(ball.checkScored()){
            case "UP" -> {
                p1.hasScored();
                if(!hasSomeoneWin()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), 5, 56, 64);
                }else{
                    finish();
                }
            }
            case "DOWN" -> {
                p2.hasScored();
                if(!hasSomeoneWin()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), -5, 56, 64);
                }else{
                    finish();
                }
            }
        }
    }

    public boolean hasSomeoneWin(){
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
        return random.nextInt(3, 8) * (random.nextBoolean() ? 1 : -1);
    }
}
