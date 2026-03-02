package org.example;

public class Feet {

    private final double value;   // immutable value

    // Constructor
    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {

        // Same reference check
        if (this == obj)
            return true;

        // Null or different type check
        if (obj == null || getClass() != obj.getClass())
            return false;

        Feet other = (Feet) obj;

        // Floating point comparison
        return Double.compare(this.value, other.value) == 0;
    }

    // Recommended when overriding equals
    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}