package com.TheLi0ns.Utility;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameObject.Balls.Ball;
import com.TheLi0ns.GameObject.Players.Player;

public class Collisions {
    public static boolean checkWallCollision(Ball ball){
        return ball.getX()+ball.getWidth()-16 > GamePanel.RIGHT_BOUND || ball.getX()+5 < GamePanel.LEFT_BOUND;
    }

    public static boolean checkBallPlayerCollision(Player player, Ball ball){
        return player.getHitbox().intersects(ball.getHitbox());
    }

    public static boolean checkBallPlayerEdgeCollision(Player player, Ball ball){
        return checkBallPlayerCollision(player, ball) &&
                (ball.getY() > GamePanel.CENTER ?
                        ball.getY()+ball.getHeight() >= player.getY()+15 :
                        ball.getY() <= player.getY()+player.getHeight()-15
                );
    }
}
