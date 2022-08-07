import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int xVelocity;
    private int yVelocity;

    Ball(int x, int y, int xVelocity, int yVelocity, int width, int height){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.width = width;
        this.height = height;
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

        if(p2.getY() > p1.getY()){
            Player p2_temp = p2;
            p2 = p1;
            p1 = p2_temp;
        }

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
                edgeBounce();
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

    private void edgeBounce() {
        if(xVelocity > 0){
            xVelocity += 2;
        }else {
            xVelocity -= 2;
        }

        if((x > 500 && xVelocity < 0) || (x < 500 && xVelocity > 0)){
            xVelocity *= -1;
        }
    }

    void fireShot(Player player){
        xVelocity = 2;
        yVelocity += 15;
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
}