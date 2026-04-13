package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Length.LengthUnit;

class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue(){
        Length f1 = new Length(2.0,LengthUnit.FEET);
        Length f2 = new Length(2.0, LengthUnit.FEET);
        assertTrue(f1.equals(f2));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue(){
        Length f1 = new Length(2.0,LengthUnit.FEET);
        Length f2 = new Length(1.0, LengthUnit.FEET);
        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEquality_InchToInch_SameValue(){
        Length in1 = new Length(6.0, LengthUnit.INCHES);
        Length in2 = new Length(6.0, LengthUnit.INCHES);
        assertTrue(in1.equals(in2));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue(){
        Length in1 = new Length(6.0, LengthUnit.INCHES);
        Length in2 = new Length(1.0, LengthUnit.INCHES);
        assertFalse(in1.equals(in2));
    }

    @Test
    public void testEquality_YardToYard_SameValue(){
        Length y1 = new Length(2.0,LengthUnit.YARDS);
        Length y2 = new Length(2.0, LengthUnit.YARDS);
        assertTrue(y1.equals(y2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue(){
        Length y1 = new Length(2.0,LengthUnit.YARDS);
        Length y2 = new Length(1.0, LengthUnit.YARDS);
        assertFalse(y1.equals(y2));
    }

    @Test
    public void testEquality_CentimetersToCentimeters_SameValue(){
        Length cm1 = new Length(2.0,LengthUnit.CENTIMETERS);
        Length cm2 = new Length(2.0, LengthUnit.CENTIMETERS);
        assertTrue(cm1.equals(cm2));
    }

    @Test
    public void testEquality_CentimetersToCentimeters_DifferentValue(){
        Length cm1 = new Length(2.0,LengthUnit.CENTIMETERS);
        Length cm2 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(cm1.equals(cm2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(3.0,LengthUnit.FEET);
        assertTrue(y.equals(f));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(2.0,LengthUnit.FEET);
        assertFalse(y.equals(f));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length in = new Length(36.0,LengthUnit.INCHES);
        assertTrue(y.equals(in));
    }

    @Test
    public void testEquality_YardToInches_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length in = new Length(26.0,LengthUnit.INCHES);
        assertFalse(y.equals(in));
    }

    @Test
    public void testEquality_YardToCentimeters_EquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length cm = new Length(91.439999,LengthUnit.CENTIMETERS);
        assertTrue(y.equals(cm));
    }

    @Test
    public void testEquality_YardToCentimeters_NonEquivalentValue(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length cm = new Length(91.0,LengthUnit.CENTIMETERS);
        assertFalse(y.equals(cm));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(12.0, LengthUnit.INCHES);
        assertTrue(f.equals(in));
    }

    @Test
    public void testEquality_FeetToInch_NonEquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(14.0, LengthUnit.INCHES);
        assertFalse(f.equals(in));
    }

    @Test
    public void testEquality_FeetToCentimeters_EquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length cm = new Length(30.47999, LengthUnit.CENTIMETERS);
        assertTrue(f.equals(cm));
    }

    @Test
    public void testEquality_FeetToCentimeters_NonEquivalentValue(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length cm = new Length(30.0, LengthUnit.CENTIMETERS);
        assertFalse(f.equals(cm));
    }

    @Test
    public void testEquality_InchesToCentimeters_EquivalentValue(){
        Length in = new Length(1.0, LengthUnit.INCHES);
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        assertTrue(in.equals(cm));
    }

    @Test
    public void testEquality_InchesToCentimeters_NonEquivalentValue(){
        Length in = new Length(1.0, LengthUnit.INCHES);
        Length cm = new Length(30.0, LengthUnit.CENTIMETERS);
        assertFalse(in.equals(cm));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty(){
        Length y = new Length(1.0,LengthUnit.YARDS);
        Length f = new Length(3.0, LengthUnit.FEET);
        Length in = new Length(36.0, LengthUnit.INCHES);

        assertTrue(y.equals(f));   // a=b
        assertTrue(f.equals(in));  // b=c
        assertTrue(y.equals(in));  // a=c
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length cm = new Length(91.439999, LengthUnit.CENTIMETERS);

        assertTrue(yards.equals(feet));
        assertTrue(yards.equals(inches));
        assertTrue(yards.equals(cm));
        assertTrue(feet.equals(inches));
        assertTrue(feet.equals(cm));
        assertTrue(inches.equals(cm));
    }

    @Test
    public void testEquality_SameReference(){
        Length f = new Length(1.0, LengthUnit.FEET);
        assertTrue(f.equals(f));
    }

    @Test
    public void testEquality_NullComparison(){
        Length f = new Length(1.0, LengthUnit.FEET);
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
}