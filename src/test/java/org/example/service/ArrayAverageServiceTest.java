package org.example.service;

import org.example.entity.DoubleArray;
import org.example.service.impl.ArrayAverageServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayAverageServiceTest {

    private final ArrayAverageService service =
            new ArrayAverageServiceImpl();

    @Test
    void shouldCalculateAverage() {
        //given
        DoubleArray array =
                new DoubleArray(new double[]{2.0, 4.0, 6.0});

        //when
        OptionalDouble result = service.calculateAverage(array);

        //then
        assertEquals(OptionalDouble.of(4.0), result);
    }

    @Test
    void shouldCalculateAverageForFractionalResult() {
        //given
        DoubleArray array =
                new DoubleArray(new double[]{1.0, 2.0});

        //when
        OptionalDouble result = service.calculateAverage(array);

        //then
        assertEquals(OptionalDouble.of(1.5), result);
    }

    @Test
    void shouldReturnEmptyForEmptyArray() {
        //given
        DoubleArray array = new DoubleArray(new double[]{});

        //when
        OptionalDouble result = service.calculateAverage(array);

        //then
        assertTrue(result.isEmpty());
    }
}
