/**
 * @author:KrutikaBhadane
 */

package org.example.interfaces;

import org.example.service.MeasurementFactory;

public interface IMeasurable {
    public double convertToBaseUnit(double value);
    public double convertFromBaseUnit(double baseValue);

    String getUnitName();
    public String getMeasurementType();
    public IMeasurable getUnitInstance(String unitName);

    static IMeasurable getUnit(String measurementType, String unitName) {
        return MeasurementFactory.getUnit(measurementType, unitName);
    }

    default SupportsArithmetic supportsArithmetic(){
        return ()-> true;
    }

    default void validateOperationSupport(String operation){
    }
}
