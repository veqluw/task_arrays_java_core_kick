package org.example.entity;

import java.util.Arrays;

public record DoubleArray(long id, double[] values) {

    public DoubleArray(long id, double[] values) {
        this.id = id;
        this.values = values.clone();
    }

    @Override
    public double[] values() {
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
        return new StringBuilder()
                .append("DoubleArray{")
                .append("\n")
                .append("id=").append(id)
                .append("\n")
                .append(", values=").append(Arrays.toString(values))
                .append('}')
                .toString();
    }
}