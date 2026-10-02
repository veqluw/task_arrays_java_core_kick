package org.example.specification;

import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.warehouse.Warehouse;

import java.util.Optional;

public class MinSpecification implements Specification {

    private final double expected;
    private final ComparisonOperator operator;
    private final Warehouse warehouse;
    private final DoubleValueComparator comparator;

    public MinSpecification(
            double expected,
            ComparisonOperator operator,
            Warehouse warehouse) {

        this.expected = expected;
        this.operator = operator;
        this.warehouse = warehouse;
        comparator = new DoubleValueComparator();
    }

    @Override
    public boolean isSatisfiedBy(DoubleArray array) {
        Optional<ArrayStatistics> statistics =
                warehouse.findById(array.getId());

        if (statistics.isEmpty()) {
            return false;
        }

        ArrayStatistics arrayStatistics = statistics.get();

        if (arrayStatistics.getMin().isEmpty()) {
            return false;
        }

        double min = arrayStatistics.getMin().getAsDouble();

        return comparator.compare(min, expected, operator);
    }
}