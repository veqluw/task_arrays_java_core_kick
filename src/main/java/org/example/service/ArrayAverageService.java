package org.example.service;

import org.example.entity.DoubleArray;

import java.util.OptionalDouble;

public interface ArrayAverageService {
    OptionalDouble calculateAverage(DoubleArray array);
}
