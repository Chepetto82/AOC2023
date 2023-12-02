package com.chepetto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlgoUtils {

    public static List<Integer> getDivisorsFor(int n) {
        List<Integer> v = new ArrayList<>();
        int i;
        for (i = 1; i * i < n; i++) {
            if (n % i == 0) {
                v.add(i);
            }
        }
        if (i - (n / i) == 1) {
            i--;
        }
        for (; i >= 1; i--) {
            if (n % i == 0) {
                v.add(n / i);
            }
        }

        return v;
    }

    public static Map<?, ?> getPrimesFor(int n) {

        // Calculating powers of prime factors and
        // storing them in a map mp[].
        return null;
        /*Map<?, ?> mp = new HashMap<>();
        for (int j = 2; j <= Math.sqrt(n); j++) {
            int count = 0;
            while (n % j == 0) {
                n /= j;
                count++;
            }
            if (count != 0)
                mp.put(j, count);
        }

        // If n is a prime number
        if (n != 1)
            mp.put(n, 1);

        return mp;
    }

    // Driver code
    public static void main(String[] args) {
        int n = 10;
        System.out.println(getPrimesFor(n));
    }*/
    }

    public static <T> T[] getPermutation(long permutationNumber, T[] array, long[] factorials) {
        int[] sequence = generateSequence(permutationNumber, array.length - 1, factorials);

        return generatePermutation(array, sequence);
    }

    public static <T> T[] generatePermutation(T[] array, int[] sequence) {
        T[] clone = array.clone();

        for (int i = 0; i < clone.length - 1; i++) {
            swap(clone, i, i + sequence[i]);
        }

        return clone;
    }

    private static int[] generateSequence(long permutationNumber, int size, long[] factorials) {
        int[] sequence = new int[size];

        for (int j = 0; j < sequence.length; j++) {
            long factorial = factorials[sequence.length - j];
            sequence[j] = (int) (permutationNumber / factorial);
            permutationNumber = (int) (permutationNumber % factorial);
        }

        return sequence;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static long[] getFactorials(int length) {
        long[] factorials = new long[length];
        long factor = 1;

        for (int i = 0; i < length; i++) {
            factor *= Math.max(i, 1);
            factorials[i] = factor;
        }

        return factorials;
    }

    public static List<String[]> permutations(String[] array) {
        int n = array.length;

        class Perm {
            private final List<String[]> permutations = new ArrayList<>();

            private void perm(String[] array, int step) {
                if (step == 1) permutations.add(array.clone());
                else for (int i = 0; i < step; i++) {
                    perm(array, step - 1);
                    int j = (step % 2 == 0) ? i : 0;
                    swap(array, step - 1, j);
                }
            }

            private void swap(String[] array, int i, int j) {
                String buffer = array[i];
                array[i] = array[j];
                array[j] = buffer;
            }

        }

        String[] nVector = new String[n];
        System.arraycopy(array, 0, nVector, 0, n);

        Perm perm = new Perm();
        perm.perm(nVector, n);
        return perm.permutations;
    }

    public static boolean[][] copyState(boolean[][] grid) {
        boolean[][] gridCopy = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, gridCopy[i], 0, grid[i].length);
        }

        return gridCopy;
    }

    public static void printArray(int[][] array, char separator) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.format("%2d" + separator, anInt);
            }
            System.out.println();
        }
    }

    public static void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.format("%1d", anInt);
            }
            System.out.println();
        }
    }

    public static void printArray(boolean[][] array) {
        for (boolean[] booleans : array) {
            for (boolean aBoolean : booleans) {
                System.out.format("%s", aBoolean ? "#" : ".");
            }
            System.out.println();
        }
    }
}