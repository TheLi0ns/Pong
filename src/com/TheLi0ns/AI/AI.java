package com.TheLi0ns.AI;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

public class AI extends Player {

    final Difficulties difficulty;
    public enum Difficulties{
        /**
         * The bot follows the ball
         */
        NORMAL,

        /**
         * The bot predict the movement of the ball
         */
        IMPOSSIBLE
    }

    public AI(int x, int y, int xVelocity, Difficulties difficulty) {
        super(x, y, xVelocity);
        this.difficulty = difficulty;
    }

    private void decideMove(){
        switch (difficulty){
            case NORMAL -> {
                //MAKES THE AI FOLLOW THE BALL IF THE BALL IS MOVING AGAINST HIM
                if(MyFrame.gameLogic.ball.getyVelocity() < 0){
                    if(MyFrame.gameLogic.ball.getX() > this.x + this.width/2) move(DIRECTION.RIGHT);
                    else if(MyFrame.gameLogic.ball.getX() < this.x + this.width/2) move(DIRECTION.LEFT);
                }
                //MAKES THE AI RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
                else{
                    if(this.x + this.width/2 < GamePanel.CENTER) move(DIRECTION.RIGHT);
                    else if(this.x + this.width/2 > GamePanel.CENTER) move(DIRECTION.LEFT);
                }
            }

            case IMPOSSIBLE -> {
                //MAKES THE AI MOVE TOWARD THE PREDICTED POS OF THE BALL
                //IF THE BALL IS MOVING AGAINST HIM
                if(MyFrame.gameLogic.ball.getyVelocity() < 0){

                    int predictedX = Utils.predictXonY(
                            MyFrame.gameLogic.ball.getxVelocity(),
                            MyFrame.gameLogic.ball.getyVelocity(),
                            new Point(MyFrame.gameLogic.ball.getX() + MyFrame.gameLogic.ball.getWidth()/2, MyFrame.gameLogic.ball.getY()),
                            this.y + this.height + 10);

                    if(this.x + this.width + 10 < predictedX) move(DIRECTION.RIGHT);
                    else if(this.x - 10 > predictedX) move(DIRECTION.LEFT);
                }
                //MAKES THE AI RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
                else{
                    if(this.x + this.width/2 < GamePanel.CENTER) move(DIRECTION.RIGHT);
                    else if(this.x + this.width/2 > GamePanel.CENTER) move(DIRECTION.LEFT);
                }
            }
        }

    }

    @Override
    public void update(){
        decideMove();
    }

}
