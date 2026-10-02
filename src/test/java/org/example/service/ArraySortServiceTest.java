package org.example.service;

import org.example.entity.DoubleArray;
import org.example.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraySortServiceTest {

    private final ArraySortService service =
            new ArraySortServiceImpl();

    @Test
    void shouldSortArrayUsingInsertionSort() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{5.0, 1.0, 4.0, 2.0, 3.0});

        //when
        double[] result = service.insertionSort(array);

        //then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0, 4.0, 5.0},
                result
        );
    }

    @Test
    void shouldSortArrayUsingQuickSort() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{5.0, 1.0, 4.0, 2.0, 3.0});

        //when
        double[] result = service.quickSort(array);

        //then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0, 4.0, 5.0},
                result
        );
    }

    @Test
    void shouldSortAlreadySortedArrayUsingInsertion() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{1.0, 2.0, 3.0});

        //when
        double[] result = service.insertionSort(array);

        //then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                result
        );
    }

    @Test
    void shouldSortArrayWithNegativeNumbers() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{3.0, -1.0, 2.0, -5.0});

        //when
        double[] result = service.quickSort(array);

        //then
        assertArrayEquals(
                new double[]{-5.0, -1.0, 2.0, 3.0},
                result
        );
    }

    @Test
    void shouldSortEmptyArray() {
        //given
        DoubleArray array = new DoubleArray(1L, new double[]{});

        //when
        double[] result = service.insertionSort(array);

        //then
        assertArrayEquals(new double[]{}, result);
    }
}
