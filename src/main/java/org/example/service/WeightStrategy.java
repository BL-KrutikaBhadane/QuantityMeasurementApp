package org.example.service;

import org.example.entity.WeightUnit;
import org.example.interfaces.ConversionStrategy;
import org.springframework.stereotype.Service;

@Service
public class WeightStrategy implements ConversionStrategy {

    public double convert(double value, String from, String to) {

        WeightUnit fromUnit = WeightUnit.valueOf(from.toUpperCase());
        WeightUnit toUnit = WeightUnit.valueOf(to.toUpperCase());

        double base = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(base);
    }

    private double fromBase(double baseValue, String toUnit) {
        switch (toUnit.toUpperCase()) {

            case "KG":
            case "KILOGRAM":
                return baseValue;

            case "GRAM":
                return baseValue * 1000;

            default:
                throw new RuntimeException("Invalid Weight Unit: " + toUnit);
        }
    }

    private double toBase(double value, String fromUnit) {
        switch (fromUnit.toUpperCase()) {

            case "KG":
            case "KILOGRAM":
                return value;

            case "GRAM":
                return value / 1000;

            default:
                throw new RuntimeException("Invalid Weight Unit: " + fromUnit);
        }
    }
}
