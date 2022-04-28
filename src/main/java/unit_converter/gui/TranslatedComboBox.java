package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.util.settings.LanguageManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TranslatedComboBox extends JComboBox<String> {

    private String[] rawOptions;
    private static List<TranslatedComboBox> allCreatedBoxes = new ArrayList<>();

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
        allCreatedBoxes.add(this);
    }

    public void reload(String[] rawOptions) {
        this.rawOptions = rawOptions;
        reload();
    }

    public void reload(String[] rawOptions, String defaultItem) {
        reload(rawOptions);
        this.setSelectedItem(LanguageManager.translate(defaultItem));
    }

    public void reload() {
        this.removeAllItems();
        for (String s : rawOptions) {
            this.addItem(LanguageManager.translate(s));
        }
    }

    public String getSelectedRaw() {
        return rawOptions[this.getSelectedIndex()];
    }

    public String getSelectedString() {
        return (String) getSelectedItem();
    }

}
