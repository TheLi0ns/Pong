package com.TheLi0ns.GameObject;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Collisions;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Vector2D;

import java.awt.*;

public class Ball {
    private final Image BALL_IMAGE = Assets.BALL;
    private final Image[] FIREBALL_IMAGES = Assets.FIRE_BALL;
    private Vector2D position;
    private final int WIDTH = BALL_IMAGE.getWidth(null);
    private final int HEIGHT = BALL_IMAGE.getHeight(null);
    private Vector2D velocity;
    private boolean fireball = false;

    public Ball(int x, int y, int xVelocity, int yVelocity){
        position = new Vector2D(x, y);
        velocity = new Vector2D(xVelocity, yVelocity);
    }

    private void move(){
        //AntiBug
        if(velocity.getY() == 0) velocity.setY(position.getY() < GamePanel.CENTER ? 2 : -2);

        position.add(velocity);
    }

    private void checkCollisions(Player p1, Player p2){

        //WALL COLLISION
        if(Collisions.checkWallCollision(this)) {
            velocity.flipHorizontally();
            Sound.play(Sound.WALL_HIT_SOUND);
        }

        checkPlayerCollision(p1);

        checkPlayerCollision(p2);
    }

    private void checkPlayerCollision(Player player){
        if (Collisions.checkBallPlayerCollision(player, this)) {

            speedUp();

            velocity.flipVertically();

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
            if(player.getY() > GamePanel.CENTER){
                position.setY(player.getY()-1- HEIGHT);
            }else {
                position.setY(player.getY() + player.getHeight() + 1);
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
        velocity.increment(new Vector2D(2, 0));

        if((position.getX() > (player.getX() + 100) && velocity.getX() < 0) ||
                (position.getX() < (player.getX() + player.getWidth() - 100) && velocity.getX() > 0)){
            velocity.flipHorizontally();
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

        velocity.setX(velocity.getX() > 0 ? 2 : -2);

        velocity.add(new Vector2D(0, 15));

        Sound.play(Sound.FIRESHOT_SOUND);
        player.setFireShotActivated(false);
    }

    private void speedUp(){
        velocity.increment(new Vector2D(1, 1));
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
        if(position.getY() < -HEIGHT){
            return "UP";
        }else if(position.getY() > 1000){
            return "DOWN";
        }
        else return "IN";
    }

    public Rectangle getHitbox(){
        return new Rectangle(position.getX(), position.getY(), WIDTH, HEIGHT);
    }

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Vector2D getPosition(){return position;}
    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }
    public int getxVelocity() {
        return velocity.getX();
    }
    public int getyVelocity() {
        return velocity.getY();
    }
    public Vector2D getVelocity(){return velocity;}

    public void draw(Graphics2D g2d){
        if(fireball){
            g2d.drawImage(this.FIREBALL_IMAGES[velocity.getY() > 0 ? 1 : 0], position.getX(), position.getY(), null);
        }else g2d.drawImage(this.BALL_IMAGE, position.getX(), position.getY(), null);
    }
}