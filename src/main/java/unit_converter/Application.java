package main.java.unit_converter;

import main.java.unit_converter.gui.AppGUI;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.util.settings.LanguageManager;
import main.java.unit_converter.util.settings.Settings;

public class Application {

    public static final String VERSION = "0.1.1";
    public static final Settings SETTINGS = new Settings(FileHelper.getSettings("config"));
    public static AppGUI applicationWindow;

    public static void main(String[] args) {
        LanguageManager.setLanguage(SETTINGS.getSetting("lang"));
        applicationWindow = new AppGUI(600, 250);
        applicationWindow.show();
    }

}
