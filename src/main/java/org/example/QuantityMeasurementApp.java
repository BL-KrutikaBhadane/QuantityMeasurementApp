package org.example;

import org.example.genericEnum.LengthUnit;
import org.example.genericEnum.WeightUnit;

public class QuantityMeasurementApp {
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> boolean demonstrateComparison(
            double value1, U unit1,
            double value2, U unit2
    ){
        Quantity<U> quantity1 = new Quantity<>(value1, unit1);
        Quantity<U> quantity2 = new Quantity<>(value2, unit2);
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            double value,
            U fromUnit,
            U toTargetUnit
    ){
        Quantity<U> quantity = new Quantity<>(value, fromUnit);
        return quantity.convertTo(toTargetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U toTargetUnit){
        return quantity.convertTo(toTargetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {
        System.out.println("=== GENERIC METHOD TESTING ===\n");

        // ---------- LENGTH TESTS ----------
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.YARDS);

        // Equality
        System.out.println("Equality (1 FEET == 12 INCHES): " +
                demonstrateEquality(q1, q2));

        // Comparison using values + units
        System.out.println("Comparison (1 FEET , 12 INCHES): " +
                demonstrateComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES));

        // Conversion
        System.out.println("Conversion (1 FEET -> INCHES): " +
                demonstrateConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println("Conversion (q2 -> FEET): " +
                demonstrateConversion(q2, LengthUnit.FEET));

        // Addition
        System.out.println("Addition (1 FEET + 12 INCHES): " +
                demonstrateAddition(q1, q2));

        System.out.println("Addition (1 FEET + 12 INCHES in INCHES): " +
                demonstrateAddition(q1, q2, LengthUnit.INCHES));

        System.out.println("Addition (1 FEET + 1 YARD in FEET): " +
                demonstrateAddition(q1, q3, LengthUnit.FEET));

        // ---------- WEIGHT TESTS ----------
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        // Equality
        System.out.println("\nEquality (1 KG == 1000 GRAM): " +
                demonstrateEquality(w1, w2));

        // Comparison
        System.out.println("Comparison (1 KG , 1000 GRAM): " +
                demonstrateComparison(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM));

        // Conversion
        System.out.println("Conversion (1 KG -> GRAM): " +
                demonstrateConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM));

        // Addition
        System.out.println("Addition (1 KG + 1000 GRAM in KG): " +
                demonstrateAddition(w1, w2, WeightUnit.KILOGRAM));

        System.out.println("\n=== TYPE SAFETY DEMO ===");

        System.out.println("Cross-category comparison blocked at compile-time ✅");
    }
}