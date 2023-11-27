package com.chepetto.util;

import java.util.Arrays;

public class ArrayUtils {
    public static final int FLIP_VERTICALLY = 1;
    public static final int FLIP_HORIZONTALLY = 2;

    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean[][] copyState(boolean[][] array) {
        boolean[][] copyGrid = new boolean[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            copyGrid[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return copyGrid;
    }

    public static int[][] copyState(int[][] array) {
        int[][] copyGrid = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            copyGrid[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return copyGrid;
    }

    public static String[][] copyState(String[][] array) {
        String[][] copyGrid = new String[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            copyGrid[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return copyGrid;
    }

    public static char[][] rotateArray(char[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        char[][] ret = new char[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M - 1 - r] = grid[r][c];
            }
        }
        return ret;
    }

    public static char[][] flipArray(char[][] grid, int orientation) {
        final int M = grid.length;
        final int N = grid[0].length;
        char[][] ret = new char[M][N];

        if (orientation == FLIP_HORIZONTALLY) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    ret[r][N - 1 - c] = grid[r][c];
                }
            }
        } else if (orientation == FLIP_VERTICALLY) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    ret[M - 1 - r][c] = grid[r][c];
                }
            }
        }
        return ret;
    }

    public static void printGrid(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length ; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }
}