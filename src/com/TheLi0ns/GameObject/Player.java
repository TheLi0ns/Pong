package com.TheLi0ns.GameObject;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Powers.DefensivePowers.*;
import com.TheLi0ns.Powers.OffensivePowers.*;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

public class Player {
    private final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    private Image PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    private int x;
    private final int y;
    private int width = PLAYER_IMAGE.getWidth(null);
    private final int height = PLAYER_IMAGE.getHeight(null);
    private int xVelocity;
    private boolean isMovementEnabled = true;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private int score = 0;
    private boolean arePowersEnabled = true;
    private DefensivePowers_super defensivePower;
    private OffensivePowers_super offensivePower;
    private int chargeDefensivePower = 0;
    private int chargeOffensivePower = 0;
    private boolean fireShotActivated = false;
    private boolean areControlsInverted = false;
    private boolean isParrying = false;

    enum DIRECTION{
        LEFT,
        RIGHT
    }

    public Player(int x, int y, int xVelocity){
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

    public void update(){
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
