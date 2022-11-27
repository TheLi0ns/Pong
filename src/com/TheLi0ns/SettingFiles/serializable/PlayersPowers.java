package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Class that represents the Players Powers
 * for json serialization and deserialization
 */
public class PlayersPowers {
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
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try {
            File f = new File(SettingFilesHandler.dir + "/playersPowers.json");
            if(!f.exists()) f.createNewFile();
            Formatter formatter = new Formatter(f);
            formatter.format("%s", json);
            formatter.flush();
            formatter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void load(){
        PlayersPowers playersPowers;

        Gson gson = new Gson();

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/playersPowers.json");
            Scanner sc = new Scanner(fileIn);
            playersPowers = gson.fromJson(sc.nextLine(), PlayersPowers.class);
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }

        setPlayersPowersIndex(playersPowers);
    }

    public static void loadDefault(){
        PlayersPowers playersPowers;

        Gson gson = new Gson();

        try {
            InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/playersPowers.json");
            Scanner sc = new Scanner(fileIn);
            playersPowers = gson.fromJson(sc.nextLine(), PlayersPowers.class);
            fileIn.close();
        } catch (IOException i) {
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
