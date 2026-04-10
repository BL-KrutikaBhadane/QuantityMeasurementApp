package org.example.util;

import org.example.entity.LengthUnit;
import org.example.entity.TemperatureUnit;
import org.example.entity.VolumeUnit;
import org.example.entity.WeightUnit;

import java.util.Arrays;

public class EnumUtils {

    public static boolean isLengthUnit(String unit) {
        return Arrays.stream(LengthUnit.values())
                .anyMatch(u -> u.name().equalsIgnoreCase(unit));
    }

    public static boolean isWeightUnit(String unit) {
        return Arrays.stream(WeightUnit.values())
                .anyMatch(u -> u.name().equalsIgnoreCase(unit));
    }

    public static boolean isTemperatureUnit(String unit) {
        return Arrays.stream(TemperatureUnit.values())
                .anyMatch(u -> u.name().equalsIgnoreCase(unit));
    }

    public static boolean isVolumeUnit(String unit) {
        return Arrays.stream(VolumeUnit.values())
                .anyMatch(u -> u.name().equalsIgnoreCase(unit));
    }
}
