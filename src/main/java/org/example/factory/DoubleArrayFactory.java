package org.example.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.parser.ArrayParser;

public class DoubleArrayFactory implements ArrayFactory {

    private final ArrayParser parser;
    private static final Logger LOGGER =
            LogManager.getLogger(DoubleArrayFactory.class);

    public DoubleArrayFactory(ArrayParser parser) {
        this.parser = parser;
    }

    @Override
    public DoubleArray create(String data) {
        LOGGER.debug("Creating DoubleArray from input data");
        double[] values = parser.parse(data);
        return new DoubleArray(values);
    }
}
