package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.QuantityMeasurementApp.Feet;
import org.example.QuantityMeasurementApp.Inches;

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
}
