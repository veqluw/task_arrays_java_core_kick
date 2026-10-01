package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.service.ArrayMinMaxService;

import java.util.OptionalDouble;

public class ArrayMinMaxServiceImpl implements ArrayMinMaxService {

    private static final Logger LOGGER =
            LogManager.getLogger(ArrayMinMaxServiceImpl.class);

    @Override
    public OptionalDouble findMax(DoubleArray array) {
        LOGGER.debug("Calculating max value");

        double[] values = array.getValues();

        if (values.length == 0) {
            return OptionalDouble.empty();
        }

        double max = values[0];

        for (int i = 1; i < values.length; ++i) {
            if (values[i] > max) {
                max = values[i];
            }
        }

        LOGGER.debug("Max value calculation completed");

        return OptionalDouble.of(max);
    }

    @Override
    public OptionalDouble findMin(DoubleArray array) {
        LOGGER.debug("Calculating min value");

        double[] values = array.getValues();

        if (values.length == 0) {
            return OptionalDouble.empty();
        }

        double min = values[0];

        for (int i = 1; i < values.length; ++i) {
            if (values[i] < min) {
                min = values[i];
            }
        }

        LOGGER.debug("Min value calculating completed");

        return OptionalDouble.of(min);
    }
}
