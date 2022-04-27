package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.conversion.UnitConversionUtil;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.util.WebHelper;
import main.java.unit_converter.util.settings.LanguageManager;

import javax.swing.*;
import java.awt.*;

public class AppGUI {

    public AppGUI(int width, int height) {
        window.setMinimumSize(new Dimension(width, height));
        window.setLocationRelativeTo(null);
        numberEntryField.setPreferredSize(new Dimension(window.getWidth() / 2, 25));
        numberEntryPanel.setMaximumSize(new Dimension(window.getWidth(), 32));
        unitSelectionPanel.setMaximumSize(new Dimension(window.getWidth(), 48));
        resultsPanel.setMaximumSize(new Dimension(window.getWidth(), 64));
        footerPanel.setMaximumSize(new Dimension(window.getWidth(), 48));
    }

    public void show() {
        window.setVisible(true);
    }

    private void reloadSelectionBoxes() {
        unitSelectionBox1.reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
        unitSelectionBox2.reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
    }

    private double result;

    private final JMenuBar menuBar = new JMenuBar() {{
        add(new JMenu("Settings") {{
            add(new JMenuItem("open settings folder") {{
                addActionListener(e -> FileHelper.openResourceDirectory("settings"));
            }});
        }});
        add(new JMenu("Help") {{
            add(new JMenuItem("report issue") {{
                addActionListener(e -> WebHelper.openWebpage("https://github.com/DistendedDev/simple-unit-converter/issues"));
            }});
            add(new JMenuItem("github page") {{
                addActionListener(e -> WebHelper.openWebpage("https://github.com/DistendedDev/simple-unit-converter"));
            }});
        }});
        add(new JMenu("Quit") {{
            add(new JMenuItem("Exit") {{
                addActionListener(e -> System.exit(0));
            }});
        }});
    }};

    private final JButton convertButton = new JButton(LanguageManager.translate("convert"),
                    FileHelper.getImage("convert.png", 20, 20)) {{
        setHorizontalTextPosition(JButton.LEFT);
        setVerticalTextPosition(JButton.CENTER);
        setBorder(BorderFactory.createEtchedBorder());
        setIconTextGap(5);
        setFont(LanguageManager.getSpecialFont(Font.BOLD,16));
        addActionListener(e -> {
            try {
                result = Double.parseDouble(numberEntryField.getText());
            } catch (Exception ignored) {
                resultsLabel.setText("Error: input is not a number");
                return;
            }
            resultsLabel.setText(
                    UnitConversionUtil.convert(result,
                    unitTypeSelectionBox.getSelectedRaw(),
                    unitSelectionBox1.getSelectedRaw(),
                    unitSelectionBox2.getSelectedRaw())
                    + unitSelectionBox2.getSelectedString());
        });
        setFocusable(false);
        setEnabled(true);
    }};

    private final JTextField numberEntryField = new JTextField();

    private final TranslatedComboBox unitTypeSelectionBox = new TranslatedComboBox(UnitConversionUtil.getUnitTypes()) {{
        addActionListener(e -> reloadSelectionBoxes());
    }};

    private final TranslatedComboBox unitSelectionBox1 = new TranslatedComboBox() {{
        reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
    }};

    private final TranslatedComboBox unitSelectionBox2 = new TranslatedComboBox() {{
        reload(UnitConversionUtil.getUnits(unitTypeSelectionBox.getSelectedRaw()),
                UnitConversionUtil.getCommonUnit(unitTypeSelectionBox.getSelectedRaw()));
    }};

    private final JPanel numberEntryPanel = new JPanel(new FlowLayout()) {{
        add(numberEntryField);
        add(convertButton);
    }};

    private final JPanel conversionUnitSelectionPanel  = new JPanel(new FlowLayout()) {{
        add(unitSelectionBox1);
        add(new JLabel(FileHelper.getImage("arrow.png", 50, 20)));
        add(unitSelectionBox2);
        setBorder(BorderFactory.createEtchedBorder());
    }};

    private final JPanel unitSelectionPanel = new JPanel(new FlowLayout()) {{
        add(unitTypeSelectionBox);
        add(conversionUnitSelectionPanel);
    }};


    private final JLabel resultsLabel = new JLabel() {{
        setFont(LanguageManager.getNormalFont(Font.BOLD, 16));
    }};

    private final JPanel resultsPanel = new JPanel(new GridLayout(2, 1)) {{
        add(new JLabel(LanguageManager.translate("result") + ":") {{
            setFont(LanguageManager.getNormalFont(Font.PLAIN, 16));
        }});
        add(resultsLabel);
        setBorder(BorderFactory.createEtchedBorder());
    }};

    private final JPanel footerPanel = new JPanel() {{
        add(new JButton("use result") {{
            addActionListener(e -> numberEntryField.setText(result + ""));
            setFocusable(false);
        }});
    }};

    private final JFrame window = new JFrame() {{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(LanguageManager.translate("application_name") + " " + Application.VERSION);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(numberEntryPanel);
        add(unitSelectionPanel);
        add(resultsPanel);
        add(footerPanel);
        setJMenuBar(menuBar);
    }};

}
