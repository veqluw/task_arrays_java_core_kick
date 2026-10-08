package org.example.validator;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayDataValidatorTest {

    private final ArrayDataValidator validator = new ArrayDataValidator();

    @Test
    public void shouldAcceptCommaSeparatedNumbers() {
        //given
        String data = "1, 2, 3";

        //when
        boolean result = validator.isValid(data);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldAcceptSpaceSeparatedNumbers() {
        //given
        String data = "3 4 7";

        //when
        boolean result = validator.isValid(data);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldAcceptNegativeNumbers() {
        //given
        String data = "-1, -2, -3";

        //when
        boolean result = validator.isValid(data);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldDeclineEmptyString() {
        //given
        String data = "";

        //when
        boolean result = validator.isValid(data);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldRejectLetters() {
        //given
        String data = "1y1 21 32";

        //when
        boolean result = validator.isValid(data);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldRejectInvalidNumberFormat() {
        //given
        String data = "1, 2, x";

        //when
        boolean result = validator.isValid(data);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldRejectNull() {
        //given
        String data = null;

        //when
        boolean result = validator.isValid(data);

        //then
        assertFalse(result);
    }
}
