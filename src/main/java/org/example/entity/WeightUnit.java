/**
 * @author:KrutikaBhadane
 */

package org.example.entity;

import org.example.interfaces.IMeasurable;

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

    @Override
    public double convertToBaseUnit(double value){
        return value*conversionFactor;
    }

    @Override
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
