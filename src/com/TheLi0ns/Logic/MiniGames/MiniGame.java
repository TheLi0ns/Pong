package com.TheLi0ns.Logic.MiniGames;

import com.TheLi0ns.Logic.GameLogic;

import java.awt.*;

public abstract class MiniGame {

    protected final GameLogic gl;

    boolean finished;

    public MiniGame(GameLogic gl){
        this.gl = gl;
    }

    abstract public void start();

    abstract public void update();

    abstract public void draw(Graphics2D g2d);

    public boolean isFinished() {
        return finished;
    }
}
