package com.chepetto.day1;

import com.chepetto.util.Solution;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App extends Solution {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("(?=(" + String.join("|", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine") + "|\\d))");

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
        return lines.stream()
                .map(l -> l.replaceAll("[a-zA-Z]", ""))
                .map(l -> l.length() < 2 ? l + l : l)
                .filter(l -> !l.isEmpty())
                .map(l -> l.charAt(0) + l.substring(l.length() - 1))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    @Override
    public Object part2() {
        return lines.stream()
                .peek(System.out::print)
                /*.map(l -> {
                // my original non-regexp solution
                            final String[][] words = new String[][]{
                                    {"one", "1"},
                                    {"two", "2"},
                                    {"three", "3"},
                                    {"four", "4"},
                                    {"five", "5"},
                                    {"six", "6"},
                                    {"seven", "7"},
                                    {"eight", "8"},
                                    {"nine", "9"}
                            };
                            int pos = 0;
                            final int n = l.length();
                            StringBuilder sb = new StringBuilder();
                            while (pos < n) {
                                boolean match = false;
                                for (String[] word : words) {
                                    if (pos + word[0].length() > n) {
                                        continue;
                                    }
                                    if (l.substring(pos, pos + word[0].length()).equals(word[0])) {
                                        sb.append(word[1]);
                                        pos = pos + word[0].length() - 1; // CHawner: so we can captuer oneight for example
                                        match = true;
                                        break;
                                    }
                                }
                                if (!match) {
                                    sb.append(l.charAt(pos));
                                    pos = pos + 1;
                                }
                            }
                            return sb.toString();
                        }
                )*/
                .map(l -> NUMBER_PATTERN.matcher(l).results().map(r -> switch (r.group(1)) {
                            case "one" -> "1";
                            case "two" -> "2";
                            case "three" -> "3";
                            case "four" -> "4";
                            case "five" -> "5";
                            case "six" -> "6";
                            case "seven" -> "7";
                            case "eight" -> "8";
                            case "nine" -> "9";

                            default -> r.group(1);
                        }
                ).collect(Collectors.joining()))
                .map(l -> l.length() < 2 ? l + l : l)
                .peek(l -> System.out.print(" -> " + l))
                .filter(l -> !l.isEmpty())
                .map(l -> l.charAt(0) + l.substring(l.length() - 1))
                .mapToInt(Integer::parseInt)
                .peek(l -> System.out.println(" -> " + l))
                .sum();
    }
}