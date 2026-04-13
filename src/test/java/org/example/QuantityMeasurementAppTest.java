package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.example.LengthUnit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class QuantityMeasurementAppTest {
    @Test
    public void testEquality_FeetToFeet_SameValue(){
        Length f1 = new Length(2.0, LengthUnit.FEET);
        Length f2 = new Length(2.0, LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue(){
        Length f1 = new Length(2.0, LengthUnit.FEET);
        Length f2 = new Length(1.0, LengthUnit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test
    public void testEquality_InchToInch_SameValue(){
        Length in1 = new Length(6.0, LengthUnit.INCHES);
        Length in2 = new Length(6.0, LengthUnit.INCHES);
        assertEquals(in1, in2);
    }

    @Test
    public void testEquality_InchToInch_DifferentValue(){
        Length in1 = new Length(6.0, LengthUnit.INCHES);
        Length in2 = new Length(1.0, LengthUnit.INCHES);
        assertNotEquals(in1, in2);
    }

    @Test
    public void testEquality_YardToYard_SameValue(){
        Length y1 = new Length(2.0,LengthUnit.YARDS);
        Length y2 = new Length(2.0, LengthUnit.YARDS);
        assertEquals(y1, y2);
    }

    @Test
    public void testEquality_YardToYard_DifferentValue(){
        Length y1 = new Length(2.0,LengthUnit.YARDS);
        Length y2 = new Length(1.0, LengthUnit.YARDS);
        assertNotEquals(y1, y2);
    }

    @Test
    public void testEquality_CentimetersToCentimeters_SameValue(){
        Length cm1 = new Length(2.0,LengthUnit.CENTIMETERS);
        Length cm2 = new Length(2.0, LengthUnit.CENTIMETERS);
        assertEquals(cm1, cm2);
    }

    @Test
    public void testEquality_CentimetersToCentimeters_DifferentValue(){
        Length cm1 = new Length(2.0,LengthUnit.CENTIMETERS);
        Length cm2 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertNotEquals(cm1, cm2);
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(3.0, LengthUnit.FEET);
        assertEquals(y, f);
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(2.0, LengthUnit.FEET);
        assertNotEquals(y, f);
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length in = new Length(36.0,LengthUnit.INCHES);
        assertEquals(y, in);
    }

    @Test
    public void testEquality_YardToInches_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length in = new Length(26.0,LengthUnit.INCHES);
        assertNotEquals(y, in);
    }

    @Test
    public void testEquality_YardToCentimeters_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length cm = new Length(91.439999,LengthUnit.CENTIMETERS);
        assertEquals(y, cm);
    }

    @Test
    public void testEquality_YardToCentimeters_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length cm = new Length(91.0,LengthUnit.CENTIMETERS);
        assertNotEquals(y, cm);
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(12.0, LengthUnit.INCHES);
        assertEquals(f, in);
    }

    @Test
    public void testEquality_FeetToInch_NonEquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(14.0, LengthUnit.INCHES);
        assertNotEquals(f, in);
    }

    @Test
    public void testEquality_FeetToCentimeters_EquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length cm = new Length(30.47999, LengthUnit.CENTIMETERS);
        assertEquals(f, cm);
    }

    @Test
    public void testEquality_FeetToCentimeters_NonEquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length cm = new Length(30.0, LengthUnit.CENTIMETERS);
        assertNotEquals(f, cm);
    }

    @Test
    public void testEquality_InchesToCentimeters_EquivalentValue(){
        Length in = new Length(1.0, LengthUnit.INCHES);
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        assertEquals(in, cm);
    }

    @Test
    public void testEquality_InchesToCentimeters_NonEquivalentValue(){
        Length in = new Length(1.0, LengthUnit.INCHES);
        Length cm = new Length(30.0, LengthUnit.CENTIMETERS);
        assertNotEquals(in, cm);
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(3.0, LengthUnit.FEET);
        Length in = new Length(36.0, LengthUnit.INCHES);

        assertEquals(y, f);   // a=b
        assertEquals(f, in);  // b=c
        assertEquals(y, in);  // a=c
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length cm = new Length(91.439999, LengthUnit.CENTIMETERS);

        assertEquals(yards, feet);
        assertEquals(yards, inches);
        assertEquals(yards, cm);
        assertEquals(feet, inches);
        assertEquals(feet, cm);
        assertEquals(inches, cm);
    }

    @Test
    public void testEquality_SameReference(){
        Length f = new Length(1.0, LengthUnit.FEET);
        assertEquals(f, f);
    }

    @Test
    public void testEquality_NullComparison(){
        Length f = new Length(1.0, LengthUnit.FEET);
        assertNotEquals(null, f);
    }

    @Test
    public void testEquality_InvalidUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Length(1.0, null);
        });
    }

    @Test
    public void testEquality_NullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Length(1.0, null);
        });
    }

    @Test
    public void testConversion_FeetToInches(){
        Length l = new Length(1.0, LengthUnit.FEET);
        assertEquals(12.0, l.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testConversion_InchesToFeet(){
        Length l = new Length(24.0, LengthUnit.INCHES);
        assertEquals(2.0, l.convertTo(LengthUnit.FEET).getValue());
    }

    @Test
    public void testConversion_YardsToInches(){
        Length l = new Length(1.0, LengthUnit.YARDS);
        assertEquals(36.0, l.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testConversion_InchesToYards(){
        Length l = new Length(72.0, LengthUnit.INCHES);
        assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }

    @Test
    public void testConversion_CentimetersToInches(){
        Length l = new Length(2.54, LengthUnit.CENTIMETERS);
        assertEquals(1.0, l.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testConversion_FeetToYard(){
        Length l = new Length(6.0, LengthUnit.FEET);
        assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }

    @Test
    public void testConversion_ZeroValue(){
        Length l = new Length(0.0, LengthUnit.FEET);
        assertEquals(0.0, l.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testConversion_NegativeValue(){
        Length l = new Length(-1.0, LengthUnit.FEET);
        assertEquals(-12.0, l.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue(){
        Length l = new Length(1.0, LengthUnit.FEET);
        Length val = l.convertTo(LengthUnit.INCHES);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateLengthConversion(val.getValue(), LengthUnit.INCHES, LengthUnit.FEET).getValue());
    }

    @Test
    public void testConversion_NaNOrInfinite_Throws(){
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.NaN, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.NEGATIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    public void testConversion_PrecisionTolerance(){
        Length l = new Length(1.0, LengthUnit.FEET);
        assertNotEquals(30.479999, l.convertTo(LengthUnit.CENTIMETERS).getValue());
    }

    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertEquals(3.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch(){
        Length l1 = new Length(6.0, LengthUnit.INCHES);
        Length l2 = new Length(6.0, LengthUnit.INCHES);
        assertEquals(12.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(2.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet(){
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(24.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet(){
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        assertEquals(2.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch(){
        Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.INCHES);
        assertEquals(5.08, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_Commutativity(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result1 = l1.add(l2);
        Length result2 = l2.add(l1);

        Length base1 = result1.convertTo(LengthUnit.INCHES);
        Length base2 = result2.convertTo(LengthUnit.INCHES);

        assertEquals(base1.getValue(), base2.getValue());
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);
        assertEquals(5.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_NegativeValues(){
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);
        assertEquals(3.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, ()-> {
            l1.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues(){
        Length l1 = new Length(1e6, LengthUnit.FEET);
        Length l2 = new Length(1e6, LengthUnit.FEET);
        assertEquals(2e6, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_SmallValues(){
        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);
        assertEquals(0.003, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(2.0, l1.add(l2,LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(24.0, l1.add(l2,LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(0.667, l1.add(l2,LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);
        assertEquals(5.08, l1.add(l2,LengthUnit.CENTIMETERS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        assertEquals(3.0, l1.add(l2,LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        assertEquals(9.0, l1.add(l2,LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length y1 = l1.add(l2,LengthUnit.YARDS);
        assertEquals(y1, l2.add(l1,LengthUnit.YARDS));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);
        assertEquals(1.667, l1.add(l2,LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);
        assertEquals(36.0, l1.add(l2,LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, ()->{
            l1.add(l2, null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale(){
        Length l1 = new Length(1000.0, LengthUnit.FEET);
        Length l2 = new Length(500.0, LengthUnit.FEET);
        assertEquals(18000.0, l1.add(l2,LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(0.667, l1.add(l2,LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        double epsilon = 0.01;

        for (LengthUnit u1 : LengthUnit.values()) {
            for (LengthUnit u2 : LengthUnit.values()) {
                for (LengthUnit target : LengthUnit.values()) {

                    Length l1 = new Length(1.0, u1);
                    Length l2 = new Length(1.0, u2);

                    Length result = l1.add(l2, target);

                    double base1 = u1.convertToBaseUnit(1.0);
                    double base2 = u2.convertToBaseUnit(1.0);
                    double sumBase = base1 + base2;

                    double expected = target.convertFromBaseUnit(sumBase);

                    assertEquals(expected, result.getValue(), epsilon);
                }
            }
        }
    }

    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        double epsilon = 0.01;

        Length l1 = new Length(2.5, LengthUnit.FEET);
        Length l2 = new Length(30.0, LengthUnit.CENTIMETERS);
        Length l3 = new Length(7.0, LengthUnit.INCHES);

        Length result = l1
                .add(l2, LengthUnit.YARDS)
                .add(l3, LengthUnit.YARDS);

        // Expected via UC8 conversion pipeline (base unit flow)
        double base1 = LengthUnit.FEET.convertToBaseUnit(2.5);
        double base2 = LengthUnit.CENTIMETERS.convertToBaseUnit(30.0);
        double base3 = LengthUnit.INCHES.convertToBaseUnit(7.0);

        double sumBase = base1 + base2 + base3;

        double expected = LengthUnit.YARDS.convertFromBaseUnit(sumBase);

        assertEquals(expected, result.getValue(), epsilon);
    }

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(12.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0, LengthUnit.INCHES.getConversionFactor());
    }

    @Test
    public void testLengthUnitEnum_YardsConstant(){
        assertEquals(36.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant(){
        assertEquals(0.393701, LengthUnit.CENTIMETERS.getConversionFactor());
    }

    @Test
    public void testConvertToBaseUnit_FeetToInches(){
        assertEquals(60.0, LengthUnit.FEET.convertToBaseUnit(5.0));
    }

    @Test
    public void testConvertToBaseUnit_InchesToInches(){
        assertEquals(12.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
    }

    @Test
    public void testConvertToBaseUnit_YardsToInches(){
        assertEquals(36.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToInches(){
        assertEquals(0.394, LengthUnit.CENTIMETERS.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertFromBaseUnit_InchesToFeet(){
        assertEquals(1.0, LengthUnit.FEET.convertFromBaseUnit(12.0));
    }

    @Test
    public void testConvertFromBaseUnit_InchesToInches(){
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(12.0));
    }

    @Test
    public void testConvertFromBaseUnit_InchesToYards(){
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(36.0));
    }

    @Test
    public void testConvertFromBaseUnit_InchesToCentimeters(){
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertFromBaseUnit(0.393701));
    }

    @Test
    public void testQuantityLengthRefactored_Equality(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertEquals(12.0, l1.convertTo(LengthUnit.INCHES).getValue());
    }

    @Test
    public void testQuantityLengthRefactored_Add(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(2.0, l1.add(l2).getValue());
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(0.667, l1.add(l2, LengthUnit.YARDS).getValue());
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Length(1.0, null);
        });
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    public void testRoundTripConversion_RefactoredDesign(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        double c1 = l1.convertTo(LengthUnit.INCHES).getValue(); // 12.0
        double c2 = l2.convertTo(LengthUnit.FEET).getValue();   // 1.0
        double c3 = LengthUnit.FEET.convertToBaseUnit(c2);      // 12.0

        assertEquals( c1, c3);
    }

    @Test
    public void testUnitImmutability() {
        for (Field field : LengthUnit.class.getDeclaredFields()) {
            if (field.getName().equals("conversionFactor")) {
                assertTrue(Modifier.isFinal(field.getModifiers()));
                assertTrue(Modifier.isPrivate(field.getModifiers()));
            }
        }
    }

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue(){
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue(){
        assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible(){
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_TransitiveProperty(){
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
        assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_ZeroValue(){
        assertTrue(new Weight(0.0, WeightUnit.KILOGRAM).equals(new Weight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_NegativeWeight(){
        assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM).equals(new Weight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_LargeWeightValue(){
        assertTrue(new Weight(1000000.0, WeightUnit.GRAM).equals(new Weight(1000.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_SmallWeightValue(){
        assertTrue(new Weight(0.001, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.GRAM)));
    }

    @Test
    public void testConversion_PoundToKilogram(){
        Weight weight = new Weight(2.20462, WeightUnit.POUND);
        assertEquals(1.0, weight.convertTo(WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_KilogramToPound(){
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(2.205, weight.convertTo(WeightUnit.POUND).getValue());
    }

    @Test
    public void testConversion_SameUnit(){
        Weight weight = new Weight(5.0, WeightUnit.KILOGRAM);
        assertEquals(5.0, weight.convertTo(WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_RoundTrip(){
        Weight weight = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight weight1 = weight.convertTo(WeightUnit.GRAM);
        Weight weight2 = weight1.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, weight2.getValue());
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram(){
        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(2.0, WeightUnit.KILOGRAM);
        assertEquals(3.0, weight1.add(weight2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram(){
        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(2000.0, WeightUnit.GRAM);
        assertEquals(3.0, weight1.add(weight2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram(){
        Weight weight1 = new Weight(2.20462, WeightUnit.POUND);
        Weight weight2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(4.409, weight1.add(weight2).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram(){
        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(2000.0, WeightUnit.GRAM);
        assertEquals(3000.0, weight1.add(weight2, WeightUnit.GRAM).getValue());
    }
}