package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.util.FileHelper;
import main.java.unit_converter.conversion.UnitConversionUtil;
import main.java.unit_converter.util.WebHelper;

import javax.swing.*;
import java.awt.*;

public class AppGUI {

    private final JButton convertButton =
            new JButton(Application.LANGUAGE.getSetting("convert", true),
                    FileHelper.getImage("convert.png", 20, 20)) {{
        setHorizontalTextPosition(JButton.LEFT);
        setVerticalTextPosition(JButton.CENTER);
        setBorder(BorderFactory.createEtchedBorder());
        setIconTextGap(5);
        setFont(new Font(Application.LANGUAGE.getSetting("FONT2"), Font.PLAIN, 16));
        addActionListener(e -> {
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

    private final JPanel unitSelectionPanel = new JPanel(new FlowLayout()) {{
        add(unitTypeSelectionBox);
        add(unitSelectionBox1);
        add(new JLabel(FileHelper.getImage("arrow.png", 50, 20)));
        add(unitSelectionBox2);
    }};


    private final JLabel resultsLabel = new JLabel() {{
        setFont(new Font(Application.LANGUAGE.getSetting("FONT1"), Font.BOLD, 16));
    }};

    private final JPanel resultsPanel = new JPanel() {{
        add(resultsLabel);
    }};

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

    private final JFrame window = new JFrame() {{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Application.LANGUAGE.getSetting("application_name", true) + " " + Application.VERSION);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(numberEntryPanel);
        add(unitSelectionPanel);
        add(resultsPanel);
        setJMenuBar(menuBar);
    }};

    public AppGUI(int width, int height) {
        window.setMinimumSize(new Dimension(width, height));
        window.setLocationRelativeTo(null);
        numberEntryField.setPreferredSize(new Dimension(window.getWidth() / 2, 25));
        numberEntryPanel.setMaximumSize(new Dimension(window.getWidth(), 32));
        unitSelectionPanel.setMaximumSize(new Dimension(window.getWidth(), 32));
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

}
