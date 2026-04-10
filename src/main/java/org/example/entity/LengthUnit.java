/**
 * @author:KrutikaBhadane
 */

package org.example.entity;

import org.example.interfaces.IMeasurable;

public enum LengthUnit implements IMeasurable {
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
        return value*conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue/conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for(LengthUnit unit : LengthUnit.values()){
            if(unit.name().equalsIgnoreCase(unitName)){
                return unit;
            }
        }

        throw new IllegalArgumentException("Invalid length unit: "+unitName);
    }
}
