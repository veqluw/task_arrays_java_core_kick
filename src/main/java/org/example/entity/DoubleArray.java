package org.example.entity;

import java.util.Arrays;

public class DoubleArray {
    private final double[] values;

    public DoubleArray(double[] values) {
        this.values = values;
    }

    public double[] getValues() {
        return values.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
