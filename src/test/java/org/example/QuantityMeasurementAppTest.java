package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.QuantityMeasurementApp.Feet;
import org.example.QuantityMeasurementApp.Inches;
import org.example.Length.LengthUnit;

class QuantityMeasurementAppTest {

    @Test
    void testFeetConstructor_WhenNaN_ShouldPass() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Feet(Double.NaN);
        });
    }

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(new Feet(1.0).equals(new Feet(1.0)));
    }

    @Test
    public void testFeetEquality_DifferentValue(){
        assertFalse(new Feet(1.0).equals(new Feet(2.0)));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        assertFalse(new Feet(1.0).equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass(){
        assertFalse(new Feet(1.0).equals(new Feet(12)));
    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet f1 = new Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    @Test
    public void testInchesEquality_SameValue(){
        assertTrue(new Inches(1.0).equals(new Inches(1.0)));
    }

    @Test
    public void testInchesEquality_DifferentValue(){
        assertFalse(new Inches(1.0).equals(new Inches(2.0)));
    }

    @Test
    public void testInchesEquality_NullComparison(){
        assertFalse(new Inches(1.0).equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass(){
        assertFalse(new Inches(1.0).equals(new Inches(12)));
    }

    @Test
    public void testInchesEquality_SameReference(){
        Inches in1 = new Inches(1.0);
        assertTrue(in1.equals(in1));
    }

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
    public void testEquality_InchToFeet_EquivalentValue(){
        Length in = new Length(12.0, LengthUnit.INCHES);
        Length f = new Length(1.0, LengthUnit.FEET);
        assertTrue(in.equals(f));
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
    public void testEquality_SameReference(){
        Length f = new Length(1.0, LengthUnit.FEET);
        assertTrue(f.equals(f));
    }
}
