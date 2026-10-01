package org.example.parser;

public class ArrayParser {

    private static final String VALUE_SEPARATOR_PATTERN = "\\s*[,;]\\s*|\\s+-\\s+|\\s+";

    public double[] parse(String data) {
        if (data.isBlank()) {
            return new double[]{};
        }

        String[] values = data.trim().split(VALUE_SEPARATOR_PATTERN);
        double[] result = new double[values.length];

        for (int i = 0; i < values.length; ++i) {
            result[i] = Double.parseDouble(values[i]);
        }

        return result;
    }
}
