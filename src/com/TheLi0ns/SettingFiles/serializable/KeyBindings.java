package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.KeyHandler;
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

        try(
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/keyBindings.json");
            Scanner sc = new Scanner(fileIn)
        ){
            keyBindings = gson.fromJson(sc.nextLine(), KeyBindings.class);
        } catch (JsonSyntaxException | IOException exception){
            loadDefault();
            return;
        }

        checkDataIntegrity(keyBindings);
        setKeyBindings(keyBindings);
    }

    private static KeyBindings getDefaultKeyBindings() throws IOException {
        KeyBindings keyBindings;

        Gson gson = new Gson();

        InputStream fileIn = Settings.class.getResourceAsStream("/default_settings/keyBindings.json");
        Scanner sc = new Scanner(fileIn);
        keyBindings = gson.fromJson(sc.nextLine(), KeyBindings.class);
        fileIn.close();

        return keyBindings;
    }

    public static void loadDefault(){
        KeyBindings keyBindings;

        try {
            keyBindings = getDefaultKeyBindings();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }

        setKeyBindings(keyBindings);
    }

    private static void checkDataIntegrity(KeyBindings keyBindings){
        KeyBindings defaultKeyBindings = null;

        boolean checkP1Left_keyCode = (keyBindings.p1Left_keyCode >= 96 && keyBindings.p1Left_keyCode <= 111) || (keyBindings.p1Left_keyCode >= 65 && keyBindings.p1Left_keyCode <= 90) || (keyBindings.p1Left_keyCode >= 48 && keyBindings.p1Left_keyCode <= 57) || (keyBindings.p1Left_keyCode >= 37 && keyBindings.p1Left_keyCode <= 40) || keyBindings.p1Left_keyCode == 32 || (keyBindings.p1Left_keyCode >= 16 && keyBindings.p1Left_keyCode <= 18);
        boolean checkP1Right_keyCode = (keyBindings.p1Right_keyCode >= 96 && keyBindings.p1Right_keyCode <= 111) || (keyBindings.p1Right_keyCode >= 65 && keyBindings.p1Right_keyCode <= 90) || (keyBindings.p1Right_keyCode >= 48 && keyBindings.p1Right_keyCode <= 57) || (keyBindings.p1Right_keyCode >= 37 && keyBindings.p1Right_keyCode <= 40) || keyBindings.p1Right_keyCode == 32 || (keyBindings.p1Right_keyCode >= 16 && keyBindings.p1Right_keyCode <= 18);
        boolean checkP1OffensivePower_keyCode = (keyBindings.p1OffensivePower_keyCode >= 96 && keyBindings.p1OffensivePower_keyCode <= 111) || (keyBindings.p1OffensivePower_keyCode >= 65 && keyBindings.p1OffensivePower_keyCode <= 90) || (keyBindings.p1OffensivePower_keyCode >= 48 && keyBindings.p1OffensivePower_keyCode <= 57) || (keyBindings.p1OffensivePower_keyCode >= 37 && keyBindings.p1OffensivePower_keyCode <= 40) || keyBindings.p1OffensivePower_keyCode == 32 || (keyBindings.p1OffensivePower_keyCode >= 16 && keyBindings.p1OffensivePower_keyCode <= 18);
        boolean checkP1DefensivePower_keyCode = (keyBindings.p1DefensivePower_keyCode >= 96 && keyBindings.p1DefensivePower_keyCode <= 111) || (keyBindings.p1DefensivePower_keyCode >= 65 && keyBindings.p1DefensivePower_keyCode <= 90) || (keyBindings.p1DefensivePower_keyCode >= 48 && keyBindings.p1DefensivePower_keyCode <= 57) || (keyBindings.p1DefensivePower_keyCode >= 37 && keyBindings.p1DefensivePower_keyCode <= 40) || keyBindings.p1DefensivePower_keyCode == 32 || (keyBindings.p1DefensivePower_keyCode >= 16 && keyBindings.p1DefensivePower_keyCode <= 18);
        boolean checkP2Left_keyCode = (keyBindings.p2Left_keyCode >= 96 && keyBindings.p2Left_keyCode <= 111) || (keyBindings.p2Left_keyCode >= 65 && keyBindings.p2Left_keyCode <= 90) || (keyBindings.p2Left_keyCode >= 48 && keyBindings.p2Left_keyCode <= 57) || (keyBindings.p2Left_keyCode >= 37 && keyBindings.p2Left_keyCode <= 40) || keyBindings.p2Left_keyCode == 32 || (keyBindings.p2Left_keyCode >= 16 && keyBindings.p2Left_keyCode <= 18);
        boolean checkP2Right_keyCode = (keyBindings.p2Right_keyCode >= 96 && keyBindings.p2Right_keyCode <= 111) || (keyBindings.p2Right_keyCode >= 65 && keyBindings.p2Right_keyCode <= 90) || (keyBindings.p2Right_keyCode >= 48 && keyBindings.p2Right_keyCode <= 57) || (keyBindings.p2Right_keyCode >= 37 && keyBindings.p2Right_keyCode <= 40) || keyBindings.p2Right_keyCode == 32 || (keyBindings.p2Right_keyCode >= 16 && keyBindings.p2Right_keyCode <= 18);
        boolean checkP2OffensivePower_keyCode = (keyBindings.p2OffensivePower_keyCode >= 96 && keyBindings.p2OffensivePower_keyCode <= 111) || (keyBindings.p2OffensivePower_keyCode >= 65 && keyBindings.p2OffensivePower_keyCode <= 90) || (keyBindings.p2OffensivePower_keyCode >= 48 && keyBindings.p2OffensivePower_keyCode <= 57) || (keyBindings.p2OffensivePower_keyCode >= 37 && keyBindings.p2OffensivePower_keyCode <= 40) || keyBindings.p2OffensivePower_keyCode == 32 || (keyBindings.p2OffensivePower_keyCode >= 16 && keyBindings.p2OffensivePower_keyCode <= 18);
        boolean checkP2DefensivePower_keyCode = (keyBindings.p2DefensivePower_keyCode >= 96 && keyBindings.p2DefensivePower_keyCode <= 111) || (keyBindings.p2DefensivePower_keyCode >= 65 && keyBindings.p2DefensivePower_keyCode <= 90) || (keyBindings.p2DefensivePower_keyCode >= 48 && keyBindings.p2DefensivePower_keyCode <= 57) || (keyBindings.p2DefensivePower_keyCode >= 37 && keyBindings.p2DefensivePower_keyCode <= 40) || keyBindings.p2DefensivePower_keyCode == 32 || (keyBindings.p2DefensivePower_keyCode >= 16 && keyBindings.p2DefensivePower_keyCode <= 18);

        if(!checkP1Left_keyCode || !checkP1Right_keyCode || !checkP1OffensivePower_keyCode || !checkP1DefensivePower_keyCode || !checkP2Left_keyCode || !checkP2Right_keyCode || !checkP2OffensivePower_keyCode || !checkP2DefensivePower_keyCode){
            try {
                defaultKeyBindings = getDefaultKeyBindings();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(!checkP1Left_keyCode) keyBindings.p1Left_keyCode = defaultKeyBindings.p1Left_keyCode;
        if(!checkP1Right_keyCode) keyBindings.p1Right_keyCode = defaultKeyBindings.p1Right_keyCode;
        if(!checkP1OffensivePower_keyCode) keyBindings.p1OffensivePower_keyCode = defaultKeyBindings.p1OffensivePower_keyCode;
        if(!checkP1DefensivePower_keyCode) keyBindings.p1DefensivePower_keyCode = defaultKeyBindings.p1DefensivePower_keyCode;
        if(!checkP2Left_keyCode) keyBindings.p2Left_keyCode = defaultKeyBindings.p2Left_keyCode;
        if(!checkP2Right_keyCode) keyBindings.p2Right_keyCode = defaultKeyBindings.p2Right_keyCode;
        if(!checkP2OffensivePower_keyCode) keyBindings.p2OffensivePower_keyCode = defaultKeyBindings.p2OffensivePower_keyCode;
        if(!checkP2DefensivePower_keyCode) keyBindings.p2DefensivePower_keyCode = defaultKeyBindings.p2DefensivePower_keyCode;
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
