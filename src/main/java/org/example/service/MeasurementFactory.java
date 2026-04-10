/**
 * @author:KrutikaBhadane
 */

package org.example.service;

import org.example.entity.LengthUnit;
import org.example.entity.TemperatureUnit;
import org.example.entity.VolumeUnit;
import org.example.entity.WeightUnit;
import org.example.interfaces.IMeasurable;

public class MeasurementFactory {

    public static IMeasurable getUnit(String measurementType, String unitName) {

        switch (measurementType.toLowerCase()) {

            case "length":
                return LengthUnit.valueOf(unitName.toUpperCase());

            case "weight":
                return WeightUnit.valueOf(unitName.toUpperCase());

            case "volume":
                return VolumeUnit.valueOf(unitName.toUpperCase());

            case "temperature":
                return TemperatureUnit.valueOf(unitName.toUpperCase());

            default:
                throw new IllegalArgumentException("Invalid measurement type: " + measurementType);
        }
    }
}
