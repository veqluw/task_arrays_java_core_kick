package org.example.entity;

import java.util.OptionalDouble;
import java.util.function.DoubleConsumer;

public class ArrayStatistics {

    private final OptionalDouble sum;
    private final OptionalDouble average;
    private final OptionalDouble min;
    private final OptionalDouble max;

    public ArrayStatistics(
            OptionalDouble sum,
            OptionalDouble average,
            OptionalDouble min,
            OptionalDouble max) {

        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public OptionalDouble getSum() {
        return sum;
    }

    public OptionalDouble getAverage() {
        return average;
    }

    public OptionalDouble getMin() {
        return min;
    }

    public OptionalDouble getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Statistics:{" +
                "sum: " + sum.getAsDouble() +
                ", average: " + average.getAsDouble() +
                ", min: " + min.getAsDouble() +
                ", max: " + max.getAsDouble();
    }
}