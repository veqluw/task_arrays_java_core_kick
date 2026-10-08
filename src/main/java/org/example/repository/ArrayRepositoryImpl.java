package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.DoubleArray;
import org.example.observer.ArrayObserver;
import org.example.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ArrayRepositoryImpl implements ArrayRepository {

    private static final Logger LOGGER = LogManager.getLogger(ArrayRepositoryImpl.class);

    private static ArrayRepositoryImpl instance;

    private final List<DoubleArray> arrays;
    private final List<ArrayObserver> observers;

    private ArrayRepositoryImpl() {
        arrays = new ArrayList<>();
        observers = new ArrayList<>();
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
                array.getId()
        );

        notifyObserversUpdate(array);
    }

    @Override
    public void remove(DoubleArray array) {
        boolean removed = arrays.remove(array);

        if (removed) {
            LOGGER.info(
                    "Array removed: id={}",
                    array.getId()
            );
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

        notifyObserversUpdate(array);
    }

    @Override
    public Optional<DoubleArray> findById(long id) {
        return arrays.stream()
                .filter(array -> array.getId() == id)
                .findFirst();
    }

    @Override
    public void deleteById(long id) {
        Optional<DoubleArray> result = findById(id);

        if(isNotExists(result, id)) return;

        if (result.isPresent()) {
            arrays.remove(result.get());
            notifyObserversDelete(result.get());
        }
    }

    @Override
    public List<DoubleArray> find(
            Specification... specifications) {
        if (specifications.length == 0) {
            return arrays.stream().toList();
        }

        return arrays.stream()
                .filter(doubleArray -> {
                    boolean isSatisfied = true;
                    for (Specification specification : specifications) {
                        isSatisfied = specification.isSatisfiedBy(doubleArray);
                        if (!isSatisfied) return isSatisfied;
                    }
                    return isSatisfied;
                })
                .toList();
    }

    @Override
    public List<DoubleArray> findAll() {
        return List.copyOf(arrays);
    }

    @Override
    public List<DoubleArray> sort(
            Comparator<DoubleArray> comparator) {

        List<DoubleArray> result =
                new ArrayList<>(arrays);

        result.sort(comparator);

        return result;
    }

    @Override
    public void addObserver(ArrayObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ArrayObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversUpdate(DoubleArray array) {
        for (ArrayObserver observer : observers) {
            observer.update(array);
        }
    }

    @Override
    public void notifyObserversDelete(DoubleArray array) {
        for (ArrayObserver observer : observers) {
            observer.delete(array);
        }
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