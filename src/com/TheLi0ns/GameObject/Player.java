package com.TheLi0ns.GameObject;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Powers.DefensivePowers.*;
import com.TheLi0ns.Powers.OffensivePowers.*;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

public class Player {
    private final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    private Image PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    protected int x;
    protected final int y;
    protected int width = PLAYER_IMAGE.getWidth(null);
    protected final int height = PLAYER_IMAGE.getHeight(null);
    protected int xVelocity;
    protected boolean isMovementEnabled = true;
    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;
    protected int score = 0;
    protected int hitPerRound = 0;
    private DefensivePowers_super defensivePower = null;
    private OffensivePowers_super offensivePower = null;

    protected boolean fireShotActivated = false;
    protected boolean smallBallActivated = false;
    protected boolean stretchBallActivated = false;
    private boolean areControlsInverted = false;
    private boolean isParrying = false;

    public enum Positions{UP, DOWN}
    private final Positions pos;

    public Player(int x, int y, int xVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.pos = y > 500 ? Positions.UP : Positions.DOWN;
    }

    protected void move(Directions direction){

        if(areControlsInverted){
            if(direction == Directions.LEFT) direction = Directions.RIGHT;
            else direction = Directions.LEFT;
        }

        switch (direction){
            case LEFT ->{
                if(x - xVelocity < GamePanel.LEFT_BOUND){
                    return;
                }
                x -= xVelocity;
            }

            case RIGHT ->{
                if(x + width + xVelocity > GamePanel.RIGHT_BOUND){
                    return;
                }
                x += xVelocity;
            }
        }
    }

    public void update(){
        if(isLeftPressed && isMovementEnabled){
            move(Directions.LEFT);
        }
        if(isRightPressed && isMovementEnabled){
            move(Directions.RIGHT);
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    private void updateWidth() {
        this.width = this.PLAYER_IMAGE.getWidth(null);
    }

    public int getHeight() {
        return height;
    }

    public Positions getPos(){
        return pos;
    }

    public Image getPLAYER_IMAGE() {
        return PLAYER_IMAGE;
    }

    public Image getNORMAL_PLAYER_IMAGE() {
        return NORMAL_PLAYER_IMAGE;
    }

    public void setPLAYER_IMAGE(Image PLAYER_IMAGE) {
        this.PLAYER_IMAGE = PLAYER_IMAGE;
        updateWidth();
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public boolean isMovementEnabled() {
        return isMovementEnabled;
    }

    public void setMovementEnabled(boolean movementEnabled) {
        isMovementEnabled = movementEnabled;
    }

    /**
     * Increases the counter of hit per round
     */
    public void hit(){
        hitPerRound++;
    }

    public void resetHitPerRound(){
        hitPerRound = 0;
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

    /**
     * Increase by 1 the score and play the score sound
     */
    public void hasScored(){
        score += 1;
        Sound.play(Sound.SCORE_SOUND);
    }

    public boolean hasWon(){
        return score == MyFrame.gameLogic.getPointsToWin();
    }

    /**
     * Increase by 1 the charge of the rechargeable powerUps
     */
    public void ChargingPowers(){
        defensivePower.charge();
        offensivePower.charge();
    }
    public DefensivePowers_super getDefensivePower() {
        return defensivePower;
    }

    public void setDefensivePower(DefensivePowersEnum defensivePower) {
        switch (defensivePower){
            case SPEED -> this.defensivePower = new DefensivePowerSpeed(this);
            case PARRY -> this.defensivePower = new DefensivePowerParry(this);
            case LARGE_RACKET -> this.defensivePower = new DefensivePowerLargeRacket(this);
            case LARGE_BALL -> this.defensivePower = new DefensivePowerLargeBall(this);
        }
    }

    public void activateDefensivePower(){
        defensivePower.activate();
    }

    public boolean isParrying() {
        return isParrying;
    }

    public void setParrying(boolean parrying) {
        isParrying = parrying;
    }

    public boolean isStretchBallActivated() {
        return stretchBallActivated;
    }

    public void setStretchBallActivated(boolean stretchBallActivated) {
        this.stretchBallActivated = stretchBallActivated;
    }

    public OffensivePowers_super getOffensivePower() {
        return offensivePower;
    }

    public void setOffensivePower(OffensivePowersEnum offensivePower, Player opponent){
        switch (offensivePower){
            case FIRE_SHOT -> this.offensivePower = new OffensivePowerFireShot(this, opponent);
            case SHRINK -> this.offensivePower = new OffensivePowerShrink(this, opponent);
            case INVERTED_CONTROLS -> this.offensivePower = new OffensivePowerInvertedControls(this, opponent);
            case SMALL_BALL -> this.offensivePower = new OffensivePowerSmallBall(this, opponent);
        }
    }

    public void activateOffensivePower(){
        offensivePower.activate();
    }

    public void setAreControlsInverted(boolean areControlsInverted) {
        this.areControlsInverted = areControlsInverted;
    }

    public boolean isFireShotActivated() {
        return fireShotActivated;
    }

    public void setFireShotActivated(boolean fireShotActivated) {
        this.fireShotActivated = fireShotActivated;
    }

    public boolean isSmallBallActivated() {
        return smallBallActivated;
    }

    public void setSmallBallActivated(boolean smallBallActivated) {
        this.smallBallActivated = smallBallActivated;
    }


    public void draw(Graphics2D g2d){
        g2d.drawImage(this.PLAYER_IMAGE, this.x, this.y, null);
        if(defensivePower != null) defensivePower.drawBattery(g2d);
        if(offensivePower != null) offensivePower.drawBattery(g2d);
    }
}
