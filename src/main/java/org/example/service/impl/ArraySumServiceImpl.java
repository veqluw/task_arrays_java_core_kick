package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.service.ArraySumService;

import java.util.OptionalDouble;

public class ArraySumServiceImpl implements ArraySumService {

    private static final Logger LOGGER =
            LogManager.getLogger(ArraySumServiceImpl.class);

    @Override
    public OptionalDouble calculateSum(DoubleArray array) {
        LOGGER.debug("Calculating sum");

        double[] values = array.getValues();

        if (values.length == 0) {
            return OptionalDouble.empty();
        }

        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        LOGGER.debug("Sum calculating completed");

        return OptionalDouble.of(sum);
    }
}
