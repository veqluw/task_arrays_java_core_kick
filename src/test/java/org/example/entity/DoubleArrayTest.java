package org.example.entity;

import org.example.observer.NoOpArrayObserver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DoubleArrayTest {

    @Test
    void shouldStoreArrayValues() {
        //given
        double[] values = {1.0, 2.0, 3.0};

        // when
        DoubleArray array = new DoubleArray(
                1L, values, new NoOpArrayObserver()
        );

        // then
        assertArrayEquals(values, array.values());
    }

    @Test
    void shouldReturnCopyOfArray() {
        // given
        double[] values = {1.0, 2.0, 3.0};
        DoubleArray array = new DoubleArray(
                1L, values, new NoOpArrayObserver()
        );

        // when
        double[] result = array.values();
        result[0] = 100.0;

        // then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                array.values()
        );
    }

    @Test
    void shouldCopyInputArrayInConstructor() {
        // given
        double[] values = {1.0, 2.0, 3.0};
        DoubleArray array = new DoubleArray(
                1L, values, new NoOpArrayObserver()
        );

        // when
        values[0] = 100.0;

        // then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                array.values()
        );
    }
}