/**
 * @author:KrutikaBhadane
 */

package org.example.entity;

import org.example.interfaces.IMeasurable;
import org.example.interfaces.SupportsArithmetic;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            (Double c) -> c,
            (Double c) -> c
    ),

    FAHRENHEIT(
            (Double f) -> (f -32) * 5/9,
            (Double c) -> (c *9 / 5) + 32
    ),
    KELVIN(
            (Double k) -> k - 273.15,
            (Double c) -> c + 273.15
    );

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    TemperatureUnit(
            Function<Double, Double> toBase,
            Function<Double, Double> fromBase) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
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

    @Override
    public SupportsArithmetic supportsArithmetic() {
        return () -> false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new IllegalArgumentException("Temperature does not support "+ operation + "operation.");
    }
}
