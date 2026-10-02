package org.example.observer;

import org.example.entity.DoubleArray;

public interface ArrayObservable {

    void addObserver(ArrayObserver observer);

    void removeObserver(ArrayObserver observer);

    void notifyObservers(DoubleArray array);
}