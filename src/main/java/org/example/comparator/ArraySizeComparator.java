package org.example.comparator;

import org.example.entity.DoubleArray;

import java.util.Comparator;

public class ArraySizeComparator
        implements Comparator<DoubleArray> {

    @Override
    public int compare(
            DoubleArray first,
            DoubleArray second) {

        return Integer.compare(
                first.size(),
                second.size()
        );
    }
}