package org.example;

import org.example.genericEnum.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.example.genericEnum.LengthUnit.FEET;
import static org.junit.jupiter.api.Assertions.*;
import org.example.genericEnum.LengthUnit;

class QuantityMeasurementAppTest {
    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable unit = FEET;

        double base = unit.convertToBaseUnit(1.0);
        double value = unit.convertFromBaseUnit(base);

        assertEquals(12.0, base, 0.0001);
        assertEquals(1.0, value, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;

        double base = unit.convertToBaseUnit(1.0);
        double value = unit.convertFromBaseUnit(base);

        assertEquals(1000.0, base, 0.0001);
        assertEquals(1.0, value, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_ConsistentBehavior() {
        IMeasurable length = FEET;
        IMeasurable weight = WeightUnit.KILOGRAM;

        double lBase = length.convertToBaseUnit(2.0);
        double wBase = weight.convertToBaseUnit(2.0);

        assertTrue(lBase > 0);
        assertTrue(wBase > 0);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> converted = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, converted.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> converted = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, converted.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
    }

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, FEET);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(FEET, result.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight);
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, ()-> new Quantity<>(1.0, null));
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, ()-> new Quantity<>(Double.NaN, FEET));
    }

    @Test
    public void testGenericQuantity_Conversion_AllUnitCombinations_Length() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCHES).getValue(), 0.0001);
        assertEquals(0.3333, q.convertTo(LengthUnit.YARDS).getValue(), 0.01);
        assertEquals(30.48, q.convertTo(LengthUnit.CENTIMETERS).getValue(), 0.01);
    }

    @Test
    public void testGenericQuantity_Conversion_AllUnitCombinations_Weight() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM).getValue(), 0.0001);
    }

    @Test
    public void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> resultFeet = q1.add(q2, FEET);
        Quantity<LengthUnit> resultInches = q1.add(q2, LengthUnit.INCHES);

        assertEquals(2.0, resultFeet.getValue(), 0.0001);
        assertEquals(24.0, resultInches.getValue(), 0.0001);
    }

    @Test
    public void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> c = new Quantity<>(0.333333, LengthUnit.YARDS);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> q1 = new Quantity<>(1.0, FEET);
        Quantity<?> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotNull(q1);
        assertNotNull(q2);
    }

    @Test
    public void testTypeErasure_RuntimeSafety() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    public void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES);

        assertNotSame(q1, q2);
    }

    @Test
    public void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        IMeasurable unit = FEET;

        double base = unit.convertToBaseUnit(2.0);
        double val = unit.convertFromBaseUnit(base);

        assertEquals(24.0, base, 0.0001);
        assertEquals(2.0, val, 0.0001);
    }

    @Test
    public void testCompositionOverInheritance_Flexibility() {
        class CustomUnit implements IMeasurable {
            public double getConversionFactor() { return 1; }
            public double convertToBaseUnit(double v){ return v; }
            public double convertFromBaseUnit(double v){ return v; }
            public String getUnitName(){ return "CUSTOM"; }
        }

        Quantity<IMeasurable> q = new Quantity<>(10, new CustomUnit());
        assertEquals(10.0, q.getValue(), 0.0001);
    }
}