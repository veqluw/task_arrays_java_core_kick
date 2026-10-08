package org.example.repository;

import org.example.entity.DoubleArray;
import org.example.observer.ArrayObservable;
import org.example.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository extends ArrayObservable {

    void add(DoubleArray array);

    void remove(DoubleArray array);

    Optional<DoubleArray> findById(long id);

    List<DoubleArray> find(Specification... specification);

    List<DoubleArray> findAll();

    List<DoubleArray> sort(Comparator<DoubleArray> comparator);

    void updateValue(long id, int index, double value);

    void deleteById(long id);

}
