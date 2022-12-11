package com.TheLi0ns.AI;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Utils;
import com.TheLi0ns.Utility.Vector2D;

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
                if(MyFrame.gameLogic.ball.getVelocity().getYDirection() == Directions.UP){
                    if(MyFrame.gameLogic.ball.getX() > this.x + this.width/2) move(Directions.RIGHT);
                    else if(MyFrame.gameLogic.ball.getX() < this.x + this.width/2) move(Directions.LEFT);
                }
                //MAKES THE AI RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
                else{
                    if(this.x + this.width/2 < GamePanel.CENTER) move(Directions.RIGHT);
                    else if(this.x + this.width/2 > GamePanel.CENTER) move(Directions.LEFT);
                }
            }

            case IMPOSSIBLE -> {
                //MAKES THE AI MOVE TOWARD THE PREDICTED POS OF THE BALL
                //IF THE BALL IS MOVING AGAINST HIM
                if(MyFrame.gameLogic.ball.getVelocity().getYDirection() == Directions.UP){

                    int predictedX = Utils.predictXonY(
                            MyFrame.gameLogic.ball.getVelocity(),
                            new Vector2D(MyFrame.gameLogic.ball.getX() + MyFrame.gameLogic.ball.getWidth()/2, MyFrame.gameLogic.ball.getY()),
                            this.y + this.height + 10);

                    if(this.x + this.width + 10 < predictedX) move(Directions.RIGHT);
                    else if(this.x - 10 > predictedX) move(Directions.LEFT);
                }
                //MAKES THE AI RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
                else{
                    if(this.x + this.width/2 < GamePanel.CENTER) move(Directions.RIGHT);
                    else if(this.x + this.width/2 > GamePanel.CENTER) move(Directions.LEFT);
                }
            }
        }

    }

    @Override
    public void update(){
        decideMove();
    }

}
