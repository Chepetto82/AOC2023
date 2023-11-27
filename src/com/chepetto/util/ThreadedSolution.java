package com.chepetto.util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadedSolution<T, U, R> {

    Callable<T> callable;

    int numThreads;

    ExecutorService executor;

    List<Future<String>> results;

    public ThreadedSolution(Callable<T> callable, int numThreads) {
        this.numThreads = numThreads;
        this.callable = callable;
        executor = Executors.newFixedThreadPool(numThreads);
    }

    public <T> List<Future<T>> getResults() {


        return null;
    }
}
