package org.example.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayParserTest {

    private final ArrayParser parser =
            new ArrayParser();

    @Test
    public void shouldParseCommaSeparatedNumbers() {
        //given
        String data = "1, 2, 3";

        //when
        double[] result = parser.parse(data);

        //then
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, result);
    }

    @Test
    public void shouldParseSpaceSeparatedNumbers() {
        //given
        String data = "3 4 7";

        //when
        double[] result = parser.parse(data);

        //then
        assertArrayEquals(new double[]{3.0, 4.0, 7.0}, result);
    }

    @Test
    public void shouldParseNegativeNumbers() {
        //given
        String data = "-1, -2, -3";

        //when
        double[] result = parser.parse(data);

        //then
        assertArrayEquals(new double[]{-1.0, -2.0, -3.0}, result);
    }

    @Test
    public void shouldReturnEmptyArrayForEmptyString() {
        //given
        String data = "";

        //when
        double[] result = parser.parse(data);

        //then
        assertArrayEquals(new double[]{}, result);
    }
}
