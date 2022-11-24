package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;

import java.io.*;

/**
 * Class that represents the keyBindings
 * for serialization and deserialization
 */
public class KeyBindings implements Serializable {
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
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(SettingFilesHandler.dir + "/keyBindings.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void load(){
        KeyBindings keyBindings;

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/keyBindings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            keyBindings = (KeyBindings) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        setKeyBindings(keyBindings);
    }

    public static void loadDefault(){
        KeyBindings keyBindings;

        try {
            InputStream fileIn = KeyBindings.class.getResourceAsStream("/default_settings/keyBindings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            keyBindings = (KeyBindings) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
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
