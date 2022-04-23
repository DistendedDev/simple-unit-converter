package main.java.unit_converter.gui;

import main.java.unit_converter.Application;
import main.java.unit_converter.conversion.UnitConversionUtil;

import javax.swing.*;

public class UnitSelectionBox extends JComboBox<String> {

    private JComboBox<String> unitTypeSelectionBox;

    public UnitSelectionBox(JComboBox<String> unitTypeSelectionBox) {
        super();
        this.unitTypeSelectionBox = unitTypeSelectionBox;
    }

    public void reload() {
        reload(unitTypeSelectionBox);
    }

    public void reload(JComboBox<String> unitTypeSelectionBox) {
        this.removeAllItems();
        for (String s : UnitConversionUtil.getUnits((String) unitTypeSelectionBox.getSelectedItem())) {
            this.addItem(Application.LANGUAGE.getSetting(s, true));
            if (s.equals(UnitConversionUtil.getCommonUnit((String) unitTypeSelectionBox.getSelectedItem()))) {
                this.setSelectedItem(Application.LANGUAGE.getSetting(s, true));
            }
        }
    }

    public String getSelectedUnit() {
        return (String) this.getSelectedItem();
    }

}
