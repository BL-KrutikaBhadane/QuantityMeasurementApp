package org.example;

import org.example.LengthUnit;
public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2
    ){
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toTargetUnit
    ){
        Length l = new Length(value, fromUnit);
        return l.convertTo(toTargetUnit);
    }

    public static Length demonstrateLengthConversion(Length length1, LengthUnit toTargetUnit){
        return length1.convertTo(toTargetUnit);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2){
        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit){
        return length1.add(length2, targetUnit);
    }



    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2){
        return weight1.equals(weight2);
    }

    public static boolean demonstrateWeightComparison(
            double value1, WeightUnit unit1,
            double value2, WeightUnit unit2
    ){
        Weight weight1 = new Weight(value1, unit1);
        Weight weight2 = new Weight(value2, unit2);
        return weight1.equals(weight2);
    }

    public static Weight demonstrateWeightConversion(
            double value,
            WeightUnit fromUnit,
            WeightUnit toTargetUnit
    ){
        Weight weight = new Weight(value, fromUnit);
        return weight.convertTo(toTargetUnit);
    }

    public static Weight demonstrateWeightConversion(Weight weight1, WeightUnit toTargetUnit){
        return weight1.convertTo(toTargetUnit);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2){
        return weight1.add(weight2);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit){
        return weight1.add(weight2, targetUnit);
    }

    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(length1+" is equals to "+length2+" = "+demonstrateLengthEquality(length1, length2));
        System.out.println(demonstrateLengthComparison(
                1.0, LengthUnit.FEET,
                12.0,LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(5.0, LengthUnit.YARDS, LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(length1, LengthUnit.INCHES));
        System.out.println(length1+" + "+length2+" = "+demonstrateLengthAddition(length1, length2));
        System.out.println(length1+" + "+length2+" = "+demonstrateLengthAddition(length1, length2, LengthUnit.CENTIMETERS));



        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println(weight1+" is equals to "+ weight2 +" = "+demonstrateWeightEquality(weight1, weight2));
        System.out.println(demonstrateWeightComparison(
                1.0, WeightUnit.KILOGRAM,
                1000.0,WeightUnit.GRAM));
        System.out.println(demonstrateWeightConversion(5.0, WeightUnit.POUND, WeightUnit.KILOGRAM));
        System.out.println(demonstrateWeightConversion(weight1, WeightUnit.GRAM));
        System.out.println(weight1+" + "+ weight2 +" = "+demonstrateWeightAddition(weight1, weight2));
        System.out.println(weight1+" + "+ weight2 +" = "+demonstrateWeightAddition(weight1, weight2, WeightUnit.MILLIGRAM));
    }
}