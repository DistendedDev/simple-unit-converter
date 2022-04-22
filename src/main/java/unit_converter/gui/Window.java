package main.java.unit_converter.gui;

import main.java.unit_converter.util.ImageHelper;
import main.java.unit_converter.conversion.UnitConversionHandler;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JButton convertButton = new JButton("Convert", ImageHelper.getImage("convert.png", 20, 20));
    private JTextField numberEntryField = new JTextField();
    private JComboBox unitTypeSelectionBox = new JComboBox(UnitConversionHandler.UNITS_HASHMAP.keySet().toArray(new String[0]));
    private JComboBox unitSelectionBox1 = new JComboBox();
    private JComboBox unitSelectionBox2 = new JComboBox();
    private JLabel resultsLabel = new JLabel();
    private JPanel numberEntryPanel = new JPanel();
    private JPanel unitSelectionPanel = new JPanel();
    private JPanel resultsPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();

    public Window(String name) {
        initFrame(name);
        initMenuBar();
        initNumberField();
        initConvertButton();
        initUnitSelectionBoxes();
        reloadSelectionBoxes();
        addElements();
    }

    //setting up main window
    private void initFrame(String name) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(name);
        this.setMinimumSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }

    //setting up the convert button
    private void initConvertButton() {
        convertButton.setSize(50, 25);
        convertButton.setHorizontalTextPosition(JButton.LEFT);
        convertButton.setVerticalTextPosition(JButton.CENTER);
        convertButton.setBorder(BorderFactory.createEtchedBorder());
        convertButton.setIconTextGap(5);
        convertButton.setFont(new Font("Cooper Black", Font.PLAIN, 16));
        convertButton.addActionListener(e -> {
            double n = Double.parseDouble(numberEntryField.getText());
            resultsLabel.setText("Result: " + UnitConversionHandler.convert(n, (String) unitTypeSelectionBox.getSelectedItem(), (String) unitSelectionBox1.getSelectedItem(), (String) unitSelectionBox2.getSelectedItem())+ unitSelectionBox2.getSelectedItem());
        });
        convertButton.setFocusable(false);
        convertButton.setEnabled(true);
    }

    //setting up text field
    private void initNumberField() {
        numberEntryField.setPreferredSize(new Dimension(250, 25));
    }

    private void reloadSelectionBoxes() {
        unitSelectionBox1.removeAllItems();
        for (String s : UnitConversionHandler.UNITS_HASHMAP.get(unitTypeSelectionBox.getSelectedItem()).units().keySet()) {
            unitSelectionBox1.addItem(s);
        }
        unitSelectionBox2.removeAllItems();
        for (String s : UnitConversionHandler.UNITS_HASHMAP.get(unitTypeSelectionBox.getSelectedItem()).units().keySet()) {
            unitSelectionBox2.addItem(s);
        }
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
        this.setJMenuBar(menuBar);
    }

    private void addElements() {
        numberEntryPanel.add(numberEntryField);
        numberEntryPanel.add(convertButton);
        numberEntryPanel.setMaximumSize(new Dimension(380, 32));
        this.add(numberEntryPanel);
        unitSelectionPanel.add(unitTypeSelectionBox);
        unitSelectionPanel.add(unitSelectionBox1);
        unitSelectionPanel.add(unitSelectionBox2);
        unitSelectionPanel.setMaximumSize(new Dimension(380, 32));
        this.add(unitSelectionPanel);
        resultsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultsPanel.add(resultsLabel);
        this.add(resultsPanel);
    }

}
