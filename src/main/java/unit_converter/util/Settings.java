package main.java.unit_converter.util;

import java.util.HashMap;

public class Settings {

    private final HashMap<String, String> settings;

    public Settings(HashMap<String, String> settings) {
        this.settings = settings;
    }

    public String[] getSettingsArray(String[] keys) {
        return getSettingsArray(keys, false);
    }

    public String[] getSettingsArray(String[] keys, boolean returnKey) {
        String[] s = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            s[i] = getSetting(keys[i], returnKey);
        }
        return s;
    }

    public String getSetting(String key) {
        return getSetting(key, false);
    }

    public String getSetting(String key, boolean returnKey) {
        if (settings.containsKey(key)) {
            return settings.get(key);
        }
        return (returnKey) ? key : null;
    }

}
