package org.example;

import org.example.Length.LengthUnit;
public class QuantityMeasurementApp {

    public static class Feet{

        public Feet(double value){
            if(Double.isNaN(value)) throw new IllegalArgumentException("This exception is thrown because value is not valid(Numeric)");
            this.value = value;
        }

        private final double value;

        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet)obj;
            return Double.compare(feet.value, value) == 0;
        }

        @Override
        public int hashCode(){
            return Double.hashCode(value);
        }
    }

    public static class Inches{

        public Inches(double value){
            if(Double.isNaN(value)) throw new IllegalArgumentException("This exception is thrown because value is not valid(Numeric)");
            this.value = value;
        }

        private final double value;

        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches)obj;
            return Double.compare(inches.value, value) == 0;
        }

        @Override
        public int hashCode(){
            return Double.hashCode(value);
        }
    }

    public static void demonstrateFeetEquality(){
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("Feet value f1, f2 are equal: "+f1.equals(f2));
    }

    public static void demonstrateInchesEquality(){
        Inches in1 = new Inches(1.0);
        Inches in2 = new Inches(1.0);
        System.out.println("Inches value in1, in1 are equal: "+in1.equals(in2));
    }

    public static void demostrateLengthEquality(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(l1.equals(l2));
    }

    public static void demonstrateFeetInchesComparison(){
        Length f = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(12.0, LengthUnit.INCHES);
        System.out.println(f.equals(in));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }

}
