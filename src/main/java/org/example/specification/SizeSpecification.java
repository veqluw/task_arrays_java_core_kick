package org.example.specification;

import org.example.entity.DoubleArray;

public class SizeSpecification implements Specification {

    private final int expected;
    private final ComparisonOperator operator;

    public SizeSpecification(
            int expected,
            ComparisonOperator operator) {

        this.expected = expected;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfiedBy(DoubleArray array) {
        int actual = array.size();

        return switch (operator) {
            case GREATER -> actual > expected;
            case LESS -> actual < expected;
            case EQUAL -> actual == expected;
        };
    }
}