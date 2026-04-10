package org.example.service;

import org.example.interfaces.ConversionStrategy;
import org.springframework.stereotype.Service;

@Service
public class TemperatureStrategy implements ConversionStrategy {

    public double convert(double value, String from, String to) {

        if (from.equalsIgnoreCase("CELSIUS") && to.equalsIgnoreCase("FAHRENHEIT"))
            return (value * 9/5) + 32;

        if (from.equalsIgnoreCase("FAHRENHEIT") && to.equalsIgnoreCase("CELSIUS"))
            return (value - 32) * 5/9;

        return value;
    }
}
