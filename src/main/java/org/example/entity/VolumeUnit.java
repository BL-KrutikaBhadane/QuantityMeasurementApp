/**
 * @author:KrutikaBhadane
 */

package org.example.entity;

import org.example.interfaces.IMeasurable;

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
