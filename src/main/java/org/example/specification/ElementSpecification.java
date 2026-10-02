package org.example.specification;

import org.example.entity.DoubleArray;

public class ElementSpecification implements Specification {

    private final double expectedValue;

    public ElementSpecification(double expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(DoubleArray array) {
        double[] values = array.getValues();

        for (double value : values) {
            if (Double.compare(value, expectedValue) == 0) {
                return true;
            }
        }

        return false;
    }
}