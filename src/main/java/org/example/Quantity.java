package org.example;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable>{
    private double value;
    private U unit;

    public Quantity(double value, U unit){
        if(Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
        if(unit == null)
            throw new IllegalArgumentException("This exception is thrown because Unit is provided as null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /* =========================================
       UC13 STEP 1 : Central Arithmetic Enum
       ========================================= */

    public enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    /* =========================================
       UC13 STEP 2 : Central Validation
       ========================================= */

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
    ) {
        if(other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Please, Enter a valid targetUnit");

        if (targetUnitRequired && !this.unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Target unit category mismatch");
    }

    /* =========================================
       UC13 STEP 3 : Central Arithmetic Engine
       ========================================= */

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation,
            U targetUnit,
            boolean targetUnitRequired
    ) {

        validateArithmeticOperands(other, targetUnit, targetUnitRequired);
        this.unit.validateOperationSupport(operation.name());

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    /* =========================================
       Existing Methods (Now DRY + Centralized)
       ========================================= */

    public Quantity<U> convertTo(U targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException(" Target unit cannot be null");

        double valueOfBaseUnit = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueOfBaseUnit);
        return new Quantity<>(round(convertedValue), targetUnit);
    }

    /* ---------------- ADD ---------------- */

    public Quantity<U> add(Quantity<U> secondQuantity){
        double base = performBaseArithmetic(secondQuantity, ArithmeticOperation.ADD, null, false);
        double resultValue = this.unit.convertFromBaseUnit(base);
        return new Quantity<>(round(resultValue), unit);
    }

    public Quantity<U> add(Quantity<U> secondQuantity, U targetUnit){
        double base = performBaseArithmetic(secondQuantity, ArithmeticOperation.ADD, targetUnit, true);
        double resultValue = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(resultValue), targetUnit);
    }

    /* ---------------- SUBTRACT ---------------- */

    public Quantity<U> subtract(Quantity<U> secondQuantity){
        double base = performBaseArithmetic(secondQuantity, ArithmeticOperation.SUBTRACT, null, false);
        double resultValue = this.unit.convertFromBaseUnit(base);
        return new Quantity<>(round(resultValue), this.unit);
    }

    public Quantity<U> subtract(Quantity<U> secondQuantity, U targetUnit){
        double base = performBaseArithmetic(secondQuantity, ArithmeticOperation.SUBTRACT, targetUnit, true);
        double resultValue = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(resultValue), targetUnit);
    }

    /* ---------------- DIVIDE ---------------- */

    public double divide(Quantity<U> secondQuantity){
        double base = performBaseArithmetic(secondQuantity, ArithmeticOperation.DIVIDE, null, false);
        return round(base);
    }

    /* =========================================
       Existing Equality Logic (UNCHANGED)
       ========================================= */

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
                round(this.unit.convertToBaseUnit(this.value)),
                round(thatQuantity.unit.convertToBaseUnit(thatQuantity.value))
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

    private double round(double v){
        return Math.round(v * 1000.0) / 1000.0;
    }
}