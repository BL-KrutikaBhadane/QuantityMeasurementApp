/**
 * @author:KrutikaBhadane
 */

package org.example.service;

import org.example.entity.LengthUnit;
import org.example.entity.TemperatureUnit;
import org.example.entity.VolumeUnit;
import org.example.entity.WeightUnit;
import org.example.interfaces.ConversionStrategy;
import org.example.interfaces.IMeasurable;
import org.example.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementFactory {

    @Autowired
    private LengthStrategy lengthStrategy;

    @Autowired
    private WeightStrategy weightStrategy;

    @Autowired
    private TemperatureStrategy temperatureStrategy;

    @Autowired
    private VolumeStrategy volumeStrategy;

    public ConversionStrategy getStrategy(String fromUnit) {

        if (EnumUtils.isLengthUnit(fromUnit))
            return lengthStrategy;

        if (EnumUtils.isWeightUnit(fromUnit))
            return weightStrategy;

        if (EnumUtils.isTemperatureUnit(fromUnit))
            return temperatureStrategy;

        if (EnumUtils.isVolumeUnit(fromUnit))
            return volumeStrategy;

        throw new RuntimeException("Invalid Unit");
    }

    public static IMeasurable getUnit(String measurementType, String unitName) {

        switch (measurementType.toUpperCase()) {

            case "LENGTH":
                return LengthUnit.valueOf(unitName.toUpperCase());

            case "WEIGHT":
                return WeightUnit.valueOf(unitName.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unitName.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unitName.toUpperCase());

            default:
                throw new RuntimeException("Invalid Measurement Type");
        }
    }
}