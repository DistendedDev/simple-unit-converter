package main.java.unit_converter;

import main.java.unit_converter.gui.Window;

import javax.swing.*;

public class Application {

    public static final String APPLICATION_NAME = "Unit Converter";
    public static final String RESOURCES_DIRECTORY = "src/main/resources/";

    public static JFrame applicationWindow = new Window(APPLICATION_NAME);

    public static void main(String[] args) {
        applicationWindow.setVisible(true);
    }

}
