package main.java.unit_converter.conversion;

import java.util.ArrayList;
import java.util.HashMap;

public record UnitType(HashMap<String, Double> units, String commonUnit) {
    public UnitType {
        if (!units.containsKey(commonUnit)) throw new IllegalArgumentException("commonUnit not in units list");
    }
}
