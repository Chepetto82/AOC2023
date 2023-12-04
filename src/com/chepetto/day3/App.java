package com.chepetto.day3;

import com.chepetto.util.Solution;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Tuple<R, T, U> {
    R first;
    T second;
    U third;

    public Tuple(R first, T second, U third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public R getFirst() {
        return first;
    }

    public void setFirst(R first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public U getThird() {
        return third;
    }

    public void setThird(U third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}

public class App extends Solution {

    Predicate<Character> nearSymbol = c -> /*c == '@' || c == '#' || c == '$'
            || c == '%' || c == '&' || c == '*'
            || c == '+' || c == '=' || c == '-'
            || c == '/';*/
            !Character.isDigit(c) && !Character.isAlphabetic(c) && c != '.';

    Predicate<Character> testFunctionPart2 = c -> c == '*';

    public App() {
        this(false);
    }

    public App(boolean useSample) {
        super(useSample);
    }

    static int mhd(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - y1) + Math.abs(x2 - y2);
    }

    static boolean isConnected(int x, int y, List<String> lines) {
        for (int row = y - 1; row <= y + 1; row++) {
            for (int col = x - 1; col <= x + 1; col++) {
                if (row < 0 || row >= lines.size() || col < 0 || col >= lines.get(row).length()) {
                    continue;
                }
                if (mhd(x, y, col, row) == 1 && Character.isDigit(lines.get(row).charAt(col))) {
                    System.out.println("Found");
                }
            }
        }
        return false;
    }

    static boolean isNumberNearSymbol(String number, int x, int y, List<String> lines, Predicate<Character> filter, Tuple<Integer, Integer, Character> result) {
        System.out.print("Checking " + number + " ");
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j < x + number.length() + 1; j++) {
                //System.out.format("[%d,%d]", j, i);
                if (i < 0 || i >= lines.size() || j < 0 || j >= lines.get(i).length()) {
                    continue;
                } else {
                    if (filter.test(lines.get(i).charAt(j))) {
                        System.out.println(lines.get(i).charAt(j) + " symbol found");
                        result.first = j;
                        result.second = i;
                        result.third = lines.get(i).charAt(j);
                        return true;
                    } else {
                        System.out.print(lines.get(i).charAt(j) + " ");
                    }
                }
            }
            //System.out.println();
        }
        System.out.println(" symbol not found");
        return false;
    }

    public static void main(String[] args) {
        App app = new App(true);
        //System.out.println(app.part1());
        System.out.println(app.part2());
    }

    // 568651 too high
    @Override
    public Object part1() {
        Set<Character> collect = lines.stream()
                .flatMapToInt(String::codePoints)
                .mapToObj(c -> (char) c)
                .filter(c -> !Character.isDigit(c) && c != '.')
                .collect(Collectors.toSet());

        System.out.println(collect);
        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            int pos = 0;
            String line = lines.get(i);
            int numStart = -1;
            int numEnd = -1;
            while (pos < line.length()) {
                String number = "";
                if (Character.isDigit(line.charAt(pos))) {
                    numStart = pos;
                    int tmpPos = pos + 1;
                    numEnd = tmpPos;
                    while (tmpPos < line.length() && Character.isDigit(line.charAt(tmpPos))) {
                        numEnd = ++tmpPos;
                    }
                    number = line.substring(numStart, numEnd);
                    pos = numEnd + 1;
                    System.out.format("Line %d from %d to %d", i, numStart, numEnd);
                    Tuple<Integer, Integer, Character> result = new Tuple<>(-1, -1, '.');
                    if (isNumberNearSymbol(number, numStart, i, lines, nearSymbol, result)) {
                        total += Integer.parseInt(number);
                    }
                } else {
                    pos++;
                }
            }
        }
        return total;
    }

    @Override
    public Object part2() {
        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            int pos = 0;
            String line = lines.get(i);
            int numStart = -1;
            int numEnd = -1;
            while (pos < line.length()) {
                String number = "";
                if (Character.isDigit(line.charAt(pos))) {
                    numStart = pos;
                    int tmpPos = pos + 1;
                    numEnd = tmpPos;
                    while (tmpPos < line.length() && Character.isDigit(line.charAt(tmpPos))) {
                        numEnd = ++tmpPos;
                    }
                    number = line.substring(numStart, numEnd);
                    pos = numEnd + 1;
                    System.out.format("Line %d from %d to %d", i, numStart, numEnd);
                    Tuple<Integer, Integer, Character> result = new Tuple<>(-1, -1, '.');
                    if (isNumberNearSymbol(number, numStart, i, lines, testFunctionPart2, result)) {
                        if (result.third == '*') {
                            if (isConnected(result.first, result.second, lines)) {

                            }
                        }

                    }
                } else {
                    pos++;
                }
            }
        }
        return total;
    }
}