package org.example.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.factory.ArrayFactory;
import org.example.factory.DoubleArrayFactory;
import org.example.observer.WarehouseObserver;
import org.example.parser.ArrayParser;
import org.example.reader.ArrayFileReader;
import org.example.repository.ArrayRepository;
import org.example.repository.ArrayRepositoryImpl;
import org.example.specification.AverageSpecification;
import org.example.specification.ComparisonOperator;
import org.example.specification.ElementSpecification;
import org.example.specification.IdSpecification;
import org.example.specification.MaxSpecification;
import org.example.specification.MinSpecification;
import org.example.specification.SizeSpecification;
import org.example.specification.SumSpecification;
import org.example.validator.ArrayDataValidator;
import org.example.warehouse.Warehouse;
import org.example.comparator.ArrayFirstElementComparator;
import org.example.comparator.ArrayIdComparator;
import org.example.comparator.ArraySizeComparator;

import java.util.List;
import java.util.Optional;

public class ApplicationRunner {

    private static final Logger LOGGER =
            LogManager.getLogger(ApplicationRunner.class);

    private static final String FILE_PATH =
            "data/arrays.txt";

    public void run() {

        LOGGER.info("================================");
        LOGGER.info("APPLICATION STARTED");
        LOGGER.info("================================");

        ArrayDataValidator validator =
                new ArrayDataValidator();

        ArrayParser parser =
                new ArrayParser();

        ArrayFileReader reader =
                new ArrayFileReader();

        ArrayFactory factory =
                new DoubleArrayFactory(parser);

        ArrayRepository repository =
                ArrayRepositoryImpl.getInstance();

        Warehouse warehouse =
                Warehouse.getInstance();

        WarehouseObserver observer =
                new WarehouseObserver(warehouse);

        repository.addObserver(observer);

        loadArrays(
                reader,
                validator,
                factory,
                repository
        );

        demonstrateStatistics(
                repository,
                warehouse
        );

        demonstrateSpecifications(
                repository,
                warehouse
        );

        demonstrateMultipleSpecifications(
                repository,
                warehouse
        );

        demonstrateSorting(repository);

        demonstrateFindById(repository);

        demonstrateRepositoryChanges(
                repository,
                warehouse
        );

        demonstrateDelete(
                repository,
                warehouse
        );

        LOGGER.info("================================");
        LOGGER.info("APPLICATION FINISHED");
        LOGGER.info("================================");
    }

    private void loadArrays(
            ArrayFileReader reader,
            ArrayDataValidator validator,
            ArrayFactory factory,
            ArrayRepository repository) {

        LOGGER.info("========== LOADING ARRAYS ==========");

        List<String> lines = reader.read(FILE_PATH);

        long id = 1;

        for (String line : lines) {

            if (!validator.isValid(line)) {
                LOGGER.warn(
                        "Invalid data skipped: {}",
                        line
                );
                continue;
            }

            DoubleArray array =
                    factory.create(id, line);

            repository.add(array);

            LOGGER.info(
                    "Array added:"
            );

            LOGGER.info(
                    "{}",
                    array
            );

            id++;
        }

        LOGGER.info(
                "Total arrays loaded: {}",
                repository.findAll().size()
        );

    }

    private void demonstrateStatistics(
            ArrayRepository repository,
            Warehouse warehouse) {

        LOGGER.info("========== STATISTICS ==========");

        for (DoubleArray array : repository.findAll()) {

            Optional<ArrayStatistics> result =
                    warehouse.findById(array.getId());

            if (result.isEmpty()) {
                continue;
            }

            ArrayStatistics statistics =
                    result.get();

            LOGGER.info("Array:");
            LOGGER.info("{}", array);

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

            LOGGER.info("--------------------------------");
        }
    }

