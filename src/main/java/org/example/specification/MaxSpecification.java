package org.example.specification;

import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.warehouse.Warehouse;

import java.util.Optional;

public class MaxSpecification implements Specification {

    private final double expected;
    private final ComparisonOperator operator;
    private final Warehouse warehouse;
    private final DoubleValueComparator comparator;

    public MaxSpecification(
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

        if (arrayStatistics.getMax().isEmpty()) {
            return false;
        }

        double max = arrayStatistics.getMax().getAsDouble();

        return comparator.compare(max, expected, operator);
    }
}