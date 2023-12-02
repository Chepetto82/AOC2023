package com.chepetto.day2;

import com.chepetto.util.Solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App extends Solution {

    public static final Pattern RESULT_PATTERN = Pattern.compile("(\\d+) (red|green|blue)");

    public App() {
        this(false);
    }

    public App(boolean useSample) {
        super(useSample);
    }

    public static void main(String[] args) {
        App app = new App(false);
        System.out.println(app.part1_stream());
        System.out.println(app.part2_stream());
    }

    @Override
    public Object part1() {
        int score = 0;

        final Map<String, Integer> limits = Map.of("red", 12, "green", 13, "blue", 14);

        for (String line : lines) {
            Map<String, Integer> cubeCount = new HashMap<>();
            String[] tmp = line.split(": ");
            int gameId = Integer.parseInt(tmp[0].replace("Game ", ""));
            String[] sets = tmp[1].split("; ");
            for (String set : sets) {
                String[] cubes = set.split(", ");
                for (String cube : cubes) {
                    String[] results = cube.split(" ");
                    cubeCount.compute(results[1], (key, val) -> (val == null) ? Integer.parseInt(results[0]) : Math.max(val, Integer.parseInt(results[0])));
                }
            }
            boolean valid = true;
            for (Map.Entry<String, Integer> limit : limits.entrySet()) {
                if (cubeCount.getOrDefault(limit.getKey(), 0) > limit.getValue()) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                System.out.println(" Valid game : " + gameId);
                score += gameId;
            }
        }

        return score;
    }

    @Override
    public Object part2() {
        long score = 0L;

        final Map<String, Integer> limits = Map.of("red", 12, "green", 13, "blue", 14);

        for (String line : lines) {
            Map<String, Integer> cubeCount = new HashMap<>();
            String[] tmp = line.split(": ");
            String[] sets = tmp[1].split("; ");
            for (String set : sets) {
                String[] cubes = set.split(", ");
                for (String cube : cubes) {
                    String[] results = cube.split(" ");
                    cubeCount.compute(results[1], (key, val) -> (val == null)
                            ? Integer.parseInt(results[0]) : Math.max(val, Integer.parseInt(results[0])));
                }
            }
            long power = 1L;
            for (Map.Entry<String, Integer> limit : limits.entrySet()) {
                power *= cubeCount.getOrDefault(limit.getKey(), 0);
            }
            score += power;
        }

        return score;
    }

    public Object part1_stream() {
        final Map<String, Integer> limits = Map.of("red", 12, "green", 13, "blue", 14);

        return lines.stream().mapToInt(l -> {
            Map<String, Integer> collected = RESULT_PATTERN.matcher(l).results()
                    .map(r -> r.group().split(" "))
                    .collect(Collectors.toMap(s -> s[1], v -> Integer.parseInt(v[0]), Math::max));

            boolean valid = true;
            for (Map.Entry<String, Integer> limit : limits.entrySet()) {
                if (collected.getOrDefault(limit.getKey(), 0) > limit.getValue()) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return Integer.parseInt(l.replace("Game ", "").split(": ")[0]);
            } else {
                return 0;
            }
        }).sum();
    }

    public Object part2_stream() {
        return lines.stream()
                .mapToInt(l -> RESULT_PATTERN.matcher(l).results()
                        .map(r -> r.group().split(" "))
                        .collect(Collectors.toMap(s -> s[1], v -> Integer.parseInt(v[0]), Math::max))
                        .values().stream()
                        .reduce(1, (partialResult, value) -> partialResult * value)
                ).sum();
    }
}