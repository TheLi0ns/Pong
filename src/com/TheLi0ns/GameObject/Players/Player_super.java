package com.TheLi0ns.GameObject.Players;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

public abstract class Player_super {
    protected final Image NORMAL_PLAYER_IMAGE = Assets.RACKET;
    private Image PLAYER_IMAGE = NORMAL_PLAYER_IMAGE;
    protected int width = PLAYER_IMAGE.getWidth(null);
    protected final int height = PLAYER_IMAGE.getHeight(null);

    protected int x;
    protected final int y;
    protected int xVelocity;

    protected boolean isMovementEnabled = true;
    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    protected int score = 0;

    protected final Player_super.Positions pos;
    public enum Positions{UP, DOWN}

    public Player_super(int x, int y, int xVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.pos = y > GamePanel.CENTER ? Player_super.Positions.UP : Player_super.Positions.DOWN;
    }

    protected void move(Directions direction){
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

    public Player_super.Positions getPos(){
        return pos;
    }

    public Image getNORMAL_PLAYER_IMAGE() {
        return NORMAL_PLAYER_IMAGE;
    }

    public Image getPLAYER_IMAGE() {
        return PLAYER_IMAGE;
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

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.PLAYER_IMAGE, this.x, this.y, null);
    }
}
