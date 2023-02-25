package com.TheLi0ns.GameObject;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Utility.*;

import java.awt.*;

public class Ball {
    private final Image NORMAL_BALL_IMAGE = Assets.BALL;
    private final Image[] FIREBALL_IMAGES = Assets.FIRE_BALL;
    private final Image LARGE_BALL = Assets.LARGE_BALL;
    private Image BALL_IMAGE = NORMAL_BALL_IMAGE;
    protected Vector2D position;
    private int WIDTH = NORMAL_BALL_IMAGE.getWidth(null);
    private int HEIGHT = NORMAL_BALL_IMAGE.getHeight(null);
    protected Vector2D velocity;
    private boolean fireball = false;
    private boolean enlarged = false;

    public Ball(int xVelocity, int yVelocity){
        position = new Vector2D(472, 468);
        velocity = new Vector2D(xVelocity, yVelocity);
    }

    public Ball(int x, int y, int xVelocity, int yVelocity){
        position = new Vector2D(x, y);
        velocity = new Vector2D(xVelocity, yVelocity);
    }

    private void move(){
        //AntiBug
        if(velocity.getY() == 0) velocity.setY(position.getY() < GamePanel.CENTER ? 2 : -2);

        position.add(velocity);
    }

    protected void checkCollisions(Player... players){

        //WALL COLLISION
        if(Collisions.checkWallCollision(this)) {
            velocity.flipHorizontally();
            Sound.play(Sound.WALL_HIT_SOUND);
        }

        for (Player player : players) {
            checkPlayerCollision(player);
        }
    }

    protected void checkPlayerCollision(Player player){
        if (Collisions.checkBallPlayerCollision(player, this)) {

            speedUp();

            velocity.flipVertically();
            //FireBall orientation
            if(fireball) BALL_IMAGE = FIREBALL_IMAGES[velocity.getY() > 0 ? 1 : 0];

            player.setMovementEnabled(false);

            //Edge Collision
            if(Collisions.checkBallPlayerEdgeCollision(player, this)){
                edgeBounce(player);
            }

            //FireShot
            if (player.isFireShotActivated()) {
                fireShot(player);
            }
            //Deactivate LargeBall
            if(enlarged){
                enlarged = false;
                position.increment(new Vector2D(0, 30));
                BALL_IMAGE = this.NORMAL_BALL_IMAGE;
                updateWidthHeight();
                Sound.play(Sound.LARGE_SOUND[0]);
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

        velocity.increment(new Vector2D(0, 15));

        this.BALL_IMAGE = this.FIREBALL_IMAGES[this.velocity.getY() > 0 ? 1 : 0];

        Sound.play(Sound.FIRESHOT_SOUND);
        player.setFireShotActivated(false);
    }

    private void stretchBall(Player player){
        if((player.getY() < GamePanel.CENTER && this.velocity.getYDirection() == Directions.UP) ||
                (player.getY() > GamePanel.CENTER && this.velocity.getYDirection() == Directions.DOWN)){
            this.enlarged = true;
            this.position.increment(new Vector2D(0, -30));
            this.BALL_IMAGE = LARGE_BALL;
            updateWidthHeight();
            Sound.play(Sound.LARGE_SOUND[1]);
        }
    }

    protected void speedUp(){
        velocity.increment(new Vector2D(1, 1));
    }

    public void update(Player...players){
        checkCollisions(players);
        move();

        for(Player p : players){
            if(p.isStretchBallActivated()){
                stretchBall(p);
                p.setStretchBallActivated(false);
            }
        }
    }

    /**
     * @return UP if the ball scored up,
     *         DOWN if the ball scored down,
     *         IN if the ball not scored
     */
    public String checkScored(){
        if(position.getY() < -HEIGHT){
            return "UP";
        }else if(position.getY() > GamePanel.HEIGHT){
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
    private void updateWidthHeight(){
        WIDTH = BALL_IMAGE.getWidth(null);
        HEIGHT = BALL_IMAGE.getHeight(null);
    }
    public int getxVelocity() {
        return velocity.getX();
    }
    public int getyVelocity() {
        return velocity.getY();
    }
    public Vector2D getVelocity(){return velocity;}

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.BALL_IMAGE, position.getX(), position.getY(), null);
    }

    public boolean fell(){return false;}
}