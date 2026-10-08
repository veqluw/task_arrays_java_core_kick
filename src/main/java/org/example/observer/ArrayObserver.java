package org.example.observer;

import org.example.entity.DoubleArray;

public interface ArrayObserver {

    void update(DoubleArray array);

    void delete(DoubleArray array);
}