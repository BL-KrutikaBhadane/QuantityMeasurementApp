/**
 * @author:KrutikaBhadane
 */

package org.example.dto;


public enum LengthUnit implements IMeasurableUnit {
    FEET,
    INCHES,
    YARDS,
    CENTIMETERS;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}
