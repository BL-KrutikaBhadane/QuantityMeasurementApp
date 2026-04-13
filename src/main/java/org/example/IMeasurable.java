package org.example;

public interface IMeasurable {
    public double convertToBaseUnit(double value);
    public double convertFromBaseUnit(double baseValue);

    default SupportsArithmatic supportsArithmetic(){
        return ()-> true;
    }

    default void validateOperationSupport(String operation){
    }
}