package main.java.unit_converter.util.settings;

import java.util.HashMap;

public class Settings {

    private final HashMap<String, String> settings;

    public Settings(HashMap<String, String> settings) {
        this.settings = settings;
    }

    public String[] getSettings(String[] keys) {
        return getSettings(keys, false);
    }

    public String[] getSettings(String[] keys, boolean returnKey) {
        String[] s = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            s[i] = getSetting(keys[i]);
        }
        return s;
    }

    public String getSetting(String key) {
        return getSetting(key, "");
    }

    public String getSetting(String key, String defaultReturn) {
        if (settings.containsKey(key)) {
            return settings.get(key);
        }
        return defaultReturn;
    }

}
