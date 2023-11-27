package com.chepetto.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PartitioningCollector<T> implements Collector<T, List<List<T>>, List<List<T>>> {

    private final int batchSize;
    private final List<T> batch;

    public PartitioningCollector(int batchSize) {
        this.batchSize = batchSize;
        this.batch = new ArrayList<>(batchSize);
    }

    @Override
    public Supplier<List<List<T>>> supplier() {
        return LinkedList::new;
    }

    @Override
    public BiConsumer<List<List<T>>, T> accumulator() {
        return (total, element) -> {
            batch.add(element);
            if (batch.size() >= batchSize) {
                total.add(new ArrayList<>(batch));
                batch.clear();
            }
        };
    }

    @Override
    public BinaryOperator<List<List<T>>> combiner() {
        return (left, right) -> {
            List<List<T>> result = new ArrayList<>();
            result.addAll(left);
            result.addAll(left);
            return result;
        };
    }

    @Override
    public Function<List<List<T>>, List<List<T>>> finisher() {
        return result -> {
            if (!batch.isEmpty()) {
                result.add(new ArrayList<>(batch));
                batch.clear();
            }
            return result;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
