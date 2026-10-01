package org.example.service;

import org.example.entity.DoubleArray;

import java.util.OptionalDouble;

public interface ArraySumService {
    OptionalDouble calculateSum(DoubleArray array);
}
