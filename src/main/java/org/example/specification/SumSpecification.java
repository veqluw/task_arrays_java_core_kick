package org.example.specification;

import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.warehouse.Warehouse;

import java.util.Optional;

public class SumSpecification implements Specification {

    private final double expected;
    private final ComparisonOperator operator;
    private final Warehouse warehouse;
    private final DoubleValueComparator comparator;

    public SumSpecification(
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

        if (arrayStatistics.getSum().isEmpty()) {
            return false;
        }

        double sum = arrayStatistics.getSum().getAsDouble();

        return comparator.compare(sum, expected, operator);
    }
}