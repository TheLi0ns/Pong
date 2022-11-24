package com.TheLi0ns.AI;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Player;

public class AI extends Player {

    public AI(int x, int y, int xVelocity) {
        super(x, y, xVelocity);
    }

    private void decideMove(){
        if(MyFrame.gameLogic.ball.getyVelocity() < 0){
            if(MyFrame.gameLogic.ball.getX() > this.x + this.width/2) move(DIRECTION.RIGHT);
            else if(MyFrame.gameLogic.ball.getX() < this.x + this.width/2) move(DIRECTION.LEFT);
        }else{
            if(this.x + this.width/2 < GamePanel.CENTER) move(DIRECTION.RIGHT);
            else if(this.x + this.width/2 > GamePanel.CENTER) move(DIRECTION.LEFT);
        }
    }

    @Override
    public void update(){
        decideMove();
    }

}
