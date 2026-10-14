package org.example.observer;

import org.example.entity.DoubleArray;

public class NoOpArrayObserver implements ArrayObserver {

    @Override
    public void update(DoubleArray array) {
    }

    @Override
    public void delete(DoubleArray array) {
    }
}
