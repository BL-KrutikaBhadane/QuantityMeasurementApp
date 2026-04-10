/**
 * @author:KrutikaBhadane
 */

package org.example.dto;

public class QuantityDTO {

    private double value;
    private String fromUnit;
    private String toUnit;

    public double getValue() {
        return value;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }
}