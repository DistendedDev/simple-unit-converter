package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.conversion.UnitConversionUtil;

import javax.swing.*;
import java.awt.*;

public class AppGUI {

    private final JFrame window = new JFrame();
    private final JButton convertButton = new JButton(Application.LANGUAGE.getSetting("convert", true),
            FileHelper.getImage("convert.png", 20, 20));
    private final JTextField numberEntryField = new JTextField();
    private final TranslatedComboBox unitTypeSelectionBox = new TranslatedComboBox(UnitConversionUtil.getUnitTypes());
    private final TranslatedComboBox unitSelectionBox1 = new TranslatedComboBox();
    private final TranslatedComboBox unitSelectionBox2 = new TranslatedComboBox();
    private final JLabel resultsLabel = new JLabel();
    private final JPanel numberEntryPanel = new JPanel();
    private final JPanel unitSelectionPanel = new JPanel();
    private final JPanel resultsPanel = new JPanel();
    private final JMenuBar menuBar = new JMenuBar();

    public AppGUI() {
        initFrame();
        initMenuBar();
        initNumberField();
        initConvertButton();
        initUnitSelectionBoxes();
        reloadSelectionBoxes();
        addElements();
    }

    public void show() {
        window.setVisible(true);
    }

    //setting up main window
    private void initFrame() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle(Application.LANGUAGE.getSetting("application_name", true));
        window.setMinimumSize(new Dimension(640, 320));
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
    }

    //setting up the convert button
    private void initConvertButton() {
        convertButton.setSize(50, 25);
        convertButton.setHorizontalTextPosition(JButton.LEFT);
        convertButton.setVerticalTextPosition(JButton.CENTER);
        convertButton.setBorder(BorderFactory.createEtchedBorder());
        convertButton.setIconTextGap(5);
        convertButton.setFont(new Font(Application.LANGUAGE.getSetting("FONT2"), Font.PLAIN, 16));
        convertButton.addActionListener(e -> {
            double n;
            try {
                n = Double.parseDouble(numberEntryField.getText());
            } catch (Exception ignored) {
                resultsLabel.setText("Error: input is not a number");
                return;
            }
            resultsLabel.setText(Application.LANGUAGE.getSetting("result", true)
                    + ": "
                    + UnitConversionUtil.convert(n,
                        unitTypeSelectionBox.getSelectedRaw(),
                        unitSelectionBox1.getSelectedRaw(),
                        unitSelectionBox2.getSelectedRaw())
                    + unitSelectionBox2.getSelectedItem());
        });
        convertButton.setFocusable(false);
        convertButton.setEnabled(true);
    }

    //setting up text field
    private void initNumberField() {
        numberEntryField.setPreferredSize(new Dimension(250, 25));
    }

    private void reloadSelectionBoxes() {
        unitSelectionBox1.reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
        unitSelectionBox2.reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
    }

    private void initUnitSelectionBoxes() {
        unitTypeSelectionBox.addActionListener(e -> {
            reloadSelectionBoxes();
        });
    }

    private void initMenuBar() {
        JMenu settingsMenu = new JMenu("Settings");
        JMenu helpMenu = new JMenu("Help");
        JMenu quitMenu = new JMenu("Quit");
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        menuBar.add(quitMenu);
        window.setJMenuBar(menuBar);
    }

    private void addElements() {
        numberEntryPanel.add(numberEntryField);
        numberEntryPanel.add(convertButton);
        numberEntryPanel.setMaximumSize(new Dimension(380, 32));
        window.add(numberEntryPanel);
        unitSelectionPanel.add(unitTypeSelectionBox);
        unitSelectionPanel.add(unitSelectionBox1);
        unitSelectionPanel.add(new JLabel(FileHelper.getImage("arrow.png", 50, 20)));
        unitSelectionPanel.add(unitSelectionBox2);
        unitSelectionPanel.setMaximumSize(new Dimension(380, 32));
        window.add(unitSelectionPanel);
        resultsLabel.setFont(new Font(Application.LANGUAGE.getSetting("FONT1"), Font.BOLD, 16));
        resultsPanel.add(resultsLabel);
        window.add(resultsPanel);
    }

}
