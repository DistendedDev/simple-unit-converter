package main.java.unit_converter.gui;

import main.java.unit_converter.util.settings.LanguageManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TranslatableText {

    public static void reloadAll() {
        for (TranslatableText text : allCreatedTexts) {
            text.reload();
        }
    }

    private static ArrayList<TranslatableText> allCreatedTexts = new ArrayList<>();

    private final String rawText;
    private final String suffix;
    private ArrayList<Container> usedLocations = new ArrayList<>();

    public TranslatableText(String raw) {
        this(raw, "");
    }

    public TranslatableText(String raw, String suffix) {
        this.rawText = raw;
        this.suffix = suffix;
        allCreatedTexts.add(this);
    }

    public String getTranslatedText() {
        return LanguageManager.translate(rawText) + suffix;
    }

    public String getRawText() {
        return rawText;
    }

    public void makeTextOf(Container container) {
        usedLocations.add(container);
        this.reload();
    }

    public void reload() {
        for (Container c : usedLocations) {
            if (c instanceof JFrame frame) {
                frame.setTitle(this.getTranslatedText());
            }
            if (c instanceof AbstractButton button) {
                button.setText(this.getTranslatedText());
            }
            if (c instanceof JButton button) {
                button.setFont(LanguageManager.getSpecialFont(Font.PLAIN, 16));
            }
            if (c instanceof JLabel label) {
                label.setFont(LanguageManager.getNormalFont(Font.BOLD, 16));
                label.setText(this.getTranslatedText());
            }
        }
    }

}
