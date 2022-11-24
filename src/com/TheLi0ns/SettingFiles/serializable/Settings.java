package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.TheLi0ns.Utility.Sound;

import java.io.*;

/**
 * Class that represents the general settings
 * for serialization and deserialization
 */
public class Settings implements Serializable {
    int pointToWin;
    boolean arePowersEnabled;
    int volumeScale;

    public Settings() {
        this.pointToWin = MyFrame.gameLogic.getPointsToWin();
        this.arePowersEnabled = MyFrame.gameLogic.arePowersEnabled();
        this.volumeScale = Sound.getVolumeScale();
    }

    public void save(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(SettingFilesHandler.dir + "/settings.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void load(){
        Settings settings;

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            settings = (Settings) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        setSettings(settings);
    }

    public static void loadDefault(){
        Settings settings;

        try {
            InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            settings = (Settings) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        setSettings(settings);
    }

    private static void setSettings(Settings settings){
        MyFrame.gameLogic.setPointsToWin(settings.pointToWin);
        MyFrame.gameLogic.setArePowersEnabled(settings.arePowersEnabled);
        Sound.setVolumeScale(settings.volumeScale);
    }
}
