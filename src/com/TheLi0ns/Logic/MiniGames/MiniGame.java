package com.TheLi0ns.Logic.MiniGames;

import java.awt.*;

public abstract class MiniGame {

    boolean finished;

    abstract public void start();

    abstract public void update();

    abstract public void draw(Graphics2D g2d);

    public boolean isFinished() {
        return finished;
    }
}
