package org.example.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.factory.ArrayFactory;
import org.example.factory.DoubleArrayFactory;
import org.example.parser.ArrayParser;
import org.example.reader.ArrayFileReader;
import org.example.service.ArrayAverageService;
import org.example.service.ArrayMinMaxService;
import org.example.service.ArraySortService;
import org.example.service.ArraySumService;
import org.example.service.impl.ArrayAverageServiceImpl;
import org.example.service.impl.ArrayMinMaxServiceImpl;
import org.example.service.impl.ArraySortServiceImpl;
import org.example.service.impl.ArraySumServiceImpl;
import org.example.validator.ArrayDataValidator;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class ApplicationRunner {

    private static final Logger LOGGER =
            LogManager.getLogger(ApplicationRunner.class);

    private static final String FILE_PATH = "data/arrays.txt";

    public void run() {
        LOGGER.info("Application started");

        ArrayDataValidator validator = new ArrayDataValidator();
        ArrayParser parser = new ArrayParser();
        ArrayFileReader reader = new ArrayFileReader();
        ArrayFactory factory = new DoubleArrayFactory(parser);

        ArrayMinMaxService minMaxService = new ArrayMinMaxServiceImpl();
        ArraySumService sumService = new ArraySumServiceImpl();
        ArrayAverageService averageService = new ArrayAverageServiceImpl();
        ArraySortService sortService = new ArraySortServiceImpl();

        List<String> lines = reader.read(FILE_PATH);

        for (String line : lines) {
            if (validator.isValid(line)) {
                DoubleArray array = factory.create(line);

                OptionalDouble min = minMaxService.findMin(array);
                OptionalDouble max = minMaxService.findMax(array);
                OptionalDouble sum = sumService.calculateSum(array);
                OptionalDouble average = averageService.calculateAverage(array);

                LOGGER.info("Array: {}", array);

                min.ifPresentOrElse(
                        value -> LOGGER.info("Min: {}", value),
                        () -> LOGGER.info("Min: no value")
                );

                max.ifPresentOrElse(
                        value -> LOGGER.info("Max: {}", value),
                        () -> LOGGER.info("Max: no value")
                );

                sum.ifPresentOrElse(
                        value -> LOGGER.info("Sum: {}", value),
                        () -> LOGGER.info("Sum: no value")
                );

                average.ifPresentOrElse(
                        value -> LOGGER.info("Average: {}", value),
                        () -> LOGGER.info("Average: no value")
                );

                double[] insertionSorted = sortService.insertionSort(array);
                double[] quickSorted = sortService.quickSort(array);

                LOGGER.info("Quick sort: {}", Arrays.toString(insertionSorted));
                LOGGER.info("Insertion sort: {}", Arrays.toString(quickSorted));
            } else {
                LOGGER.warn("Invalid data skipped: {}", line);
            }
        }

        LOGGER.info("Application finished");
    }
}
