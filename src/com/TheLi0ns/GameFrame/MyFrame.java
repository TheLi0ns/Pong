package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import javax.swing.*;

public class MyFrame extends JFrame {
    JFrame frame = new JFrame();
    public static GamePanel gamePanel = new GamePanel();

    public Assets assets = new Assets();
    public Sound sound = new Sound();
    public static GameLogic gameLogic = new GameLogic();
    public MyFrame(){
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                quit();
            }
        });
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        SettingFilesHandler.load();
    }

    /**
     * Saves settings and exits the app
     */
    public static void quit(){
        SettingFilesHandler.save();
        System.exit(0);
    }
}
