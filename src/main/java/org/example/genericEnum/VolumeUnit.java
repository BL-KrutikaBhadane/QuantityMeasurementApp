package org.example.genericEnum;

import org.example.IMeasurable;

public enum VolumeUnit implements IMeasurable {
    MILLILITRE(1.0),
    LITRE(1000.0),
    GALLON(3785.412);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return value*conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue/conversionFactor;
    }
}