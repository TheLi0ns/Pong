import java.awt.*;

public class Ball {
    private final Image ball_image = Assets.BALL;
    private int x;
    private int y;
    private final int width = ball_image.getWidth(null);
    private final int height = ball_image.getHeight(null);
    private int xVelocity;
    private int yVelocity;

    Ball(int x, int y, int xVelocity, int yVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    void move(){
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

    void checkCollisions(Player p1, Player p2){

        //WALL COLLISION
        if(Collisions.checkWallCollision(this)) {
            xVelocity *= -1;
            Sound.play(Sound.WALL_HIT_SOUND);
        }

        checkPlayerCollision(p1);

        checkPlayerCollision(p2);
    }

    void checkPlayerCollision(Player player){
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
            } else {
                Sound.play(Sound.PLAYER_HIT_SOUND);
            }

            //AntiBug
            if(player.getY() > 500){
                this.y = player.getY()-1-height;
            }else {
                this.y = player.getY() + player.getHeight() + 1;
            }
        }else if(!player.isMovementEnabled()){
            player.setMovementEnabled(true);
        }
    }

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

    void fireShot(Player player){
        if(xVelocity > 0){
            xVelocity = 2;
        }else xVelocity = -2;

        if(yVelocity > 0){
            yVelocity += 15;
        }else yVelocity -= 15;

        Sound.play(Sound.FIRESHOT_SOUND);
        player.setFireShotActivated(false);
    }

    void speedUp(){
        if(yVelocity > 0){
            yVelocity++;
        }else yVelocity--;

        if(xVelocity > 0){
            xVelocity++;
        }else xVelocity--;
    }

    void update(Player p1, Player p2){
        checkCollisions(p1, p2);
        move();
    }

    String checkScored(){
        if(y < -height){
            return "UP";
        }else if(y > 1000){
            return "DOWN";
        }
        else return "IN";
    }

    public Rectangle getHitbox(){
        return new Rectangle(x, y, width, height);
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
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.ball_image, this.x, this.y, null);
    }
}