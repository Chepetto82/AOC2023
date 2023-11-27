package com.chepetto.util.common;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }


    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public void move(Movement movement) {
        this.x += movement.x;
        this.y += movement.y;
    }

    public void move(CoordMovement movement) {
        this.x += movement.x;
        this.y += movement.y;
    }

    public Set<Point> getAllNeighbours() {
        return new HashSet<>(getNeighoursMap().values());
    }

    public Map<String, Point> getNeighoursMap() {
        return Map.of("NW", new Point(x - 1, y - 1),
                "N", new Point(x, y - 1),
                "NE", new Point(x + 1, y - 1),
                "W", new Point(x - 1, y),
                "E", new Point(x + 1, y),
                "SW", new Point(x - 1, y + 1),
                "S", new Point(x, y + 1),
                "SE", new Point(x + 1, y + 1));
    }

    public Set<Point> getOrthogonalNeighbours() {
        Map<String, Point> neighbours = getNeighoursMap();
        return Set.of(neighbours.get("N"),
                neighbours.get("E"),
                neighbours.get("S"),
                neighbours.get("W"));
    }

    public int distanceTo(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    @Override
    public String toString() {
        return String.format("P(x,y)=%d,%d", x, y);
    }
}