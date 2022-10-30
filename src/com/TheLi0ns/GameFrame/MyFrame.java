package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import javax.swing.*;

public class MyFrame extends JFrame {
    JFrame frame = new JFrame();
    public static GamePanel gamePanel = new GamePanel();

    public Assets assets = new Assets();
    public Sound sound = new Sound();
    public static GameLogic gameLogic = new GameLogic();
    MyFrame(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
    }
}
