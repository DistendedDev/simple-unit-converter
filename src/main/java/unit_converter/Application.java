package main.java.unit_converter;

import main.java.unit_converter.gui.AppGUI;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.util.Settings;

import javax.swing.*;
import java.util.HashMap;

public class Application {

    public static final Settings SETTINGS = new Settings(FileHelper.getSettings("config.txt"));
    public static final Settings LANGUAGE = new Settings(FileHelper.getSettings("lang/" + SETTINGS.getSetting("lang") + ".txt"));

    public static AppGUI applicationWindow = new AppGUI();

    public static void main(String[] args) {
        applicationWindow.show();
    }

}
