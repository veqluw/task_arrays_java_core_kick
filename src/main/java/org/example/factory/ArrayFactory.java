package org.example.factory;

import org.example.entity.DoubleArray;

public interface ArrayFactory {

    DoubleArray create(long id, String data);
}