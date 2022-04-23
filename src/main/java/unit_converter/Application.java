package main.java.unit_converter;

import main.java.unit_converter.gui.AppGUI;
import main.java.unit_converter.util.FileHelper;

import javax.swing.*;
import java.util.HashMap;

public class Application {

    public static final HashMap<String, String> SETTINGS = FileHelper.getSettings("config.txt");
    public static final HashMap<String, String> LANGUAGE = FileHelper.getSettings("lang/" + SETTINGS.get("lang") + ".txt");

    public static AppGUI applicationWindow = new AppGUI(LANGUAGE);

    public static void main(String[] args) {
        applicationWindow.show();
    }

}
