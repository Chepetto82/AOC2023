package com.chepetto.util.common;

public class MazePair<Point, Character> {
    Point point;
    char c;

    public MazePair(Point point, char c) {
        this.point = point;
        this.c = c;
    }

    public Point point() {
        return point;
    }

    public char value() {
        return c;
    }
}
