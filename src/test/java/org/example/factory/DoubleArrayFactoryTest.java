package org.example.factory;

import org.example.entity.DoubleArray;
import org.example.parser.ArrayParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DoubleArrayFactoryTest {

    private final ArrayParser parser =
            new ArrayParser();

    private final ArrayFactory factory =
            new DoubleArrayFactory(parser);

    @Test
    public void shouldCreateDoubleArray() {
        //given
        String data = "1, 2, 3";

        //when
        DoubleArray result = factory.create(data);

        //then
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                result.getValues()
        );
    }
}