/**
 * @author:KrutikaBhadane
 */

package org.example.dto;


public enum WeightUnit implements IMeasurableUnit {
    MILLIGRAM,
    GRAM,
    KILOGRAM,
    POUND,
    TONNE;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}
