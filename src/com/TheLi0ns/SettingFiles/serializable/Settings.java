package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.TheLi0ns.Utility.Sound;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Class that represents the general settings
 * for json serialization and deserialization
 */
public class Settings {
    int pointToWin;
    boolean arePowersEnabled;
    int volumeScale;

    public Settings() {
        this.pointToWin = MyFrame.gameLogic.getPointsToWin();
        this.arePowersEnabled = MyFrame.gameLogic.arePowersEnabled();
        this.volumeScale = Sound.getVolumeScale();
    }

    public void save(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try {
            File f = new File(SettingFilesHandler.dir + "/settings.json");
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
        Settings settings;

        Gson gson = new Gson();

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/settings.json");
            Scanner sc = new Scanner(fileIn);
            settings = gson.fromJson(sc.nextLine(), Settings.class);
            fileIn.close();
        } catch (JsonSyntaxException | IOException exception){
            loadDefault();
            return;
        }

        checkDataIntegrity(settings);
        setSettings(settings);
    }

    private static Settings getDefaultSettings() throws IOException {
        Settings settings;

        Gson gson = new Gson();

        InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/settings.json");
        Scanner sc = new Scanner(fileIn);
        settings = gson.fromJson(sc.nextLine(), Settings.class);
        fileIn.close();

        return settings;
    }

    public static void loadDefault(){
        Settings settings;
        try {
            settings = getDefaultSettings();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setSettings(settings);
    }

    static Settings checkDataIntegrity(Settings settings){

        Settings defaultSettings = null;

        boolean checkPointToWin = settings.pointToWin > 0 && settings.pointToWin <= GameLogic.MAX_POINTS;
        boolean checkVolumeScale = settings.volumeScale >= 0 && settings.pointToWin <= Sound.MAX_VOLUME_SCALE;

        if(!checkVolumeScale || !checkPointToWin){
            try {
                defaultSettings = getDefaultSettings();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(!checkPointToWin) settings.pointToWin = defaultSettings.pointToWin;
        if(!checkVolumeScale) settings.volumeScale = defaultSettings.volumeScale;

        return settings;
    }

    private static void setSettings(Settings settings){
        MyFrame.gameLogic.setPointsToWin(settings.pointToWin);
        MyFrame.gameLogic.setArePowersEnabled(settings.arePowersEnabled);
        Sound.setVolumeScale(settings.volumeScale);
    }
}
