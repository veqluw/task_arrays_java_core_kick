package org.example.service;

import org.example.entity.DoubleArray;
import org.example.service.impl.ArraySumServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArraySumServiceTest {

    private final ArraySumService service =
            new ArraySumServiceImpl();

    @Test
    void shouldCalculateSum() {
        //given
        DoubleArray array =
                new DoubleArray(new double[]{1.0, 2.0, 3.0});

        //when
        OptionalDouble result = service.calculateSum(array);

        //then
        assertEquals(OptionalDouble.of(6.0), result);
    }

    @Test
    void shouldCalculateSumForNegativeNumbers() {
        //given
        DoubleArray array =
                new DoubleArray(new double[]{-1.0, -2.0, -3.0});

        //when
        OptionalDouble result = service.calculateSum(array);

        //then
        assertEquals(OptionalDouble.of(-6.0), result);
    }

    @Test
    void shouldReturnEmptyForEmptyArray() {
        //given
        DoubleArray array = new DoubleArray(new double[]{});

        //when
        OptionalDouble result = service.calculateSum(array);

        //then
        assertTrue(result.isEmpty());
    }
}
