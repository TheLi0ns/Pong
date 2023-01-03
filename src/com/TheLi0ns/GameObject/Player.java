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
    protected final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    protected Image PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    protected int x;
    protected final int y;
    protected int width = PLAYER_IMAGE.getWidth(null);
    protected final int height = PLAYER_IMAGE.getHeight(null);
    protected int xVelocity;
    protected boolean isMovementEnabled = true;
    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;
    protected int score = 0;
    protected boolean arePowersEnabled = true;
    protected DefensivePowers_super defensivePower = null;
    protected OffensivePowers_super offensivePower = null;

    /**
     * The powers have to be charged 1 goal 1 charge
     * this is the charge of the defensive power
     * it is charged at 3
     */
    protected int chargeDefensivePower = 0;

    /**
     * The powers have to be charged 1 goal 1 charge
     * this is the charge of the offensive power
     * it is charged at 5
     */
    protected int chargeOffensivePower = 0;

    protected boolean fireShotActivated = false;
    protected boolean areControlsInverted = false;
    protected boolean isParrying = false;

    public Player(int x, int y, int xVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
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

    public void updateWidth() {
        this.width = this.PLAYER_IMAGE.getWidth(null);
    }

    public int getHeight() {
        return height;
    }

    public Image getNORMAL_PLAYER_IMAGE() {
        return NORMAL_PLAYER_IMAGE;
    }

    public void setPLAYER_IMAGE(Image PLAYER_IMAGE) {
        this.PLAYER_IMAGE = PLAYER_IMAGE;
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
        if(!isDefensivePowerCharged() && MyFrame.gameLogic.isDefensivePowerRechargeable()) {
            chargeDefensivePower++;
        }
        if(!isOffensivePowerCharged() && MyFrame.gameLogic.isOffensivePowerRechargeable()) {
            chargeOffensivePower++;
        }
    }
    public DefensivePowers_super getDefensivePower() {
        return defensivePower;
    }

    public void setDefensivePower(DefensivePowersEnum defensivePower) {
        switch (defensivePower){
            case SPEED -> this.defensivePower = new DefensivePowerSpeed(this);
            case PARRY -> this.defensivePower = new DefensivePowerParry(this);
            case LARGE_RACKET -> this.defensivePower = new DefensivePowerLargeRacket(this);
        }
    }
    public boolean isDefensivePowerCharged(){
        return chargeDefensivePower == 3;
    }

    public int getChargeDefensivePower() {
        return chargeDefensivePower;
    }

    public void activateDefensivePower(){
        if(isDefensivePowerCharged() && arePowersEnabled){
            chargeDefensivePower = -2;

            defensivePower.activate();
        }
    }

    public boolean isParrying() {
        return isParrying;
    }

    public void setParrying(boolean parrying) {
        isParrying = parrying;
    }

    public OffensivePowers_super getOffensivePower() {
        return offensivePower;
    }

    public void setOffensivePower(OffensivePowersEnum offensivePower, Player opponent){
        switch (offensivePower){
            case FIRE_SHOT -> this.offensivePower = new OffensivePowerFireShot(this, opponent);
            case SHRINK -> this.offensivePower = new OffensivePowerShrink(this, opponent);
            case INVERTED_CONTROLS -> this.offensivePower = new OffensivePowerInvertedControls(this, opponent);
        }
    }

    public boolean isOffensivePowerCharged(){
        return chargeOffensivePower == 6;
    }

    public int getChargeOffensivePower() {
        return chargeOffensivePower;
    }

    public void activateOffensivePower(){
        if(isOffensivePowerCharged() && arePowersEnabled){
            chargeOffensivePower = -2;

            offensivePower.activate();
        }
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

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.PLAYER_IMAGE, this.x, this.y, null);
    }
}
