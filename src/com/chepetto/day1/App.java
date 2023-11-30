package com.chepetto.day1;

import com.chepetto.util.Solution;

public class App extends Solution {
    public App() {
        this(false);
    }

    public App(boolean useSample) {
        super(useSample);
    }

    public static void main(String[] args) {
        App app = new App(false);
        System.out.println(app.part1());
        System.out.println(app.part2());
    }

    @Override
    public Object part1() {
        return lines.stream().map(l -> l.replace("n", "o")).count();
    }

    @Override
    public Object part2() {
        return null;
    }
}