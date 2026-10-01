package org.example.service;

import org.example.entity.DoubleArray;

public interface ArraySortService {
    double[] insertionSort(DoubleArray array);

    double[] quickSort(DoubleArray array);
}
