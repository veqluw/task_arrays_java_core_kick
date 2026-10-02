package org.example.specification;

public class DoubleValueComparator {

    public boolean compare(
            double actual,
            double expected,
            ComparisonOperator operator) {

        return switch (operator) {
            case GREATER -> actual > expected;
            case LESS -> actual < expected;
            case EQUAL -> Double.compare(actual, expected) == 0;
        };
    }
}