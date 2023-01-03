package com.TheLi0ns.Logic.MiniGames;

import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;

import java.awt.*;

public abstract class MiniGame {

    Player player;

    Ball ball;

    boolean finished;

    abstract public void start();

    abstract public void update();

    abstract public void draw(Graphics2D g2d);

    public Player getPlayer() {
        return player;
    }

    public Ball getBall() {
        return ball;
    }

    public boolean isFinished() {
        return finished;
    }
}
