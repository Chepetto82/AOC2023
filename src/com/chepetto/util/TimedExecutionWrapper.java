package com.chepetto.util;

import java.time.Duration;
import java.time.Instant;

public class TimedExecutionWrapper {
    static final int PART_1 = 1;
    static final int PART_2 = 2;

    public static Object runSolutionTimed(Solution app, int part) {
        Instant start = Instant.now();

        Object result = null;

        switch (part) {
            case PART_1 -> result = app.part1();
            case PART_2 -> result = app.part2();
            default -> {
            }
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            final Instant end = Instant.now();
            Duration interval = Duration.between(start, end);
            System.out.format("Execution time: %d.%4d%n", interval.getSeconds(), interval.getNano());
        }));

        Instant end = Instant.now();

        return result;
    }

    public static void main(String[] args) {

    }
}
