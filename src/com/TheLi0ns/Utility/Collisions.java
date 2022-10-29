package com.TheLi0ns.Utility;

import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;

public class Collisions {
    public static boolean checkWallCollision(Ball ball){
        return ball.getX()+ball.getWidth()-16 > 995 || ball.getX() < 0;
    }

    public static boolean checkBallPlayerCollision(Player player, Ball ball){
        return player.getHitbox().intersects(ball.getHitbox());
    }

    public static boolean checkBallPlayerEdgeCollision(Player player, Ball ball){
        return checkBallPlayerCollision(player, ball) &&
                (ball.getY() > 500 ?
                        ball.getY()+ball.getHeight() >= player.getY()+15 :
                        ball.getY() <= player.getY()+player.getHeight()-15
                );
    }
}
