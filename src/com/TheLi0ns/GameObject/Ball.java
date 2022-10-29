package com.TheLi0ns.GameObject;

import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Collisions;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

public class Ball {
    private final Image BALL_IMAGE = Assets.BALL;
    private final Image[] FIREBALL_IMAGES = Assets.FIRE_BALL;
    private int x;
    private int y;
    private final int WIDTH = BALL_IMAGE.getWidth(null);
    private final int HEIGHT = BALL_IMAGE.getHeight(null);
    private int xVelocity;
    private int yVelocity;
    private boolean fireball = false;

    public Ball(int x, int y, int xVelocity, int yVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    private void move(){
        if(yVelocity == 0){
            if(y < 500){
                yVelocity = 2;
            }else {
                yVelocity = -2;
            }
        }
        x += xVelocity;
        y += yVelocity;
    }

    private void checkCollisions(Player p1, Player p2){

        //WALL COLLISION
        if(Collisions.checkWallCollision(this)) {
            xVelocity *= -1;
            Sound.play(Sound.WALL_HIT_SOUND);
        }

        checkPlayerCollision(p1);

        checkPlayerCollision(p2);
    }

    private void checkPlayerCollision(Player player){
        if (Collisions.checkBallPlayerCollision(player, this)) {

            speedUp();

            yVelocity *= -1;

            player.setMovementEnabled(false);

            //Edge Collision
            if(Collisions.checkBallPlayerEdgeCollision(player, this)){
                edgeBounce(player);
            }

            //FireShot
            if (player.isFireShotActivated()) {
                fireShot(player);
            }
            //Parry
            else if(player.isParrying()){
                Sound.play(Sound.PARRY_SOUND);
                player.setParrying(false);
            } else {
                Sound.play(Sound.PLAYER_HIT_SOUND);
            }

            //AntiBug
            if(player.getY() > 500){
                this.y = player.getY()-1- HEIGHT;
            }else {
                this.y = player.getY() + player.getHeight() + 1;
            }
        }else if(!player.isMovementEnabled()){
            player.setMovementEnabled(true);
        }
    }

    /**
     * Update the x velocity after the collision
     * with the edge of the racket
     * @param player player who collided
     */
    private void edgeBounce(Player player) {
        if(xVelocity > 0){
            xVelocity += 2;
        }else {
            xVelocity -= 2;
        }

        if((x > (player.getX() + 100) && xVelocity < 0) || (x < (player.getX() + player.getWidth() - 100) && xVelocity > 0)){
            xVelocity *= -1;
        }
    }

    /**
     * Update the xVelocity and the yVelocity to make
     * the ball faster and straighter
     * also plays the sound
     * @param player player who used the fireshot powerup
     */
    private void fireShot(Player player){
        this.fireball = true;

        if(xVelocity > 0){
            xVelocity = 2;
        }else xVelocity = -2;

        if(yVelocity > 0){
            yVelocity += 15;
        }else yVelocity -= 15;

        Sound.play(Sound.FIRESHOT_SOUND);
        player.setFireShotActivated(false);
    }

    private void speedUp(){
        if(yVelocity > 0){
            yVelocity++;
        }else yVelocity--;

        if(xVelocity > 0){
            xVelocity++;
        }else xVelocity--;
    }

    public void update(Player p1, Player p2){
        checkCollisions(p1, p2);
        move();
    }

    /**
     * @return UP if the ball scored up,
     *         DOWN if the ball scored down,
     *         IN if the ball not scored
     */
    public String checkScored(){
        if(y < -HEIGHT){
            return "UP";
        }else if(y > 1000){
            return "DOWN";
        }
        else return "IN";
    }

    public Rectangle getHitbox(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void draw(Graphics2D g2d){
        if(fireball){
            g2d.drawImage(this.FIREBALL_IMAGES[yVelocity>0 ? 1 : 0], this.x, this.y, null);
        }else g2d.drawImage(this.BALL_IMAGE, this.x, this.y, null);
    }
}