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

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Length length = (Length)obj;
        return compare(length);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value+" "+unit;
    }

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException(" Target unit cannot be null");

        double valueOfBaseUnit = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueOfBaseUnit);

        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length secondLength){
        if(secondLength == null) throw new IllegalArgumentException("Length to add cannot be null cannot be null");
        double firstValue = this.unit.convertToBaseUnit(this.value);
        double secondValue = secondLength.unit.convertToBaseUnit(secondLength.value);
        double sumValue = firstValue + secondValue;

        double resultValue = this.unit.convertFromBaseUnit(sumValue);
        return new Length(resultValue, this.unit);
    }

    public Length add(Length secondLength, LengthUnit targetUnit){
        if(secondLength == null) throw new IllegalArgumentException("Length to add cannot be null");
        if(targetUnit == null) throw new IllegalArgumentException("Please, Enter a valid targetUnit");
        return addAndConvert(secondLength, targetUnit);
    }

    private Length addAndConvert(Length secondLength, LengthUnit targetUnit) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = secondLength.unit.convertToBaseUnit(secondLength.value);

        double baseValueInInches = base1 + base2;

        double resultValue = targetUnit.convertFromBaseUnit(baseValueInInches);
        return new Length(resultValue, targetUnit);
    }

    public double convertToBaseUnit(){
        return this.unit.convertToBaseUnit(this.value);
    }

    private double convertFromBaseToTargetUnit(double baseValueInInches, LengthUnit targetUnit) {
        return targetUnit.convertFromBaseUnit(baseValueInInches);
    }


    public boolean compare(Length thatLength){
        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    public static void main(String[] args) {
        Length l = new Length(0.0, LengthUnit.FEET);
        System.out.println(l.convertTo(LengthUnit.FEET));
    }
}