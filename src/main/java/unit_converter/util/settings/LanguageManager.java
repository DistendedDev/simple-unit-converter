package main.java.unit_converter.util.settings;

import main.java.unit_converter.Application;
import main.java.unit_converter.gui.TranslatableText;
import main.java.unit_converter.util.FileHelper;

import java.awt.*;

public class LanguageManager {

    public static void setLanguage(String lang) {
        Language = new Settings(FileHelper.getSettings("lang/" + lang));
        TranslatableText.reloadAll();
        try {
            Application.applicationWindow.reloadAllSelectionBoxes();
        } catch (Exception ignored) {}
    }

    public static String translate(String key) {
        return Language.getSetting(key, key);
    }

    public static Font getNormalFont(int style, int size) {
        return new Font(Language.getSetting("FONT1"), style, size);
    }

    public static Font getSpecialFont(int style, int size) {
        return new Font(Language.getSetting("FONT2"), style, size);
    }

    private static Settings Language;

}
