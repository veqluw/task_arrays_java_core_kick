package org.example.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.ArrayFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArrayFileReader {

    private static final Logger LOGGER =
            LogManager.getLogger(ArrayFileReader.class);

    public List<String> read(String filePath) throws ArrayFileException {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            LOGGER.info("File successfully read: {}", filePath);
            return lines;
        } catch (IOException exception) {
            LOGGER.error("Failed to read file: {}", filePath, exception);
            throw new ArrayFileException(
                    "Unable to read file: " + filePath,
                    exception
            );
        }
    }
}
