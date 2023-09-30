package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Powers.DefensivePowers.DefensivePowersEnum;
import com.TheLi0ns.Powers.OffensivePowers.OffensivePowersEnum;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
        this.p1DefensivePower_index = GamePanel.powersSelectionMenuPvP.p1_defensivePower_index;
        this.p1OffensivePower_index = GamePanel.powersSelectionMenuPvP.p1_offensivePower_index;
        this.p2DefensivePower_index = GamePanel.powersSelectionMenuPvP.p2_defensivePower_index;
        this.p2OffensivePower_index = GamePanel.powersSelectionMenuPvP.p2_offensivePower_index;
    }

    public void save(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try{
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

        try(
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/playersPowers.json");
            Scanner sc = new Scanner(fileIn)
        ){
            playersPowers = gson.fromJson(sc.nextLine(), PlayersPowers.class);
        } catch (JsonSyntaxException | IOException exception){
            loadDefault();
            return;
        }

        checkDataIntegrity(playersPowers);
        setPlayersPowersIndex(playersPowers);
    }

    private static PlayersPowers getDefaultPlayerPowers() throws IOException {
        PlayersPowers playersPowers;

        Gson gson = new Gson();

        InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/keyBindings.json");
        Scanner sc = new Scanner(fileIn);
        playersPowers = gson.fromJson(sc.nextLine(), PlayersPowers.class);
        fileIn.close();

        return playersPowers;
    }

    public static void loadDefault(){
        PlayersPowers playersPowers;
        try {
            playersPowers = getDefaultPlayerPowers();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }

        setPlayersPowersIndex(playersPowers);
    }

    private static void checkDataIntegrity(PlayersPowers playersPowers){
        PlayersPowers defaultPlayerPowers = null;

        boolean checkP1OffensivePower_index = playersPowers.p1OffensivePower_index > 0 && playersPowers.p1OffensivePower_index < OffensivePowersEnum.values().length;
        boolean checkP1DefensivePower_index = playersPowers.p1DefensivePower_index > 0 && playersPowers.p1DefensivePower_index < DefensivePowersEnum.values().length;

        boolean checkP2OffensivePower_index = playersPowers.p2OffensivePower_index > 0 && playersPowers.p2OffensivePower_index < OffensivePowersEnum.values().length;
        boolean checkP2DefensivePower_index = playersPowers.p2DefensivePower_index > 0 && playersPowers.p2DefensivePower_index < DefensivePowersEnum.values().length;

        if(!checkP1DefensivePower_index || !checkP1OffensivePower_index || !checkP2DefensivePower_index || !checkP2OffensivePower_index){
            try {
                defaultPlayerPowers = getDefaultPlayerPowers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(!checkP1DefensivePower_index) playersPowers.p1DefensivePower_index = defaultPlayerPowers.p1DefensivePower_index;
        if(!checkP1OffensivePower_index) playersPowers.p1OffensivePower_index = defaultPlayerPowers.p1OffensivePower_index;
        if(!checkP2DefensivePower_index) playersPowers.p2DefensivePower_index = defaultPlayerPowers.p2DefensivePower_index;
        if(!checkP2OffensivePower_index) playersPowers.p2OffensivePower_index = defaultPlayerPowers.p2OffensivePower_index;
    }

    private static void setPlayersPowersIndex(PlayersPowers playersPowers) {
        GamePanel.powersSelectionMenuPvE.defensivePower_index = playersPowers.p1DefensivePower_index;
        GamePanel.powersSelectionMenuPvE.offensivePower_index = playersPowers.p1OffensivePower_index;
        GamePanel.powersSelectionMenuPvP.p1_defensivePower_index = playersPowers.p1DefensivePower_index;
        GamePanel.powersSelectionMenuPvP.p1_offensivePower_index = playersPowers.p1OffensivePower_index;
        GamePanel.powersSelectionMenuPvP.p2_defensivePower_index = playersPowers.p2DefensivePower_index;
        GamePanel.powersSelectionMenuPvP.p2_offensivePower_index = playersPowers.p2OffensivePower_index;
    }
}
