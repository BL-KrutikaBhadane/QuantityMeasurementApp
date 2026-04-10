package org.example.service;

import org.example.interfaces.ConversionStrategy;
import org.springframework.stereotype.Component;

@Component
public class VolumeStrategy implements ConversionStrategy {

    @Override
    public double convert(double value, String fromUnit, String toUnit) {

        double baseValue = toBase(value, fromUnit);

        return fromBase(baseValue, toUnit);
    }

    private double toBase(double value, String fromUnit) {
        switch (fromUnit.toUpperCase()) {
            case "LITER":
                return value;

            case "MILLILITER":
                return value / 1000;

            default:
                throw new RuntimeException("Invalid Volume Unit: " + fromUnit);
        }
    }

    private double fromBase(double baseValue, String toUnit) {
        switch (toUnit.toUpperCase()) {
            case "LITER":
                return baseValue;

            case "MILLILITER":
                return baseValue * 1000;

            default:
                throw new RuntimeException("Invalid Volume Unit: " + toUnit);
        }
    }
}
