package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.conversion.UnitConversionUtil;
import main.java.unit_converter.util.WebHelper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AppGUI {

    private final JFrame window = new JFrame();
    private final JButton convertButton = new JButton(Application.LANGUAGE.getSetting("convert", true),
            FileHelper.getImage("convert.png", 20, 20));
    private final JTextField numberEntryField = new JTextField();
    private final TranslatedComboBox unitTypeSelectionBox = new TranslatedComboBox(UnitConversionUtil.getUnitTypes());
    private final TranslatedComboBox unitSelectionBox1 = new TranslatedComboBox();
    private final TranslatedComboBox unitSelectionBox2 = new TranslatedComboBox();
    private final JLabel resultsLabel = new JLabel();
    private final JPanel numberEntryPanel = new JPanel(new FlowLayout());
    private final JPanel unitSelectionPanel = new JPanel(new FlowLayout());
    private final JPanel resultsPanel = new JPanel();
    private final JMenuBar menuBar = new JMenuBar();

    public AppGUI(int width, int height) {
        initFrame(width, height);
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
    private void initFrame(int width, int height) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle(Application.LANGUAGE.getSetting("application_name", true) + " " + Application.VERSION);
        window.setMinimumSize(new Dimension(width, height));
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
    }

    //setting up the convert button
    private void initConvertButton() {
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
        numberEntryField.setPreferredSize(new Dimension(window.getWidth() / 2, 25));
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
        JMenu settingsMenu = new JMenu("Settings") {{
            add(new JMenuItem("open settings folder") {{
                addActionListener(e -> FileHelper.openResourceDirectory("settings"));
            }});
        }};
        JMenu helpMenu = new JMenu("Help") {{
            add(new JMenuItem("report issue") {{
                addActionListener(e -> WebHelper.openWebpage("https://github.com/DistendedDev/simple-unit-converter/issues"));
            }});
            add(new JMenuItem("github page") {{
                addActionListener(e -> WebHelper.openWebpage("https://github.com/DistendedDev/simple-unit-converter"));
            }});
        }};
        JMenu quitMenu = new JMenu("Quit") {{
            add(new JMenuItem("Exit") {{
                addActionListener(e -> System.exit(0));
            }});
        }};
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        menuBar.add(quitMenu);
        window.setJMenuBar(menuBar);
    }

    private void addElements() {
        numberEntryPanel.add(numberEntryField);
        numberEntryPanel.add(convertButton);
        numberEntryPanel.setMaximumSize(new Dimension(window.getWidth(), 32));
        window.add(numberEntryPanel);
        unitSelectionPanel.add(unitTypeSelectionBox);
        unitSelectionPanel.add(unitSelectionBox1);
        unitSelectionPanel.add(new JLabel(FileHelper.getImage("arrow.png", 50, 20)));
        unitSelectionPanel.add(unitSelectionBox2);
        unitSelectionPanel.setMaximumSize(new Dimension(window.getWidth(), 32));
        window.add(unitSelectionPanel);
        resultsLabel.setFont(new Font(Application.LANGUAGE.getSetting("FONT1"), Font.BOLD, 16));
        resultsPanel.add(resultsLabel);
        window.add(resultsPanel);
    }

}
