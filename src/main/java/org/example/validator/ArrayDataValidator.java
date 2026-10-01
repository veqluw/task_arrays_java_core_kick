package org.example.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayDataValidator {

    private static final String DOUBLE_PATTERN =
            "-?\\d+(\\.\\d+)?((\\s*[,;]\\s*|\\s+-\\s+|\\s+)-?\\d+(\\.\\d+)?)*";

    private static final Logger LOGGER =
            LogManager.getLogger(ArrayDataValidator.class);

    public boolean isValid(String data) {
        boolean valid;

        if (data != null) {
             valid = data.isBlank() || data.matches(DOUBLE_PATTERN);
        } else {
             valid = false;
        }
        if (!valid) {
            LOGGER.warn("Invalid array data: {}", data);
        }
        return valid;
    }
}
