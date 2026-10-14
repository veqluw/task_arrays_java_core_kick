package org.example.entity;

import org.example.observer.ArrayObservable;
import org.example.observer.ArrayObserver;

import java.util.Arrays;

public record DoubleArray(long id,
                          double[] values,
                          ArrayObserver observer) implements ArrayObservable {

    public DoubleArray(long id, double[] values, ArrayObserver observer) {
        this.id = id;
        this.values = values.clone();
        this.observer = observer;
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
    public void notifyUpdate() {
        observer.update(this);
    }

    @Override
    public void notifyDelete() {
        observer.delete(this);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("DoubleArray{")
                .append("id=").append(id)
                .append(", values=").append(Arrays.toString(values))
                .append('}')
                .toString();
    }
}