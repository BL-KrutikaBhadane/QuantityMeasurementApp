package org.example;

public class Quantity<U extends IMeasurable>{
    private double value;
    private U unit;

    public Quantity(double value, U unit){
        if(Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Invalid numeric value");
        if(unit == null) throw new IllegalArgumentException("This exception is thrown because Unit is provided as null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Quantity)) return false;

        Quantity<?> q = (Quantity<?>) obj;
        if(this.unit.getClass() != q.unit.getClass()) return false;

        return compare((Quantity<U>) q);
    }

    public boolean compare(Quantity<U> thatQuantity){
        return Double.compare(
                this.unit.convertToBaseUnit(this.value),
                thatQuantity.unit.convertToBaseUnit(thatQuantity.value)
        ) == 0;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(this.unit.convertToBaseUnit(this.value));
    }

    @Override
    public String toString() {
        return value+" "+unit;
    }

    public Quantity<U> convertTo(U targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException(" Target unit cannot be null");

        double valueOfBaseUnit = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueOfBaseUnit);

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> secondQuantity){
        if(secondQuantity == null) throw new IllegalArgumentException("Quantity to add cannot be null cannot be null");

        double firstValue = this.unit.convertToBaseUnit(this.value);
        double secondValue = secondQuantity.unit.convertToBaseUnit(secondQuantity.value);
        double sumValue = firstValue + secondValue;

        double resultValue = this.unit.convertFromBaseUnit(sumValue);
        return new Quantity<>(resultValue, unit);
    }

    public Quantity<U> add(Quantity<U> secondQuantity, U targetUnit){
        if(secondQuantity == null) throw new IllegalArgumentException("Quantity to add cannot be null");
        if(targetUnit == null) throw new IllegalArgumentException("Please, Enter a valid targetUnit");
        return addAndConvert(secondQuantity, targetUnit);
    }

    private Quantity<U> addAndConvert(Quantity<U> secondQuantity, U targetUnit) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = secondQuantity.unit.convertToBaseUnit(secondQuantity.value);

        double baseValueInInches = base1 + base2;

        double resultValue = targetUnit.convertFromBaseUnit(baseValueInInches);
        return new Quantity<>(resultValue, targetUnit);
    }
}