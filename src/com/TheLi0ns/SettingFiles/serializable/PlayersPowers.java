package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;

import java.io.*;

public class PlayersPowers implements Serializable {
    int p1OffensivePower_index;
    int p1DefensivePower_index;

    int p2OffensivePower_index;
    int p2DefensivePower_index;

    public PlayersPowers(){
        this.p1DefensivePower_index = GamePanel.p1PowerSelectionMenu.defensivePower_index;
        this.p1OffensivePower_index = GamePanel.p1PowerSelectionMenu.offensivePower_index;
        this.p2DefensivePower_index = GamePanel.p2PowerSelectionMenu.defensivePower_index;
        this.p2OffensivePower_index = GamePanel.p2PowerSelectionMenu.offensivePower_index;
    }

    public void save(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(SettingFilesHandler.dir + "/playersPowers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void load(){
        PlayersPowers playersPowers;

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/playersPowers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playersPowers = (PlayersPowers) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        setPlayersPowersIndex(playersPowers);
    }

    public static void loadDefault(){
        PlayersPowers playersPowers;

        try {
            InputStream fileIn = KeyBindings.class.getResourceAsStream("/default_settings/playersPowers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playersPowers = (PlayersPowers) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        setPlayersPowersIndex(playersPowers);
    }

    private static void setPlayersPowersIndex(PlayersPowers playersPowers) {
        GamePanel.p1PowerSelectionMenu.defensivePower_index = playersPowers.p1DefensivePower_index;
        GamePanel.p1PowerSelectionMenu.offensivePower_index = playersPowers.p1OffensivePower_index;
        GamePanel.p2PowerSelectionMenu.defensivePower_index = playersPowers.p2DefensivePower_index;
        GamePanel.p2PowerSelectionMenu.offensivePower_index = playersPowers.p2OffensivePower_index;
    }
}
