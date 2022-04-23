package main.java.unit_converter;

import main.java.unit_converter.gui.AppGUI;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.util.settings.Settings;

public class Application {

    public static final String VERSION = "0.1.1";
    public static final Settings SETTINGS = new Settings(FileHelper.getSettings("config"));
    public static final Settings LANGUAGE = new Settings(FileHelper.getSettings("lang/" + SETTINGS.getSetting("lang")));

    public static AppGUI applicationWindow = new AppGUI(600, 250);

    public static void main(String[] args) {
        applicationWindow.show();
    }

}
