package com.chepetto.util;

import java.nio.file.Path;
import java.util.List;

public abstract class Solution {
    protected static final String BASE_PATH = Path.of("").toAbsolutePath() + "\\src\\com\\chepetto\\";
    protected List<String> lines;
    boolean sample;
    String filename;

    protected Solution(boolean useSampleData) {
        sample = useSampleData;
        String day = this.getClass().getPackage().getName();
        day = day.substring(day.lastIndexOf(".") + 1);

        filename = BASE_PATH + day + "\\" + day + ((sample) ? "_sample" : "") + ".txt";
        lines = FileUtil.readLinesFromFile(getFilename());
    }

    protected Solution() {
        this(false);
    }

    public abstract Object part1();

    public abstract Object part2();

    public final String getFilename() {
        return filename;
    }
}
