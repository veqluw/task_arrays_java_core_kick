package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.service.ArraySortService;

public class ArraySortServiceImpl implements ArraySortService {

    private static final Logger LOGGER =
            LogManager.getLogger(ArraySortServiceImpl.class);

    @Override
    public double[] insertionSort(DoubleArray array) {
        LOGGER.debug("Starting insertion sort");

        double[] values = array.getValues();

        if (values.length == 0) {
            return values;
        }

        for (int i = 1; i < values.length; ++i) {
            double key = values[i];
            int j = i - 1;

            while (j >= 0 && values[j] > key) {
                values[j + 1] = values[j];
                j = j - 1;
            }
            values[j + 1] = key;
        }

        LOGGER.debug("Insertion sorting completed");

        return values;
    }

    @Override
    public double[] quickSort(DoubleArray array) {
        LOGGER.debug("Starting quick sort");

        double[] values = array.getValues();
        if (values.length == 0) {
            return values;
        }

        sort(values, 0, values.length - 1);

        LOGGER.debug("Quick sorting completed");


        return values;
    }

    private int partition(double[] arr, int low, int high) {
        double pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void sort(double[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

}
