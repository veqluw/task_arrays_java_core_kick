package org.example.service;

import org.example.entity.DoubleArray;

import java.util.OptionalDouble;

public interface ArrayMinMaxService {
    OptionalDouble findMax(DoubleArray array);
    OptionalDouble findMin(DoubleArray array);
}
