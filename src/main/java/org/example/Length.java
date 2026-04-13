package org.example;

public class Length {
    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit){
        if(Double.isNaN(value)) throw new IllegalArgumentException("This exception is thrown because value is not valid(Numeric)");
        if(unit == null) throw new IllegalArgumentException("This exception is thrown because Unit is provided as null");
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){
        return value*unit.getConversionFactor();
    }

    public boolean compare(Length thatLength){
        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Length length = (Length)obj;
        return compare(length);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(convertToBaseUnit());
    }

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.1, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(l1.equals(l2));
    }
}
