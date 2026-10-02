package org.example.specification;

import org.example.entity.DoubleArray;

public interface Specification {

    boolean isSatisfiedBy(DoubleArray array);
}
