package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DoubleArrayTest {

    @Test
    void shouldStoreArrayValues() {
        //given
        double[] values = {1.0, 2.0, 3.0};

        //when
        DoubleArray array = new DoubleArray(values);

        //then
        assertArrayEquals(values, array.getValues());
    }

    @Test
    void shouldReturnCopyOfArray() {
        //given
        double[] values = {1.0, 2.0, 3.0};
        DoubleArray array = new DoubleArray(values);

        //when
        double[] result = array.getValues();
        result[0] = 100.0;

        //then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                array.getValues()
        );
    }
}
