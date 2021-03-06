package main.java.unit_converter.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class FileHelper {

    public static final String RESOURCES_DIRECTORY = "src/main/resources/";
    public static final String IMAGES_DIRECTORY = RESOURCES_DIRECTORY + "images/";
    public static final String SETTINGS_DIRECTORY = RESOURCES_DIRECTORY + "settings/";

    private static Scanner fileReader;

    public static ImageIcon getImage(String fileDirectory, int width, int height) {
        ImageIcon icon;
        try {
            icon = new ImageIcon(ImageIO
                    .read(new File(IMAGES_DIRECTORY + fileDirectory))
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch(Exception e) {
            System.out.printf("image %s not found", fileDirectory);
            icon = null;
        }
        return icon;
    }

    public static HashMap<String, String> getSettings(String fileDirectory) {
        if (fileDirectory.split("\\.").length < 2) {
            fileDirectory += ".txt";
        }
        HashMap<String, String> settings = new LinkedHashMap<>();
        try {
            fileReader = new Scanner(new File(SETTINGS_DIRECTORY + fileDirectory));
        } catch (Exception e) {
            System.out.println("Error: " + fileDirectory + " file not found. ");
        }
        while (fileReader.hasNextLine()) {
            String[] setting = fileReader.nextLine().split(":");
            if (setting[0].length() == 0 || setting[0].charAt(0) == '#') continue;
            if (setting.length != 2) {
                for (String s : setting) {
                    System.out.print(s + ' ');
                }
                System.out.println("invalid setting");
                continue;
            }
            settings.put(setting[0], setting[1]);
        }
        return settings;
    }

    public static void openResourceDirectory(String directory) {
        File file = new File (RESOURCES_DIRECTORY + directory);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (Exception ignored) {}
    }

}
