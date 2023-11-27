package com.chepetto.util.common;

public enum CoordMovement {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int x;
    public final int y;

    CoordMovement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static CoordMovement lookup(char c) {
        return switch (c) {
            case '^' -> UP;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            case 'v' -> DOWN;
            default -> throw new IllegalStateException("unknown direction");
        };
    }
}
