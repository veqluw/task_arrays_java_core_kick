package org.example.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.parser.ArrayParser;

public class DoubleArrayFactory implements ArrayFactory {

    private static final Logger LOGGER =
            LogManager.getLogger(DoubleArrayFactory.class);

    private final ArrayParser parser;

    public DoubleArrayFactory(ArrayParser parser) {
        this.parser = parser;
    }

    @Override
    public DoubleArray create(long id, String data) {
        LOGGER.debug(
                "Creating DoubleArray with id={}",
                id
        );

        double[] values = parser.parse(data);

        return new DoubleArray(id, values);
    }
}