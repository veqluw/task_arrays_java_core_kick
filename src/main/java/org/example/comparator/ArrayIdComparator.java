package org.example.comparator;

import org.example.entity.DoubleArray;

import java.util.Comparator;

public class ArrayIdComparator
        implements Comparator<DoubleArray> {

    @Override
    public int compare(
            DoubleArray first,
            DoubleArray second) {

        return Long.compare(
                first.getId(),
                second.getId()
        );
    }
}