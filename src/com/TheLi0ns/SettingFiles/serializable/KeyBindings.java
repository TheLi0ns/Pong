package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Class that represents the keyBindings
 * for json serialization and deserialization
 */
public class KeyBindings {
    int p1Left_keyCode;
    int p1Right_keyCode;

    int p1OffensivePower_keyCode;
    int p1DefensivePower_keyCode;


    int p2Left_keyCode;
    int p2Right_keyCode;

    int p2OffensivePower_keyCode;
    int p2DefensivePower_keyCode;

    public KeyBindings() {
        this.p1Left_keyCode = KeyHandler.p1Left_key;
        this.p1Right_keyCode = KeyHandler.p1Right_key;
        this.p1OffensivePower_keyCode = KeyHandler.p1OffensivePower_key;
        this.p1DefensivePower_keyCode = KeyHandler.p1DefensivePower_key;
        this.p2Left_keyCode = KeyHandler.p2Left_key;
        this.p2Right_keyCode = KeyHandler.p2Right_key;
        this.p2OffensivePower_keyCode = KeyHandler.p2OffensivePower_key;
        this.p2DefensivePower_keyCode = KeyHandler.p2DefensivePower_key;
    }

    public void save(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try {
            File f = new File(SettingFilesHandler.dir + "/keyBindings.json");
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
        KeyBindings keyBindings;

        Gson gson = new Gson();

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/keyBindings.json");
            Scanner sc = new Scanner(fileIn);
            keyBindings = gson.fromJson(sc.nextLine(), KeyBindings.class);
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }

        setKeyBindings(keyBindings);
    }

    public static void loadDefault(){
        KeyBindings keyBindings;

        Gson gson = new Gson();

        try {
            InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/keyBindings.json");
            Scanner sc = new Scanner(fileIn);
            keyBindings = gson.fromJson(sc.nextLine(), KeyBindings.class);
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }

        setKeyBindings(keyBindings);
    }

    private static void setKeyBindings(KeyBindings keyBindings){
        KeyHandler.p1Left_key = keyBindings.p1Left_keyCode;
        KeyHandler.p1Right_key = keyBindings.p1Right_keyCode;
        KeyHandler.p1OffensivePower_key = keyBindings.p1OffensivePower_keyCode;
        KeyHandler.p1DefensivePower_key = keyBindings.p1DefensivePower_keyCode;
        KeyHandler.p2Left_key = keyBindings.p2Left_keyCode;
        KeyHandler.p2Right_key = keyBindings.p2Right_keyCode;
        KeyHandler.p2OffensivePower_key = keyBindings.p2OffensivePower_keyCode;
        KeyHandler.p2DefensivePower_key = keyBindings.p2DefensivePower_keyCode;
    }
}
