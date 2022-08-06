import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int xVelocity;
    private int yVelocity;
    private final int initialxVelocity;
    private final int initialyVelocity;

    Ball(int x, int y, int xVelocity, int yVelocity, int width, int height){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.initialxVelocity = xVelocity;
        this.initialyVelocity = yVelocity;
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
        if(x < 0 || x+40 > 995) {
            xVelocity *= -1;
            Sound.play(Sound.WALL_HIT_SOUND);
        }

        if(p1.getHitbox().intersects(this.getHitbox()) || p2.getHitbox().intersects(this.getHitbox())){
            if(xVelocity > 0){
                xVelocity++;
                yVelocity++;
                if(p1.isFireShotActivated()){
                    xVelocity = 2;
                    yVelocity += 15;
                    Sound.play(Sound.FIRESHOT_SOUND);
                    p1.setFireShotActivated(false);
                }else{
                    Sound.play(Sound.PLAYER_HIT_SOUND);
                }
            }else{
                xVelocity--;
                yVelocity--;
                if(p2.isFireShotActivated()){
                    xVelocity = 2;
                    yVelocity += 15;
                    Sound.play(Sound.FIRESHOT_SOUND);
                    p2.setFireShotActivated(false);
                }else{
                    Sound.play(Sound.PLAYER_HIT_SOUND);
                }
            }
            yVelocity *= -1;
        }

        if(p1.getHitbox().intersects(this.getHitbox()) && p1.isMovementEnabled()){
            y = p1.getY()-1-height;
            p1.setMovementEnabled(false);
        }else if(!p1.isMovementEnabled()){
            p1.setMovementEnabled(true);
        }

        if(p2.getHitbox().intersects(this.getHitbox()) && p2.isMovementEnabled()){
            p2.setMovementEnabled(false);
            y = p2.getY()+p2.getHeight()+1;
        }else if(!p2.isMovementEnabled()){
            p2.setMovementEnabled(true);
        }

        /*
        //P1 VERTICAL COLLISION
        if(y-10+64 > p1.getY() && y-10+64 < p1.getY()+36) {
            for (int i = 0; i < 218; i++) {
                for(int j = 0; j < 8; j++){
                    if(x + 20 + j == p1.getX() + i){
                        yVelocity *= -1;
                        return;
                    }
                }
            }
        }

        //P2 VERTICAL COLLISION
        if(y+10 < p2.getY()+36 && y+10 > p2.getY()) {
            for (int i = 0; i < 218; i++) {
                for(int j = 0; j < 8; j++){
                    if(x + 20 + j == p2.getX() + i){
                        yVelocity *= -1;
                        return;
                    }
                }
            }
        }
         */
    }

    String checkScored(){
        if(y < 0-height){
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
