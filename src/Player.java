import java.awt.*;

public class Player {
    private int x;
    private final int y;
    private final int width;
    private final int height;
    private int xVelocity;
    private boolean isMovementEnabled = true;
    private int score = 0;
    private int chargeSpeedPowerUp = 0;
    private int chargeFireShotPowerUp = 0;
    private boolean fireShotActivated = false;

    static class DIRECTION{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
    }

    Player(int x, int y, int xVelocity, int width, int height){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.width = width;
        this.height = height;
    }

    void move(int direction){
        switch (direction){
            case 0 ->{
                if(x - xVelocity < 5){
                    return;
                }
                x -= xVelocity;
            }

            case 1 ->{
                if(x + 218 + xVelocity > 995){
                    return;
                }
                x += xVelocity;
            }
        }
    }

    public Rectangle getHitbox(){
        return new Rectangle(x, y, width, height);
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMovementEnabled() {
        return isMovementEnabled;
    }

    public void setMovementEnabled(boolean movementEnabled) {
        isMovementEnabled = movementEnabled;
    }

    public int getScore() {
        return score;
    }

    public void hasScored(){
        score += 1;
    }

    public boolean hasWon(){
        return score == GameLogic.pointToWin;
    }

    public void ChargingPowerUps(){
        if(!isSpeedPowerUpCharged()) {
            chargeSpeedPowerUp++;
        }
        if(!isFireShotPowerUpCharged()) {
            chargeFireShotPowerUp++;
        }
    }
    public boolean isSpeedPowerUpCharged(){
        return chargeSpeedPowerUp == 3;
    }

    public void activateSpeedPowerUp(){
        chargeSpeedPowerUp = -2;
        new Thread(() -> {
            int initialxVelocity = xVelocity;
            xVelocity = 15;
            Sound.play(Sound.SPEED_SOUND[1]);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            xVelocity = initialxVelocity;
            Sound.play(Sound.SPEED_SOUND[0]);
        }).start();
    }

    public int getChargeSpeedPowerUp() {
        return chargeSpeedPowerUp;
    }

    public boolean isFireShotPowerUpCharged(){
        return chargeFireShotPowerUp == 6;
    }

    public void activateFireShotPowerUp(){
        chargeFireShotPowerUp = -2;
        new Thread(() -> {
            fireShotActivated = true;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fireShotActivated = false;
        }).start();
    }

    public int getChargeFireShotPowerUp() {
        return chargeFireShotPowerUp;
    }

    public boolean isFireShotActivated() {
        return fireShotActivated;
    }

    public void setFireShotActivated(boolean fireShotActivated) {
        this.fireShotActivated = fireShotActivated;
    }
}
