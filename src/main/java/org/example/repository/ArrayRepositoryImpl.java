package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ArrayRepositoryImpl implements ArrayRepository {

    private static final Logger LOGGER = LogManager.getLogger(ArrayRepositoryImpl.class);

    private static ArrayRepositoryImpl instance;

    private final List<DoubleArray> arrays;

    private ArrayRepositoryImpl() {
        arrays = new ArrayList<>();
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }

        return instance;
    }

    @Override
    public void add(DoubleArray array) {
        arrays.add(array);

        LOGGER.info(
                "Array added: id={}",
                array.id()
        );

        array.notifyUpdate();
    }

    @Override
    public void remove(DoubleArray array) {
        boolean removed = arrays.remove(array);

        if (removed) {
            LOGGER.info(
                    "Array removed: id={}",
                    array.id()
            );
            array.notifyDelete();
        }
    }

    @Override
    public void updateValue(
            long id,
            int index,
            double value) {

        Optional<DoubleArray> result = findById(id);

        if (isNotExists(result, id)) return;

        DoubleArray array = result.get();
        array.setValue(index, value);

        LOGGER.info(
                "Array updated: id={}, index={}, value={}",
                id,
                index,
                value
        );

        array.notifyUpdate();
    }

    @Override
    public void deleteById(long id) {
        Optional<DoubleArray> result = findById(id);

        if (isNotExists(result, id)) {
            return;
        }

        DoubleArray array = result.get();
        boolean removed = arrays.remove(array);

        if (removed) {
            array.notifyDelete();

            LOGGER.info("Array removed: id={}", id);
        }
    }

    @Override
    public List<DoubleArray> find(
            Specification... specifications) {

        Predicate<DoubleArray> predicate = array -> true;

        for (Specification specification : specifications) {
            predicate = predicate.and(specification::isSatisfiedBy);
        }

        return arrays.stream()
                .filter(predicate)
                .toList();
    }

    @Override
    public List<DoubleArray> sort(
            Comparator<DoubleArray> comparator) {

        List<DoubleArray> result =
                new ArrayList<>(arrays);

        result.sort(comparator);

        return result;
    }

    private Optional<DoubleArray> findById(long id) {
        return arrays.stream()
                .filter(array -> array.id() == id)
                .findFirst();
    }

    private boolean isNotExists(Optional<DoubleArray> array, long id) {
        if (array.isEmpty()) {
            LOGGER.warn(
                    "Array not found: id={}",
                    id
            );
            return true;
        }
        return false;
    }
}