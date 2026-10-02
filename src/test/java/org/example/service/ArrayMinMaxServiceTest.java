package org.example.service;

import org.example.entity.DoubleArray;
import org.example.service.impl.ArrayMinMaxServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayMinMaxServiceTest {

    private final ArrayMinMaxService service =
            new ArrayMinMaxServiceImpl();

    @Test
    void shouldFindMinimumValue() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{5.0, 2.0, 8.0, 1.0});

        //when
        OptionalDouble result = service.findMin(array);

        //then
        assertEquals(OptionalDouble.of(1.0), result);
    }

    @Test
    void shouldFindMaximumValue() {
        //given
        DoubleArray array =
                new DoubleArray(1L, new double[]{5.0, 2.0, 8.0, 1.0});

        //when
        OptionalDouble result = service.findMax(array);

        //then
        assertEquals(OptionalDouble.of(8.0), result);
    }

    @Test
    void shouldReturnEmptyForMinimumOfEmptyArray() {
        //given
        DoubleArray array = new DoubleArray(1L, new double[]{});

        //when
        OptionalDouble result = service.findMin(array);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyForMaximumOfEmptyArray() {
        //given
        DoubleArray array = new DoubleArray(1L, new double[]{});

        //when
        OptionalDouble result = service.findMax(array);

        //then
        assertTrue(result.isEmpty());
    }
}
