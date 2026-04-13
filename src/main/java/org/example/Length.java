package org.example;

public class Length {
    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit){
        if(Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Invalid numeric value");
        if(unit == null) throw new IllegalArgumentException("This exception is thrown because Unit is provided as null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit(){
        return Math.round(value*unit.getConversionFactor() *1000.0)/1000.0;
    }


    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException(" Target unit cannot be null");

        double valueOfBaseUnit = this.value * this.unit.getConversionFactor();
        double convertedValue = Math.round(valueOfBaseUnit / targetUnit.getConversionFactor() *1000.0)/1000.0;
        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length secondLength){
        if(secondLength == null) throw new IllegalArgumentException("Length to add cannot be null cannot be null");
        double firstValue = this.value * this.unit.getConversionFactor();
        double secondValue = secondLength.value * secondLength.unit.getConversionFactor();
        double sumValue = firstValue + secondValue;
        double resultValue = Math.round(sumValue / this.unit.getConversionFactor() *1000.0)/1000.0;
        return new Length(resultValue, this.unit);
    }

    @Override
    public String toString() {
        return ""+value+" "+unit;
    }

    public boolean compare(Length thatLength){
        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Length length = (Length)obj;
        return compare(length);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(convertToBaseUnit());
    }

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }
}