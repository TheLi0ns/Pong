package com.TheLi0ns.SettingFiles;

import com.TheLi0ns.SettingFiles.serializable.KeyBindings;
import com.TheLi0ns.SettingFiles.serializable.PlayersPowers;
import com.TheLi0ns.SettingFiles.serializable.Settings;

import java.io.File;

/**
 * It handles the {@link SettingFilesHandler#save() save}
 * and {@link SettingFilesHandler#load() load}
 * of the setting files
 * @see KeyBindings
 * @see Settings
 * @see PlayersPowers
 */
public class SettingFilesHandler {
    /**
     * Name of the directory in which
     * the files will be
     */
    public static String dir = "settings";
    static boolean dirExist;
    public static void save(){
        if(!dirExist){
            if(new File(dir).mkdir()) dirExist = true;
        }

        new KeyBindings().save();
        new Settings().save();
        new PlayersPowers().save();
    }

    public static void load(){
        Settings.load();
        KeyBindings.load();
        PlayersPowers.load();
    }

    public static void loadDefault(){
        Settings.loadDefault();
        KeyBindings.loadDefault();
        PlayersPowers.loadDefault();
    }
}
