package org.example.comparator;

import org.example.entity.DoubleArray;

import java.util.Comparator;

public class ArrayFirstElementComparator
        implements Comparator<DoubleArray> {

    @Override
    public int compare(
            DoubleArray first,
            DoubleArray second) {

        double[] firstValues = first.getValues();
        double[] secondValues = second.getValues();

        if (firstValues.length == 0
                && secondValues.length == 0) {
            return 0;
        }

        if (firstValues.length == 0) {
            return -1;
        }

        if (secondValues.length == 0) {
            return 1;
        }

        return Double.compare(
                firstValues[0],
                secondValues[0]
        );
    }
}