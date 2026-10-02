package org.example.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.ArrayStatistics;
import org.example.entity.DoubleArray;
import org.example.service.ArrayAverageService;
import org.example.service.ArrayMinMaxService;
import org.example.service.ArraySumService;
import org.example.service.impl.ArrayAverageServiceImpl;
import org.example.service.impl.ArrayMinMaxServiceImpl;
import org.example.service.impl.ArraySumServiceImpl;
import org.example.warehouse.Warehouse;

import java.util.OptionalDouble;

public class WarehouseObserver implements ArrayObserver {

    private static final Logger LOGGER = LogManager.getLogger(WarehouseObserver.class);

    private final Warehouse warehouse;
    private final ArraySumService sumService;
    private final ArrayAverageService averageService;
    private final ArrayMinMaxService minMaxService;

    public WarehouseObserver(Warehouse warehouse) {
        this.warehouse = warehouse;
        sumService = new ArraySumServiceImpl();
        averageService = new ArrayAverageServiceImpl();
        minMaxService = new ArrayMinMaxServiceImpl();
    }

    @Override
    public void update(DoubleArray array) {
        OptionalDouble sum = sumService.calculateSum(array);
        OptionalDouble average = averageService.calculateAverage(array);
        OptionalDouble min = minMaxService.findMin(array);
        OptionalDouble max = minMaxService.findMax(array);

        ArrayStatistics statistics = new ArrayStatistics(
                sum,
                average,
                min,
                max
        );

        warehouse.save(array.getId(), statistics);

        LOGGER.debug(
                "Warehouse updated for array id={}",
                array.getId()
        );
    }
}