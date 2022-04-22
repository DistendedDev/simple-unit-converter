package main.java.unit_converter.conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class UnitConversionUtil {

    public static HashMap<String, UnitType> UNITS = new LinkedHashMap<>() {{
        put("Length", new UnitType(new LinkedHashMap<>() {{
            put("mm", 0.001);
            put("cm", 0.01);
            put("m", 1.0);
            put("km", 1000.0);
        }}, "m"));
        put("Time", new UnitType(new LinkedHashMap<>() {{
            put("ms", 1.0/1000/60);
            put("sec", 1.0/60);
            put("min", 1.0);
            put("hr", 60.0);
        }}, "min"));
    }};

    public static double convert(double value, String unitType, String currentUnit, String toConvert) {
        String commonUnit = UNITS.get(unitType).commonUnit();
        value *= UNITS.get(unitType).units().get(currentUnit);
        value /= UNITS.get(unitType).units().get(toConvert);
        return value;
    }

    public static ArrayList<String> getUnitTypes() {
        return new ArrayList<>(UNITS.keySet());
    }

}
