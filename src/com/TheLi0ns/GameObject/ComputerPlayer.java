package com.TheLi0ns.GameObject;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Logic.GameModes.Match_pve;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Utils;
import com.TheLi0ns.Utility.Vector2D;

public class ComputerPlayer extends Player {

    final Match_pve ENV;

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


    public ComputerPlayer(int x, int y, int xVelocity, Difficulties difficulty, Match_pve ENV) {
        super(x, y, xVelocity);
        this.difficulty = difficulty;
        this.ENV = ENV;
    }

    private void decideMove(){
        switch (difficulty){
            case NORMAL -> {
                //MAKES THE ComputerPlayer FOLLOW THE BALL IF THE BALL IS MOVING AGAINST HIM
                if(ENV.getBall().getVelocity().getYDirection() == Directions.UP){
                    if(ENV.getBall().getX() > this.x + this.width/2) move(Directions.RIGHT);
                    else if(ENV.getBall().getX() < this.x + this.width/2) move(Directions.LEFT);
                }
                //MAKES THE ComputerPlayer RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
                else{
                    if(this.x + this.width/2 < GamePanel.CENTER) move(Directions.RIGHT);
                    else if(this.x + this.width/2 > GamePanel.CENTER) move(Directions.LEFT);
                }
            }

            case IMPOSSIBLE -> {
                //MAKES THE ComputerPlayer MOVE TOWARD THE PREDICTED POS OF THE BALL
                //IF THE BALL IS MOVING AGAINST HIM
                if(ENV.getBall().getVelocity().getYDirection() == Directions.UP){

                    int predictedX = Utils.predictXonY(
                            ENV.getBall().getVelocity(),
                            new Vector2D(ENV.getBall().getX() + ENV.getBall().getWidth()/2, ENV.getBall().getY()),
                            this.y + this.height + 10);

                    if(this.x + this.width + 10 < predictedX) move(Directions.RIGHT);
                    else if(this.x - 10 > predictedX) move(Directions.LEFT);
                }
                //MAKES THE ComputerPlayer RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
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
