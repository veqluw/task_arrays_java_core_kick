package org.example.entity;

import java.util.OptionalDouble;

public record ArrayStatistics(OptionalDouble sum,
                              OptionalDouble average,
                              OptionalDouble min,
                              OptionalDouble max) {

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Statistics{")
                .append("sum=").append(sum.getAsDouble())
                .append(", average=").append(average.getAsDouble())
                .append(", min=").append(min.getAsDouble())
                .append(", max=").append(max.getAsDouble())
                .append('}')
                .toString();
    }
}