package com.chepetto.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {
    static final String URL = "https://adventofcode.com/%4d/day/%d/input";

    public static List<List<String>> parseFileIntoGroup(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;

            List<List<String>> groupedLists = new ArrayList<>();

            List<String> subList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if ("".equals(line)) {
                    groupedLists.add(subList);
                    subList = new ArrayList<>();
                } else {
                    subList.add(line);
                }
            }
            groupedLists.add(subList);
            return groupedLists;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    private FileUtil() {
        throw new UnsupportedOperationException();
    }

    public static List<String> readLinesFromFile(String fileName) {
        System.out.println("Reading from " + fileName);
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}