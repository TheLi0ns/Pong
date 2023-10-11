package com.TheLi0ns.GameObject.Balls;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.Utility.Collisions;
import com.TheLi0ns.Utility.Sound;

public class DribbleBall extends Ball{
    public DribbleBall(int xVelocity, int yVelocity) {
        super(xVelocity, yVelocity);
    }

    public DribbleBall(int x, int y, int xVelocity, int yVelocity) {
        super(x, y, xVelocity, yVelocity);
    }

    @Override
    protected void checkCollisions(Player... players) {
        super.checkCollisions(players);

        if(this.getHitbox().y < 5){
            velocity.flipVertically();
            Sound.play(Sound.WALL_HIT_SOUND);
            this.speedUp();
        }
    }

    @Override
    protected void checkPlayerCollision(Player player) {
        if (Collisions.checkBallPlayerCollision(player, this)){
            player.hasScored();
        }
        super.checkPlayerCollision(player);
    }

    /**
     * @return true if the ball has fell
     */
    @Override
    public boolean fell(){
        return this.position.getY() > GamePanel.HEIGHT;
    }
}
