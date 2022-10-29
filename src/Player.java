import java.awt.*;

public class Player {
    private final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    private final Image SPEEDY_PLAYER_IMAGE = Assets.SPEEDY_RACKET;
    private final Image LARGE_PLAYER_IMAGE = Assets.LARGE_RACKET;
    private final Image INVERTED_PLAYER_IMAGE = Assets.INVERTED_RACKET;
    private final Image SHRUNK_PLAYER_IMAGE = Assets.SHRUNK_RACKET;
    private final Image PARRY_IMAGE = Assets.PARRY_RACKET;
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
    private boolean arePowersEnabled = true;
    private DefensivePowerUps defensivePowerUp;
    private OffensivePowerUps offensivePowerup;
    private int chargeDefensivePowerUp = 0;
    private int chargeOffensivePowerUp = 0;
    private boolean fireShotActivated = false;
    private boolean isParrying = false;
    private boolean areControlsInverted = false;
    private boolean isRacketShrinked = false;

    enum DIRECTION{
        LEFT,
        RIGHT
    }

    enum OffensivePowerUps {
        /**
         * Increase the ball speed
         * after the collision
         */
        FIRE_SHOT("FIRE SHOT"),

        /**
         * Inverts the controls
         * of the opponent
         */
        INVERTED_CONTROLS("INVERTED"),

        /**
         * Shrinks the opponent racket
         */
        SHRINK("SHRINK");

        String name;

        OffensivePowerUps(String name){
            this.name = name;
        }

        static OffensivePowerUps powerNamed(String name){
            for(OffensivePowerUps i : OffensivePowerUps.values()){
                if(name.equals(i.name)) return i;
            }
            return null;
        }
    }

    enum DefensivePowerUps {
        /**
         * Increase the xVelocity
         */
        SPEED("SPEED"),

        /**
         * Stretches the racket
         */
        LARGE_RACKET("LARGE RACKET"),

        /**
         * Make the racket block any shot for 200ms
         */
        PARRY("PARRY");

        String name;

        DefensivePowerUps(String name){
            this.name = name;
        }

        static DefensivePowerUps powerNamed(String name){
            for(DefensivePowerUps i : DefensivePowerUps.values()){
                if(name.equals(i.name)) return i;
            }
            return null;
        }
    }

    Player(int x, int y, int xVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
    }

    public void setArePowersEnabled(boolean arePowersEnabled) {
        this.arePowersEnabled = arePowersEnabled;
    }

    private void move(DIRECTION direction){

        if(areControlsInverted){
            if(direction == DIRECTION.LEFT) direction = DIRECTION.RIGHT;
            else direction = DIRECTION.LEFT;
        }

        switch (direction){
            case LEFT ->{
                if(x - xVelocity < 5){
                    return;
                }
                x -= xVelocity;
            }

            case RIGHT ->{
                if(x + width + xVelocity > 995){
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
        Sound.play(Sound.SCORE_SOUND);
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
    public DefensivePowerUps getDefensivePowerUp() {
        return defensivePowerUp;
    }

    public void setDefensivePowerUp(DefensivePowerUps defensivePowerUp) {
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
                case PARRY -> activateParryPowerUp();
            }
        }
    }

    /**
     * Make the racket block any shot for 200ms
     */
    private void activateParryPowerUp(){
        new Thread(() -> {
            isParrying = true;
            PLAYER_IMAGE = PARRY_IMAGE;
            width = PLAYER_IMAGE.getWidth(null);
            int x_tmp = x;
            x = 0;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
            width = NORMAL_PLAYER_IMAGE.getWidth(null);
            x = x_tmp;
            isParrying = false;
        }).start();
    }

    public boolean isParrying() {
        return isParrying;
    }

    public void setParrying(boolean parrying) {
        isParrying = parrying;
    }

    /**
     * Increase the xVelocity for 7 secs
     */
    private void activateSpeedPowerUp(){
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

    /**
     * Stretches the racket for 7 secs
     */
    private void activateLargeRacketPowerUp(){
        new Thread(() -> {
            PLAYER_IMAGE = LARGE_PLAYER_IMAGE;
            Sound.play(Sound.LARGE_SOUND[1]);
            this.width = LARGE_PLAYER_IMAGE.getWidth(null);
            x -= 35;
            if(x < 0){
                x = 0;
            }else if(x+width > 1000){
                x = 1000-width;
            }

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            x += 35;
            PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
            Sound.play(Sound.LARGE_SOUND[0]);
            this.width = NORMAL_PLAYER_IMAGE.getWidth(null);
        }).start();
    }

    public OffensivePowerUps getOffensivePowerup() {
        return offensivePowerup;
    }

    public void setOffensivePowerup(OffensivePowerUps offensivePowerup) {
        this.offensivePowerup = offensivePowerup;
    }


    public boolean isOffensivePowerUpCharged(){
        return chargeOffensivePowerUp == 6;
    }

    public int getChargeOffensivePowerUp() {
        return chargeOffensivePowerUp;
    }

    public void activateOffensivePowerUp(Player opponent){
        if(isOffensivePowerUpCharged() && arePowersEnabled){
            chargeOffensivePowerUp = -2;

            switch (offensivePowerup){
                case FIRE_SHOT -> activateFireShotPowerUp();
                case INVERTED_CONTROLS -> activateInvertedControlsPowerUp(opponent);
                case SHRINK -> activateShrinkPowerUp(opponent);
            }
        }
    }

    /**
     * Shrinks the opponent racket for 7 secs
     */
    private void activateShrinkPowerUp(Player opponent){
        opponent.setRacketShrinked(true);
        new Thread(() -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            opponent.setRacketShrinked(false);
        }).start();
    }

    public void setRacketShrinked(boolean racketShrinked) {
        isRacketShrinked = racketShrinked;

        if(racketShrinked){
            PLAYER_IMAGE = SHRUNK_PLAYER_IMAGE;
            width = SHRUNK_PLAYER_IMAGE.getWidth(null);
            x += 35;
            Sound.play(Sound.SHRINK_SOUND[1]);
        }
        else{
            PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
            width = NORMAL_PLAYER_IMAGE.getWidth(null);
            x -= 35;
            Sound.play(Sound.SHRINK_SOUND[0]);
        }

        if(x > 1000){
            x = 1000;
        }else if(x < 0){
            x = 0;
        }

        width = PLAYER_IMAGE.getWidth(null);
    }

    /**
     * Inverts the opponent controls for 7 secs
     */
    private void activateInvertedControlsPowerUp(Player opponent){
        opponent.setAreControlsInverted(true);
        Sound.play(Sound.INVERTED_CONTROLS_SOUND);
        new Thread(() -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            opponent.setAreControlsInverted(false);
        }).start();
    }

    public void setAreControlsInverted(boolean areControlsInverted) {
        this.areControlsInverted = areControlsInverted;

        if(areControlsInverted) PLAYER_IMAGE = INVERTED_PLAYER_IMAGE;
        else PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    }

    /**
     * Make the fireshot active for 5 secs
     */
    private void activateFireShotPowerUp(){
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

    public boolean isFireShotActivated() {
        return fireShotActivated;
    }

    public void setFireShotActivated(boolean fireShotActivated) {
        this.fireShotActivated = fireShotActivated;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.PLAYER_IMAGE, this.x, this.y, null);
    }
}
