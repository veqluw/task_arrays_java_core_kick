package org.example.repository;

import org.example.entity.DoubleArray;
import org.example.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {

    void add(DoubleArray array);

    void remove(DoubleArray array);

    List<DoubleArray> find(Specification... specification);

    List<DoubleArray> sort(Comparator<DoubleArray> comparator);

    void updateValue(long id, int index, double value);

    void deleteById(long id);

}
