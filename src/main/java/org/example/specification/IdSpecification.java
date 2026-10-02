package org.example.specification;

import org.example.entity.DoubleArray;

public class IdSpecification implements Specification {

    private final long expectedId;

    public IdSpecification(long expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public boolean isSatisfiedBy(DoubleArray array) {
        return array.getId() == expectedId;
    }
}
