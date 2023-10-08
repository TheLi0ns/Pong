package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.Logic.GameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the rendering
 */
public class GamePanel extends JPanel {

    public static final int WIDTH = 1000, HEIGHT = 1000;
    public static final int LEFT_BOUND = 5, RIGHT_BOUND = 995, CENTER = 500;

    final GameLogic gl;

    GamePanel(GameLogic gl){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        this.gl = gl;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //MATCH
        if(gl.getGameState() == GameLogic.GameStates.PLAYING){
            gl.getGameMode().draw(g2d);
        }
        //CUTSCENE
        if(gl.getGameState() == GameLogic.GameStates.CUTSCENE) {
            CutsceneHandler.draw(g2d);
        }
        //MENU
        else if(gl.getGameState() == GameLogic.GameStates.MENU) {
            gl.getMenu().draw(g2d);
        }

        g2d.dispose();
    }
}
