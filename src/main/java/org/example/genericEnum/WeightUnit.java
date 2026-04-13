package org.example.genericEnum;

import org.example.IMeasurable;

public enum WeightUnit implements IMeasurable {
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return Math.round(value*this.conversionFactor *1000.0)/1000.0;
    }

    public double convertFromBaseUnit(double baseValue) {
        return Math.round(baseValue/this.conversionFactor *1000.0)/1000.0;
    }
}