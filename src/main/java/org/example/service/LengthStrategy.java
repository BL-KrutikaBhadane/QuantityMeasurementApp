package org.example.service;

import org.example.entity.LengthUnit;
import org.example.interfaces.ConversionStrategy;
import org.springframework.stereotype.Service;

@Service
public class LengthStrategy implements ConversionStrategy {

    public double convert(double value, String from, String to) {
        LengthUnit fromUnit = LengthUnit.valueOf(from.toUpperCase());
        LengthUnit toUnit = LengthUnit.valueOf(to.toUpperCase());

        double base = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(base);
    }
}
