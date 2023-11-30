package com.chepetto.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HashUtils {
    protected static final int[] KNOT_HASH_APPEND_SEQUENCE = new int[]{17, 31, 73, 47, 23};
    private static final int KNOT_HASH_RUNS = 64;
    static java.security.MessageDigest MD5Instance;

    static {
        try {
            MD5Instance = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private HashUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String knotHashAsHex(String input) {
        return HashUtils.denseHashToHex(HashUtils.denseHash(HashUtils.knotHash(input, 64)));
    }

    public static int[] knotHash(String input, int runs) {
        int[] combinedInput = IntStream.concat(
                Arrays.stream(input.codePoints().toArray()),
                Arrays.stream(KNOT_HASH_APPEND_SEQUENCE)).toArray();

        int[] knotHashNumbersRange = IntStream.rangeClosed(0, 255).toArray();

        int length = knotHashNumbersRange.length;

        int position = 0;
        int skipSize = 0;
        for (int run = 0; run < runs; run++) {
            for (int i = 0; i < combinedInput.length; i++) {

                int subListLength = combinedInput[i];
                int[] tmpArray = new int[subListLength];

                int reversePos = subListLength - 1;
                if (position + subListLength < length) {
                    for (int j = position; j < position + subListLength; j++) {
                        tmpArray[reversePos--] = knotHashNumbersRange[j];
                    }
                    System.arraycopy(tmpArray, 0, knotHashNumbersRange, position, tmpArray.length);
                } else {
                    for (int j = position; j < position + subListLength; j++) {
                        tmpArray[reversePos--] = knotHashNumbersRange[j % length];
                    }

                    int counter = 0;
                    for (int j = position; j < position + subListLength; j++) {
                        knotHashNumbersRange[j % length] = tmpArray[counter++];
                    }
                }

                position = (position + subListLength + skipSize++) % length;
            }
        }
        return knotHashNumbersRange;
    }

    public static int[] knotHash(String input) {
        return knotHash(input, KNOT_HASH_RUNS);
    }

    public static int[] denseHash(int[] numbers) {
        int[] denseHash = new int[16];

        for (int i = 0; i < 16; i++) {
            int result = 0;
            for (int j = i * 16; j < (i + 1) * 16; j++) {
                if (result == 0) {
                    result = numbers[j];
                } else {
                    result ^= numbers[j];
                }
            }
            denseHash[i] = result;
        }

        return denseHash;
    }

    public static String denseHashToHex(int[] denseHash) {
        return Arrays.stream(denseHash)
                .mapToObj(num -> String.format("%02X", num))
                .collect(Collectors.joining())
                .toLowerCase();
    }

    public static String hexToBin(String hex) {
        return hex.replace("0", "0000")
                .replace("1", "0001")
                .replace("2", "0010")
                .replace("3", "0011")
                .replace("4", "0100")
                .replace("5", "0101")
                .replace("6", "0110")
                .replace("7", "0111")
                .replace("8", "1000")
                .replace("9", "1001")
                .replace("A", "1010")
                .replace("B", "1011")
                .replace("C", "1100")
                .replace("D", "1101")
                .replace("E", "1110")
                .replace("F", "1111");
    }

    public static String md5(String input) {
        /*HashFunction md5 = Hashing.md5();
        HashCode hc = md5.newHasher()
                .putString(input, Charsets.UTF_8)
                .hash();
    
        return hc.toString();*/


        byte[] array = MD5Instance.digest(input.getBytes());
        MD5Instance.update(StandardCharsets.UTF_8.encode(input));
        return String.format("%032x", new BigInteger(1, MD5Instance.digest()));
    }
}