package com.chepetto.day3;

import com.chepetto.util.Solution;
import com.chepetto.util.common.Point;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Gear {
    Point location;
    int numberOne = -4711;
    int numberTwo = -4711;

    List<Integer> numbers = new ArrayList<>();

    public Gear(int x, int y) {
        this.location = Point.of(x, y);
    }

    public void add(int i) {
        numbers.add(i);
        /*if (numberOne == -4711) {
            numberOne = i;
        } else {
            numberTwo = i;
        }*/
    }

    public int ratio() {
        return numbers.stream().reduce(1, (a, b) -> a * b);
    }
}

public class App extends Solution {

    Predicate<Character> predicatePart1 = c -> !Character.isDigit(c) && !Character.isAlphabetic(c) && c != '.';

    Predicate<Character> predicatePart2 = c -> c == '*';

    public App() {
        this(false);
    }

    public App(boolean useSample) {
        super(useSample);
    }

    static String key(int x, int y) {
        return String.format("%d, %d", x, y);
    }

    static boolean isNumberNearSymbol(String number, int x, int y, List<String> lines, Predicate<Character> filter, Map<String, Gear> result) {
        System.out.print("Checking " + number + " ");
        for (int row = y - 1; row <= y + 1; row++) {
            for (int col = x - 1; col < x + number.length() + 1; col++) {
                //System.out.format("[%d,%d]", j, i);
                if (row < 0 || row >= lines.size() || col < 0 || col >= lines.get(row).length()) {
                    continue;
                } else {
                    if (filter.test(lines.get(row).charAt(col))) {
                        System.out.println(lines.get(row).charAt(col) + " symbol found");

                        Gear gear = result.getOrDefault(key(col, row), new Gear(col, row));

                        gear.add(Integer.parseInt(number));
                        result.put(key(col, row), gear);

                        return true;
                    } else {
                        System.out.print(lines.get(row).charAt(col) + " ");
                    }
                }
            }
            //System.out.println();
        }
        System.out.println(" symbol not found");
        return false;
    }

    public static void main(String[] args) {
        App app = new App(false);
        System.out.println(app.part1());
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
        Map<String, Gear> gearMap = new HashMap<>();

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
                    if (isNumberNearSymbol(number, numStart, i, lines, predicatePart1, gearMap)) {
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

        Map<String, Gear> gearMap = new HashMap<>();
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

                    if (isNumberNearSymbol(number, numStart, i, lines, predicatePart2, gearMap)) {

                    }
                } else {
                    pos++;
                }
            }
        }
        return gearMap.values().stream()
                .filter(g -> g.numbers.size() == 2)
                .mapToInt(Gear::ratio)
                .sum();
    }
}