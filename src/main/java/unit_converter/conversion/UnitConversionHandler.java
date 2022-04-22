package main.java.unit_converter.conversion;

import java.util.HashMap;

public class UnitConversionHandler {

    public static HashMap<String, UnitType> UNITS_HASHMAP = new HashMap<>() {{
        put("Length", new UnitType(new HashMap<String, Double>() {{
            put("mm", 0.001);
            put("cm", 0.01);
            put("m", 1.0);
            put("km", 1000.0);
        }}, "m"));
    }};

    public static double convert(double value, String unitType, String currentUnit, String toConvert) {
        String commonUnit = UNITS_HASHMAP.get(unitType).commonUnit();
        value *= UNITS_HASHMAP.get(unitType).units().get(currentUnit);
        value /= UNITS_HASHMAP.get(unitType).units().get(toConvert);
        return value;
    }

}
