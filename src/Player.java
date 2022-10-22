import java.awt.*;

public class Player {
    private final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    private final Image SPEEDY_PLAYER_IMAGE = Assets.SPEEDY_RACKET;
    private Image PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    private int x;
    private final int y;
    private int width = PLAYER_IMAGE.getWidth(null);
    private int height = PLAYER_IMAGE.getHeight(null);
    private int xVelocity;
    private boolean isMovementEnabled = true;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private int score = 0;
    private boolean arePowersEnabled;
    private DEFENSIVE_POWERUPS defensivePowerUp;
    private OFFENSIVE_POWERUPS offensivePowerup;
    private int chargeDefensivePowerUp = 0;
    private int chargeOffensivePowerUp = 0;
    private boolean fireShotActivated = false;

    enum DIRECTION{
        LEFT,
        RIGHT
    }

    enum OFFENSIVE_POWERUPS{
        FIRE_SHOT,
        INVERTED_CONTROLS
    }

    enum DEFENSIVE_POWERUPS{
        SPEED,
        LARGE_RACKET
    }

    Player(int x, int y, int xVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
    }

    public void setArePowersEnabled(boolean arePowersEnabled) {
        this.arePowersEnabled = arePowersEnabled;
    }

    void move(DIRECTION direction){
        switch (direction){
            case LEFT ->{
                if(x - xVelocity < 5){
                    return;
                }
                x -= xVelocity;
            }

            case RIGHT ->{
                if(x + 218 + xVelocity > 995){
                    return;
                }
                x += xVelocity;
            }
        }
    }

    void update(){
        if(isLeftPressed && isMovementEnabled){
            move(DIRECTION.LEFT);
        }
        if(isRightPressed && isMovementEnabled){
            move(DIRECTION.RIGHT);
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

    public void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        isRightPressed = rightPressed;
    }

    public int getScore() {
        return score;
    }

    public void hasScored(){
        score += 1;
    }

    public boolean hasWon(){
        return score == MyFrame.gameLogic.getPointToWin();
    }

    /**
     * Increase by 1 the charge of the rechargeable powerUps
     */
    public void ChargingPowerUps(){
        if(!isDefensivePowerUpCharged() && MyFrame.gameLogic.isDefensivePowerRechargeable()) {
            chargeDefensivePowerUp++;
        }
        if(!isOffensivePowerUpCharged() && MyFrame.gameLogic.isOffensivePowerRechargeable()) {
            chargeOffensivePowerUp++;
        }
    }
    public DEFENSIVE_POWERUPS getDefensivePowerUp() {
        return defensivePowerUp;
    }

    public void setDefensivePowerUp(DEFENSIVE_POWERUPS defensivePowerUp) {
        this.defensivePowerUp = defensivePowerUp;
    }
    public boolean isDefensivePowerUpCharged(){
        return chargeDefensivePowerUp == 3;
    }

    public int getChargeDefensivePowerUp() {
        return chargeDefensivePowerUp;
    }

    public void activateDefensivePowerUp(){
        if(isDefensivePowerUpCharged() && arePowersEnabled){
            chargeDefensivePowerUp = -2;

            switch (defensivePowerUp){
                case SPEED -> activateSpeedPowerUp();
                case LARGE_RACKET -> activateLargeRacketPowerUp();
            }
        }
    }

    /**
     * Increase the xVelocity for 7 secs
     */
    public void activateSpeedPowerUp(){
        new Thread(() -> {
            PLAYER_IMAGE = SPEEDY_PLAYER_IMAGE;
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
            PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
        }).start();
    }

    public void activateLargeRacketPowerUp(){
        //ToDo
    }

    public OFFENSIVE_POWERUPS getOffensivePowerup() {
        return offensivePowerup;
    }

    public void setOffensivePowerup(OFFENSIVE_POWERUPS offensivePowerup) {
        this.offensivePowerup = offensivePowerup;
    }


    public boolean isOffensivePowerUpCharged(){
        return chargeOffensivePowerUp == 6;
    }

    public int getChargeOffensivePowerUp() {
        return chargeOffensivePowerUp;
    }

    public boolean isFireShotActivated() {
        return fireShotActivated;
    }

    public void setFireShotActivated(boolean fireShotActivated) {
        this.fireShotActivated = fireShotActivated;
    }

    public void activateOffensivePowerUp(){
        if(isDefensivePowerUpCharged() && arePowersEnabled){
            chargeOffensivePowerUp = -2;

            switch (offensivePowerup){
                case FIRE_SHOT -> activateFireShotPowerUp();
                case INVERTED_CONTROLS -> activateInvertedControlsPowerUp();
            }
        }
    }

    public void activateInvertedControlsPowerUp(){
        //ToDo
    }

    /**
     * Make the fireshot active for 5 secs
     */
    public void activateFireShotPowerUp(){
        fireShotActivated = true;
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fireShotActivated = false;
        }).start();
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.PLAYER_IMAGE, this.x, this.y, null);
    }
}
