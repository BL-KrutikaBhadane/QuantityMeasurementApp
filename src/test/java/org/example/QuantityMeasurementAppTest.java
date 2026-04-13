package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Length.LengthUnit.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Length.LengthUnit;

class QuantityMeasurementAppTest {
    @Test
    public void testEquality_FeetToFeet_SameValue(){
        Length f1 = new Length(2.0, FEET);
        Length f2 = new Length(2.0, FEET);
        assertTrue(f1.equals(f2));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue(){
        Length f1 = new Length(2.0, FEET);
        Length f2 = new Length(1.0, FEET);
        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEquality_InchToInch_SameValue(){
        Length in1 = new Length(6.0, INCHES);
        Length in2 = new Length(6.0, INCHES);
        assertTrue(in1.equals(in2));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue(){
        Length in1 = new Length(6.0, INCHES);
        Length in2 = new Length(1.0, INCHES);
        assertFalse(in1.equals(in2));
    }

    @Test
    public void testEquality_YardToYard_SameValue(){
        Length y1 = new Length(2.0,YARDS);
        Length y2 = new Length(2.0, YARDS);
        assertTrue(y1.equals(y2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue(){
        Length y1 = new Length(2.0,YARDS);
        Length y2 = new Length(1.0, YARDS);
        assertFalse(y1.equals(y2));
    }

    @Test
    public void testEquality_CentimetersToCentimeters_SameValue(){
        Length cm1 = new Length(2.0,CENTIMETERS);
        Length cm2 = new Length(2.0, CENTIMETERS);
        assertTrue(cm1.equals(cm2));
    }

    @Test
    public void testEquality_CentimetersToCentimeters_DifferentValue(){
        Length cm1 = new Length(2.0,CENTIMETERS);
        Length cm2 = new Length(1.0, CENTIMETERS);
        assertFalse(cm1.equals(cm2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length f = new Length(3.0, FEET);
        assertTrue(y.equals(f));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length f = new Length(2.0, FEET);
        assertFalse(y.equals(f));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length in = new Length(36.0,INCHES);
        assertTrue(y.equals(in));
    }

    @Test
    public void testEquality_YardToInches_NonEquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length in = new Length(26.0,INCHES);
        assertFalse(y.equals(in));
    }

    @Test
    public void testEquality_YardToCentimeters_EquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length cm = new Length(91.439999,CENTIMETERS);
        assertTrue(y.equals(cm));
    }

    @Test
    public void testEquality_YardToCentimeters_NonEquivalentValue(){
        Length y = new Length(1.0,YARDS);
        Length cm = new Length(91.0,CENTIMETERS);
        assertFalse(y.equals(cm));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue(){
        Length f = new Length(1.0, FEET);
        Length in = new Length(12.0, INCHES);
        assertTrue(f.equals(in));
    }

    @Test
    public void testEquality_FeetToInch_NonEquivalentValue(){
        Length f = new Length(1.0, FEET);
        Length in = new Length(14.0, INCHES);
        assertFalse(f.equals(in));
    }

    @Test
    public void testEquality_FeetToCentimeters_EquivalentValue(){
        Length f = new Length(1.0, FEET);
        Length cm = new Length(30.47999, CENTIMETERS);
        assertTrue(f.equals(cm));
    }

    @Test
    public void testEquality_FeetToCentimeters_NonEquivalentValue(){
        Length f = new Length(1.0, FEET);
        Length cm = new Length(30.0, CENTIMETERS);
        assertFalse(f.equals(cm));
    }

    @Test
    public void testEquality_InchesToCentimeters_EquivalentValue(){
        Length in = new Length(1.0, INCHES);
        Length cm = new Length(2.54, CENTIMETERS);
        assertTrue(in.equals(cm));
    }

    @Test
    public void testEquality_InchesToCentimeters_NonEquivalentValue(){
        Length in = new Length(1.0, INCHES);
        Length cm = new Length(30.0, CENTIMETERS);
        assertFalse(in.equals(cm));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty(){
        Length y = new Length(1.0,YARDS);
        Length f = new Length(3.0, FEET);
        Length in = new Length(36.0, INCHES);

        assertTrue(y.equals(f));   // a=b
        assertTrue(f.equals(in));  // b=c
        assertTrue(y.equals(in));  // a=c
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(1.0, YARDS);
        Length feet = new Length(3.0, FEET);
        Length inches = new Length(36.0, INCHES);
        Length cm = new Length(91.439999, CENTIMETERS);

        assertTrue(yards.equals(feet));
        assertTrue(yards.equals(inches));
        assertTrue(yards.equals(cm));
        assertTrue(feet.equals(inches));
        assertTrue(feet.equals(cm));
        assertTrue(inches.equals(cm));
    }

    @Test
    public void testEquality_SameReference(){
        Length f = new Length(1.0, FEET);
        assertTrue(f.equals(f));
    }

    @Test
    public void testEquality_NullComparison(){
        Length f = new Length(1.0, FEET);
        assertFalse(f.equals(null));
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
        Length l = new Length(1.0, FEET);
        assertEquals(12.0, l.convertTo(INCHES).getValue());
    }

    @Test
    public void testConversion_InchesToFeet(){
        Length l = new Length(24.0, INCHES);
        assertEquals(2.0, l.convertTo(FEET).getValue());
    }

    @Test
    public void testConversion_YardsToInches(){
        Length l = new Length(1.0, YARDS);
        assertEquals(36.0, l.convertTo(INCHES).getValue());
    }

    @Test
    public void testConversion_InchesToYards(){
        Length l = new Length(72.0, INCHES);
        assertEquals(2.0, l.convertTo(YARDS).getValue());
    }

    @Test
    public void testConversion_CentimetersToInches(){
        Length l = new Length(2.54, CENTIMETERS);
        assertEquals(1.0, l.convertTo(INCHES).getValue());
    }

    @Test
    public void testConversion_FeetToYard(){
        Length l = new Length(6.0, FEET);
        assertEquals(2.0, l.convertTo(YARDS).getValue());
    }

    @Test
    public void testConversion_ZeroValue(){
        Length l = new Length(0.0, FEET);
        assertEquals(0.0, l.convertTo(INCHES).getValue());
    }

    @Test
    public void testConversion_NegativeValue(){
        Length l = new Length(-1.0, FEET);
        assertEquals(-12.0, l.convertTo(INCHES).getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue(){
        Length l = new Length(1.0, FEET);
        Length val = l.convertTo(INCHES);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateLengthConversion(val.getValue(), INCHES, FEET).getValue());
    }

    @Test
    public void testConversion_NaNOrInfinite_Throws(){
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.NaN, FEET));
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.POSITIVE_INFINITY, FEET));
        assertThrows(IllegalArgumentException.class, ()->
                new Length(Double.NEGATIVE_INFINITY, FEET));
    }

    @Test
    public void testConversion_PrecisionTolerance(){
        Length l = new Length(1.0, FEET);
        assertNotEquals(30.479999, l.convertTo(CENTIMETERS).getValue());
    }

    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(2.0, FEET);
        assertEquals(3.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch(){
        Length l1 = new Length(6.0, INCHES);
        Length l2 = new Length(6.0, INCHES);
        assertEquals(12.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);
        assertEquals(2.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet(){
        Length l1 = new Length(12.0, INCHES);
        Length l2 = new Length(1.0, FEET);
        assertEquals(24.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet(){
        Length l1 = new Length(1.0, YARDS);
        Length l2 = new Length(3.0, FEET);
        assertEquals(2.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch(){
        Length l1 = new Length(2.54, CENTIMETERS);
        Length l2 = new Length(1.0, INCHES);
        assertEquals(5.08, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_Commutativity(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);

        Length result1 = l1.add(l2);
        Length result2 = l2.add(l1);

        Length base1 = result1.convertTo(INCHES);
        Length base2 = result2.convertTo(INCHES);

        assertEquals(base1.getValue(), base2.getValue());
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, FEET);
        Length l2 = new Length(0.0, INCHES);
        assertEquals(5.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_NegativeValues(){
        Length l1 = new Length(5.0, FEET);
        Length l2 = new Length(-2.0, FEET);
        assertEquals(3.0, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand(){
        Length l1 = new Length(1.0, FEET);
        assertThrows(IllegalArgumentException.class, ()-> {
            l1.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues(){
        Length l1 = new Length(1e6, FEET);
        Length l2 = new Length(1e6, FEET);
        assertEquals(2e6, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_SmallValues(){
        Length l1 = new Length(0.001, FEET);
        Length l2 = new Length(0.002, FEET);
        assertEquals(0.003, l1.add(l2).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);
        assertEquals(2.0, l1.add(l2,FEET).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){
        Length l1 = new Length(12.0, INCHES);
        Length l2 = new Length(1.0, FEET);
        assertEquals(24.0, l1.add(l2,INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);
        assertEquals(0.667, l1.add(l2,YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){
        Length l1 = new Length(1.0, INCHES);
        Length l2 = new Length(1.0, INCHES);
        assertEquals(5.08, l1.add(l2,CENTIMETERS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length l1 = new Length(2.0, YARDS);
        Length l2 = new Length(3.0, FEET);
        assertEquals(3.0, l1.add(l2,YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length l1 = new Length(2.0, YARDS);
        Length l2 = new Length(3.0, FEET);
        assertEquals(9.0, l1.add(l2,FEET).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);
        Length y1 = l1.add(l2,YARDS);
        assertEquals(y1, l2.add(l1,YARDS));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){
        Length l1 = new Length(5.0, FEET);
        Length l2 = new Length(0.0, INCHES);
        assertEquals(1.667, l1.add(l2,YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length l1 = new Length(5.0, FEET);
        Length l2 = new Length(-2.0, FEET);
        assertEquals(36.0, l1.add(l2,INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        Length l1 = new Length(1.0, FEET);
        Length l2 = new Length(12.0, INCHES);
        assertThrows(IllegalArgumentException.class, ()->{
            l1.add(l2, null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale(){
        Length l1 = new Length(1000.0, FEET);
        Length l2 = new Length(500.0, FEET);
        assertEquals(18000.0, l1.add(l2,INCHES).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length l1 = new Length(12.0, INCHES);
        Length l2 = new Length(12.0, INCHES);
        assertEquals(0.667, l1.add(l2,YARDS).getValue());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        double epsilon = 0.001;

        for (LengthUnit u1 : LengthUnit.values()) {
            for (LengthUnit u2 : LengthUnit.values()) {
                for (LengthUnit target : LengthUnit.values()) {

                    Length l1 = new Length(1.0, u1);
                    Length l2 = new Length(1.0, u2);

                    Length result = l1.add(l2, target);

                    // Expected value computed via base unit (inches)
                    double expectedInInches =
                            (u1.getConversionFactor()) +
                                    (u2.getConversionFactor());

                    double expected =
                            expectedInInches / target.getConversionFactor();

                    assertEquals(expected, result.getValue(), epsilon);
                }
            }
        }
    }

    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        double epsilon = 0.001;

        Length l1 = new Length(2.5, FEET);
        Length l2 = new Length(30.0, CENTIMETERS);
        Length l3 = new Length(7.0, INCHES);

        Length result = l1
                .add(l2, YARDS)
                .add(l3, YARDS);

        // Expected via base unit
        double expectedInInches =
                (2.5 * FEET.getConversionFactor()) +
                        (30.0 * CENTIMETERS.getConversionFactor()) +
                        (7.0 * INCHES.getConversionFactor());

        double expected = expectedInInches / YARDS.getConversionFactor();

        assertEquals(expected, result.getValue(), epsilon);
    }
}