package org.example.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.comparator.ArrayFirstElementComparator;
import org.example.comparator.ArrayIdComparator;
import org.example.comparator.ArraySizeComparator;
import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.factory.ArrayFactory;
import org.example.factory.DoubleArrayFactory;
import org.example.observer.WarehouseObserver;
import org.example.parser.ArrayParser;
import org.example.reader.ArrayFileReader;
import org.example.repository.ArrayRepository;
import org.example.repository.ArrayRepositoryImpl;
import org.example.specification.ComparisonOperator;
import org.example.specification.MaxSpecification;
import org.example.specification.SizeSpecification;
import org.example.specification.SumSpecification;
import org.example.validator.ArrayDataValidator;
import org.example.warehouse.Warehouse;

import java.util.List;
import java.util.Optional;

public class ApplicationRunner {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationRunner.class);

    private static final String FILE_PATH = "data/arrays.txt";

    public void run() {
        LOGGER.info("Application started");

        ArrayDataValidator validator = new ArrayDataValidator();
        ArrayParser parser = new ArrayParser();
        ArrayFileReader reader = new ArrayFileReader();
        ArrayFactory factory = new DoubleArrayFactory(parser);

        ArrayRepository repository = ArrayRepositoryImpl.getInstance();
        Warehouse warehouse = Warehouse.getInstance();
        WarehouseObserver observer = new WarehouseObserver(warehouse);
        repository.addObserver(observer);

        List<String> lines = reader.read(FILE_PATH);

        long id = 1;

        for (String line : lines) {
            if (validator.isValid(line)) {
                DoubleArray array =
                        factory.create(id, line);

                repository.add(array);

                logStatistics(
                        array,
                        warehouse
                );

                id++;
            } else {
                LOGGER.warn(
                        "Invalid data skipped: {}",
                        line
                );
            }
        }

        demonstrateSearch(repository, warehouse);
        demonstrateSorting(repository);

        LOGGER.info("Application finished");
    }

    private void logStatistics(
            DoubleArray array,
            Warehouse warehouse) {

        Optional<ArrayStatistics> result =
                warehouse.findById(array.getId());

        if (result.isEmpty()) {
            return;
        }

        ArrayStatistics statistics =
                result.get();

        LOGGER.info(
                "Array: {}",
                array
        );

        statistics.getMin().ifPresent(
                value -> LOGGER.info(
                        "Min: {}",
                        value
                )
        );

        statistics.getMax().ifPresent(
                value -> LOGGER.info(
                        "Max: {}",
                        value
                )
        );

        statistics.getSum().ifPresent(
                value -> LOGGER.info(
                        "Sum: {}",
                        value
                )
        );

        statistics.getAverage().ifPresent(
                value -> LOGGER.info(
                        "Average: {}",
                        value
                )
        );
    }

    private void demonstrateSearch(
            ArrayRepository repository,
            Warehouse warehouse) {

        List<DoubleArray> arraysWithLargeSum =
                repository.find(
                        new SumSpecification(
                                100,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        LOGGER.info(
                "Arrays with sum > 100: {}",
                arraysWithLargeSum
        );

        List<DoubleArray> arraysWithLargeMax =
                repository.find(
                        new MaxSpecification(
                                50,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        LOGGER.info(
                "Arrays with max > 50: {}",
                arraysWithLargeMax
        );

        List<DoubleArray> largeArrays =
                repository.find(
                        new SizeSpecification(
                                3,
                                ComparisonOperator.GREATER
                        )
                );

        LOGGER.info(
                "Arrays with more than 3 elements: {}",
                largeArrays
        );
    }

    private void demonstrateSorting(
            ArrayRepository repository) {

        List<DoubleArray> byId =
                repository.sort(
                        new ArrayIdComparator()
                );

        LOGGER.info(
                "Sorted by ID: {}",
                byId
        );

        List<DoubleArray> bySize =
                repository.sort(
                        new ArraySizeComparator()
                );

        LOGGER.info(
                "Sorted by size: {}",
                bySize
        );

        List<DoubleArray> byFirstElement =
                repository.sort(
                        new ArrayFirstElementComparator()
                );

        LOGGER.info(
                "Sorted by first element: {}",
                byFirstElement
        );
    }
}