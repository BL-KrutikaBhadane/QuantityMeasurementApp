package org.example;

public enum LengthUnit{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return Math.round(value*conversionFactor *1000.0)/1000.0;
    }

    public double convertFromBaseUnit(double baseValue) {
        return Math.round(baseValue/conversionFactor *1000.0)/1000.0;
    }
}
