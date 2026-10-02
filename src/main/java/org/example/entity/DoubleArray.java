package org.example.entity;

import java.util.Arrays;

public class DoubleArray {

    private final long id;
    private final double[] values;

    public DoubleArray(long id, double[] values) {
        this.id = id;
        this.values = values.clone();
    }

    public long getId() {
        return id;
    }

    public double[] getValues() {
        return values.clone();
    }

    public void setValue(int index, double value) {
        values[index] = value;
    }

    public double getValue(int index) {
        return values[index];
    }

    public int size() {
        return values.length;
    }

    @Override
    public String toString() {
        return "DoubleArray{" +
                "id=" + id +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}