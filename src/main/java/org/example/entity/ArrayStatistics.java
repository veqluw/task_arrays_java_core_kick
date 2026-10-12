package org.example.entity;

import java.util.OptionalDouble;

public record ArrayStatistics(OptionalDouble sum, OptionalDouble average, OptionalDouble min, OptionalDouble max) {

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Statistics:{").append("\n")
                .append("sum: ").append(sum.getAsDouble()).append("\n")
                .append(", average: ").append(average.getAsDouble()).append("\n")
                .append(", min: ").append(min.getAsDouble()).append("\n")
                .append(", max: ").append(max.getAsDouble())
                .toString();
    }
}