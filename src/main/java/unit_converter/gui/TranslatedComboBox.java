package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.util.settings.LanguageManager;

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
            this.addItem(LanguageManager.translate(s));
        }
    }

    public void reload(String[] rawOptions, String defaultItem) {
        reload(rawOptions);
        this.setSelectedItem(LanguageManager.translate(defaultItem));
    }

    public String getSelectedRaw() {
        return rawOptions[this.getSelectedIndex()];
    }

    public String getSelectedString() {
        return (String) getSelectedItem();
    }

}
