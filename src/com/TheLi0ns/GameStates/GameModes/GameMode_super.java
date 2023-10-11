package com.TheLi0ns.GameStates.GameModes;

import com.TheLi0ns.GameStates.GameState;
import com.TheLi0ns.Logic.GameLogic;

import java.awt.*;

public abstract class GameMode_super implements GameState {

    protected final GameLogic gl;

    States state = States.PAUSE;
    enum States{PLAYING, PAUSE, FINISH}

    public GameMode_super(GameLogic gl){
        this.gl = gl;
    }

    abstract protected void start();

    @Override
    public final void update(){
        if(state == States.PLAYING) updateGame();
    }

    abstract protected void updateGame();

    @Override
    public final void draw(Graphics2D g2d){
        switch (state){
            case PLAYING -> drawGame(g2d);
            case PAUSE -> drawPauseScreen(g2d);
            case FINISH -> drawFinishScreen(g2d);
        }
    }

    abstract protected void drawGame(Graphics2D g2d);
    abstract protected void drawPauseScreen(Graphics2D g2d);
    abstract protected void drawFinishScreen(Graphics2D g2d);

    protected void drawField(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(0, 0, 0, 1000);
        g2d.drawLine(1000, 0, 1000, 1000);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(400, 400, 200, 200);
        g2d.drawLine(0, 500, 1000, 500);
        g2d.fillOval(490, 490, 20, 20);

        g2d.setPaint(Color.darkGray);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3,5}, 0));
        g2d.drawLine(0, 45, 1000,45);
        g2d.drawLine(0, 954, 1000,954);
    }

    public boolean isFinished() {
        return state == States.FINISH;
    }

    public boolean isPaused(){
        return state == States.PAUSE;
    }

    public boolean isPlaying(){
        return state == States.PLAYING;
    }

    public void togglePause(){
        switch (state){
            case PLAYING -> state = States.PAUSE;
            case PAUSE -> state = States.PLAYING;
        }
    }
}
