package org.example.factory;

import org.example.entity.DoubleArray;

public interface ArrayFactory {
    DoubleArray create(String data);
}
