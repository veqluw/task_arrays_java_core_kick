package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.service.ArrayAverageService;

import java.util.OptionalDouble;

public class ArrayAverageServiceImpl implements ArrayAverageService {

    private static final Logger LOGGER =
            LogManager.getLogger(ArrayAverageServiceImpl.class);

    @Override
    public OptionalDouble calculateAverage(DoubleArray array) {
        LOGGER.debug("Calculating average value");

        double[] values = array.getValues();

        if (values.length == 0) {
            return OptionalDouble.empty();
        }

        double average = 0;

        for (double value : values) {
            average += value;
        }

        average /= values.length;

        LOGGER.debug("Average calculation completed");

        return OptionalDouble.of(average);
    }
}