    private void demonstrateSpecifications(
            ArrayRepository repository,
            Warehouse warehouse) {

        LOGGER.info("========== SPECIFICATIONS ==========");

        List<DoubleArray> idResult =
                repository.find(
                        new IdSpecification(2)
                );

        logArrays(
                "Array with ID = 2:",
                idResult
        );

        List<DoubleArray> sizeResult =
                repository.find(
                        new SizeSpecification(
                                3,
                                ComparisonOperator.GREATER
                        )
                );

        logArrays(
                "Arrays with size > 3:",
                sizeResult
        );

        List<DoubleArray> sumResult =
                repository.find(
                        new SumSpecification(
                                20,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        logArrays(
                "Arrays with sum > 20:",
                sumResult
        );

        List<DoubleArray> averageResult =
                repository.find(
                        new AverageSpecification(
                                5,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        logArrays(
                "Arrays with average > 5:",
                averageResult
        );

        List<DoubleArray> minResult =
                repository.find(
                        new MinSpecification(
                                1,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        logArrays(
                "Arrays with min > 1:",
                minResult
        );

        List<DoubleArray> maxResult =
                repository.find(
                        new MaxSpecification(
                                9,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        logArrays(
                "Arrays with max > 9:",
                maxResult
        );

        List<DoubleArray> elementResult =
                repository.find(
                        new ElementSpecification(5)
                );

        logArrays(
                "Arrays containing element 5:",
                elementResult
        );
    }

    private void demonstrateMultipleSpecifications(
            ArrayRepository repository,
            Warehouse warehouse) {

        LOGGER.info(
                "========== MULTIPLE SPECIFICATIONS =========="
        );

        List<DoubleArray> result =
                repository.find(
                        new SizeSpecification(
                                3,
                                ComparisonOperator.GREATER
                        ),
                        new SumSpecification(
                                10,
                                ComparisonOperator.GREATER,
                                warehouse
                        ),
                        new AverageSpecification(
                                3,
                                ComparisonOperator.GREATER,
                                warehouse
                        )
                );

        logArrays(
                "Arrays satisfying all conditions:",
                result
        );
    }

    private void demonstrateSorting(
            ArrayRepository repository) {

        LOGGER.info("========== SORTING ==========");

        List<DoubleArray> byId =
                repository.sort(
                        new ArrayIdComparator()
                );

        logArrays(
                "Sorted by ID:",
                byId
        );

        List<DoubleArray> bySize =
                repository.sort(
                        new ArraySizeComparator()
                );

        logArrays(
                "Sorted by size:",
                bySize
        );

        List<DoubleArray> byFirstElement =
                repository.sort(
                        new ArrayFirstElementComparator()
                );

        logArrays(
                "Sorted by first element:",
                byFirstElement
        );
    }

    private void demonstrateFindById(
            ArrayRepository repository) {

        LOGGER.info("========== FIND BY ID ==========");

        long id = 2;

        Optional<DoubleArray> result =
                repository.findById(id);

        result.ifPresentOrElse(
                array -> {
                    LOGGER.info(
                            "Array with ID {} found:",
                            id
                    );

                    LOGGER.info(
                            "{}",
                            array
                    );
                },
                () -> LOGGER.warn(
                        "Array with ID {} not found",
                        id
                )
        );
    }

    private void demonstrateRepositoryChanges(
            ArrayRepository repository,
            Warehouse warehouse) {

        LOGGER.info(
                "========== UPDATE / OBSERVER =========="
        );

        long id = 1;

        Optional<DoubleArray> result =
                repository.findById(id);

        if (result.isEmpty()) {
            LOGGER.warn(
                    "Array with ID {} not found",
                    id
            );
            return;
        }

        DoubleArray array = result.get();

        LOGGER.info("Before update:");
        LOGGER.info("{}", array);

        warehouse.findById(id)
                .ifPresent(
                        statistics -> LOGGER.info(
                                "Statistics before update: {}",
                                statistics
                        )
                );

       repository.updateValue(1, 0, 2.4);

        LOGGER.info("After update:");
        LOGGER.info("{}", array);

        warehouse.findById(id)
                .ifPresent(
                        statistics -> LOGGER.info(
                                "Statistics after update: {}",
                                statistics
                        )
                );
    }

    private void demonstrateDelete(
            ArrayRepository repository,
            Warehouse warehouse) {

        LOGGER.info("========== DELETE ==========");

        long id = 2;

        LOGGER.info(
                "Before deletion:"
        );

        repository.findById(id)
                .ifPresentOrElse(
                        array -> LOGGER.info(
                                "{}",
                                array
                        ),
                        () -> LOGGER.info(
                                "Array not found"
                        )
                );

        repository.deleteById(id);

        LOGGER.info(
                "After deletion:"
        );

        repository.findById(id)
                .ifPresentOrElse(
                        array -> LOGGER.info(
                                "{}",
                                array
                        ),
                        () -> LOGGER.info(
                                "Array not found"
                        )
                );

        LOGGER.info(
                "Warehouse statistics after deletion:"
        );

        warehouse.findById(id)
                .ifPresentOrElse(
                        statistics -> LOGGER.info(
                                "{}",
                                statistics
                        ),
                        () -> LOGGER.info(
                                "Statistics not found"
                        )
                );
    }

    private void logArrays(
            String title,
            List<DoubleArray> arrays) {

        LOGGER.info(title);

        if (arrays.isEmpty()) {
            LOGGER.info("No arrays found.");
            return;
        }

        for (DoubleArray array : arrays) {
            LOGGER.info(
                    "{}",
                    array
            );
        }

        LOGGER.info("--------------------------------");
    }
}