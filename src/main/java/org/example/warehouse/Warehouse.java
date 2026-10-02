package org.example.warehouse;

import org.example.entity.ArrayStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse {

    private static Warehouse instance;

    private final Map<Long, ArrayStatistics> statistics;

    private Warehouse() {
        statistics = new HashMap<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }

        return instance;
    }

    public void save(long arrayId, ArrayStatistics arrayStatistics) {
        statistics.put(arrayId, arrayStatistics);
    }

    public Optional<ArrayStatistics> findById(long arrayId) {
        return Optional.ofNullable(statistics.get(arrayId));
    }

    public void remove(long arrayId) {
        statistics.remove(arrayId);
    }
}
