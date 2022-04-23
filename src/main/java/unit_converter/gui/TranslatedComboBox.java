package main.java.unit_converter.gui;

import main.java.unit_converter.Application;

import javax.swing.*;

public class TranslatedComboBox extends JComboBox<String> {

    private String[] rawOptions;

    public TranslatedComboBox(String[] rawOptions) {
        this();
        reload(rawOptions);
    }

    public TranslatedComboBox(String[] rawOptions, String defaultItem) {
        this();
        reload(rawOptions, defaultItem);
    }

    public TranslatedComboBox() {
        super();
    }

    public void reload(String[] rawOptions) {
        this.rawOptions = rawOptions;
        this.removeAllItems();
        for (String s : rawOptions) {
            this.addItem(Application.LANGUAGE.getSetting(s, true));
        }
    }

    public void reload(String[] rawOptions, String defaultItem) {
        reload(rawOptions);
        this.setSelectedItem(Application.LANGUAGE.getSetting(defaultItem, true));
    }

    public String getSelectedRaw() {
        return rawOptions[this.getSelectedIndex()];
    }

    public String getSelectedString() {
        return (String) getSelectedItem();
    }

}
