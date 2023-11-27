package com.chepetto.util;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Combinatorics {
    public static <T> List<List<T>> nOverK(List<T> set, int k) {
        if (k > set.size()) {
            k = set.size();
        }
        List<List<T>> result = Lists.newArrayList();
        List<T> subset = Lists.newArrayListWithCapacity(k);
        for (int i = 0; i < k; i++) {
            subset.add(null);
        }
        return processLargerSubsets(result, set, subset, 0, 0);
    }

    public static <T> List<List<T>> processLargerSubsets(List<List<T>> result, List<T> set, List<T> subset, int subsetSize,
                                                         int nextIndex) {
        if (subsetSize == subset.size()) {
            result.add(ImmutableList.copyOf(subset));
        } else {
            for (int j = nextIndex; j < set.size(); j++) {
                subset.set(subsetSize, set.get(j));
                processLargerSubsets(result, set, subset, subsetSize + 1, j + 1);
            }
        }
        return result;
    }

    public static <T> List<List<T>> permutations(List<T> list, int size) {
        //Collection<List<T>> all = Lists.newArrayList();
        List<List<T>> all = Lists.newArrayList();
        if (list.size() < size) {
            size = list.size();
        }
        if (list.size() == size) {
            all.addAll(Collections2.permutations(list));
        } else {
            for (List<T> p : nOverK(list, size)) {
                all.addAll(Collections2.permutations(p));
            }
        }
        return all;
    }

    public static List<List<String>> allPossibleCombinations(List<String> elements) {
        List<List<String>> combinations = new ArrayList<>();

        for (String element : elements) {
            List<String> currentCombination = new ArrayList<>();
            combinationsHelper(element, elements, currentCombination, combinations, 1);
        }

        return combinations;
    }

    private static void combinationsHelper(String currentElement, List<String> allElements, List<String> currentCombination, List<List<String>> combinations, int depth) {
        currentCombination.add(depth - 1, currentElement);

        int maxDepth = allElements.size();
        if (depth >= maxDepth) {
            combinations.add(List.copyOf(currentCombination));
            return;
        }

        int currentDepth = depth + 1;
        for (String element : allElements) {
            combinationsHelper(element, allElements, currentCombination, combinations, currentDepth);
        }
    }
}
