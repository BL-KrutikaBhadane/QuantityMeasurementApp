package org.example;

import org.example.genericEnum.VolumeUnit;
import org.example.genericEnum.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.example.genericEnum.LengthUnit;

class QuantityMeasurementAppTest {
    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;

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
        IMeasurable length = LengthUnit.FEET;
        IMeasurable weight = WeightUnit.KILOGRAM;

        double lBase = length.convertToBaseUnit(2.0);
        double wBase = weight.convertToBaseUnit(2.0);

        assertTrue(lBase > 0);
        assertTrue(wBase > 0);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
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
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
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
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
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
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight);
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, ()-> new Quantity<>(1.0, null));
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, ()-> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testGenericQuantity_Conversion_AllUnitCombinations_Length() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

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
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> resultFeet = q1.add(q2, LengthUnit.FEET);
        Quantity<LengthUnit> resultInches = q1.add(q2, LengthUnit.INCHES);

        assertEquals(2.0, resultFeet.getValue(), 0.0001);
        assertEquals(24.0, resultInches.getValue(), 0.0001);
    }

    @Test
    public void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
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
        Quantity<?> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<?> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotNull(q1);
        assertNotNull(q2);
    }

    @Test
    public void testTypeErasure_RuntimeSafety() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    public void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES);

        assertNotSame(q1, q2);
    }

    @Test
    public void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        IMeasurable unit = LengthUnit.FEET;

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

    private static final double EPS = 0.01;

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(2.0, VolumeUnit.LITRE));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertEquals(q1.getUnit().convertToBaseUnit(q1.getValue()),
                q2.getUnit().convertToBaseUnit(q2.getValue()), EPS);
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(q1.getUnit().convertToBaseUnit(q1.getValue()),
                q2.getUnit().convertToBaseUnit(q2.getValue()), EPS);
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(null, new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(q, q);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE), new Quantity<>(0.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testEquality_NegativeVolumeUnit() {
        assertEquals(new Quantity<>(-1.0, VolumeUnit.LITRE), new Quantity<>(-1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, q.getValue(), EPS);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, q.getValue(), EPS);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, q.getValue(), EPS);
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> q = new Quantity<>(3.78541, VolumeUnit.LITRE).convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, q.getValue(), EPS);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> q = new Quantity<>(1.5, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.5, q.getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(2.0, VolumeUnit.LITRE));
        assertEquals(3.0, q.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        assertEquals(2.0, q.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);
        assertEquals(2000.0, q.getValue(), EPS);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(1.0, VolumeUnit.LITRE));
        assertEquals(
                q1.getUnit().convertToBaseUnit(q1.getValue()),
                q2.getUnit().convertToBaseUnit(q2.getValue()),
                EPS
        );
    }

    @Test
    void testVolumetUnitEnum_LitreConstant() {
        assertEquals(1000.0, VolumeUnit.LITRE.getConversionFactor(), EPS);
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.getConversionFactor(), EPS);
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3785.412, VolumeUnit.GALLON.getConversionFactor(), EPS);
    }

    @Test
    void testConvertToBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.LITRE.convertToBaseUnit(1.0), EPS);
    }

    @Test
    void testConvertFromBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.LITRE.convertFromBaseUnit(1000.0), EPS);
    }

    @Test
    void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> r = q.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertEquals(q.getValue(), r.getValue(), EPS);
    }

    @Test
    void testScalability_VolumeIntegration() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertNotNull(q.convertTo(VolumeUnit.GALLON));
    }

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), q1.subtract(q2));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), q1.subtract(q2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), q1.subtract(q2, LengthUnit.FEET));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), q1.subtract(q2, LengthUnit.INCHES));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), q1.subtract(q2, VolumeUnit.MILLILITRE));
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), a.subtract(b));
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), b.subtract(a));
    }

    @Test
    void testSubtraction_WithLargeValues() {
        Quantity<WeightUnit> q1 = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5e5, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), q1.subtract(q2));
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract((Quantity) q2));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), q);
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testDivision_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    @Test
    void testDivision_CrossCategory() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) q2));
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.add(b).subtract(b);
        assertEquals(a, result);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        a.subtract(b);
        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        a.divide(b);
        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }
}