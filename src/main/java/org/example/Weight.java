package org.example;

import org.example.genericEnum.WeightUnit;

public class Weight {
    private double value;
    private WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if(Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Invalid numeric value");
        if(unit == null) throw new IllegalArgumentException("This exception is thrown because Unit is provided as null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public boolean compare(Weight thatWeight){
        return Double.compare(
                this.convertToBaseUnit(),
                thatWeight.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Weight weight = (Weight)obj;
        return compare(weight);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value+" "+unit;
    }

    public Weight convertTo(WeightUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException(" Target unit cannot be null");

        double valueOfBaseUnit = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueOfBaseUnit);

        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight secondWeight){
        if(secondWeight == null) throw new IllegalArgumentException("Length to add cannot be null cannot be null");
        double firstValue = this.unit.convertToBaseUnit(this.value);
        double secondValue = secondWeight.unit.convertToBaseUnit(secondWeight.value);
        double sumValue = firstValue + secondValue;

        double resultValue = this.unit.convertFromBaseUnit(sumValue);
        return new Weight(resultValue, this.unit);
    }

    public Weight add(Weight secondWeight, WeightUnit targetUnit){
        if(secondWeight == null) throw new IllegalArgumentException("Length to add cannot be null");
        if(targetUnit == null) throw new IllegalArgumentException("Please, Enter a valid targetUnit");
        return addAndConvert(secondWeight, targetUnit);
    }

    private Weight addAndConvert(Weight secondWeight, WeightUnit targetUnit) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = secondWeight.unit.convertToBaseUnit(secondWeight.value);

        double baseValueInInches = base1 + base2;

        double resultValue = targetUnit.convertFromBaseUnit(baseValueInInches);
        return new Weight(resultValue, targetUnit);
    }

    public double convertToBaseUnit(){
        return this.unit.convertToBaseUnit(this.value);
    }

    private double convertFromBaseToTargetUnit(double baseValueInInches, WeightUnit targetUnit) {
        return targetUnit.convertFromBaseUnit(baseValueInInches);
    }
}
