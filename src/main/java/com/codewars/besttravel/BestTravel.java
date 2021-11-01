package com.codewars.besttravel;

import java.util.*;

/**
 * Source: https://www.codewars.com/kata/55e7280b40e1c4a06d0000aa
 */
public class BestTravel {

    public static void main(String[] args) {

        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        int n = BestTravel.chooseBestSum(163, 3, ts);
        System.out.println(n);

    }

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        List<List<Integer>> combinations = createCombinations(ls, k);

        return combinations.stream()
                .map(combination -> combination.stream().reduce(0, Integer::sum))
                .filter(distance -> distance <= t)
                .map(distance -> new DistanceWithDiff(distance, Math.abs(t - distance)))
                .peek(System.out::println)
                .min(Comparator.comparing(DistanceWithDiff::getDiff))
                .map(DistanceWithDiff::getDistance)
                .orElse(null);
    }

    public static <T> List<List<T>> createCombinations(List<T> elts, int combinationSize) {
        var fullCombinations = new ArrayList<List<T>>();
        createCombinations(elts, fullCombinations, new ArrayList<T>(), 0, combinationSize);
        return fullCombinations;
    }

    private static <T> void createCombinations(List<T> elts, List<List<T>> fullCombinations, List<T> combination,
                                               int index, int missing) {
        if (missing == 0) {
            fullCombinations.add(combination);
            return;
        }

        for (int i = index; i <= elts.size() - missing; i++) {
            List<T> newCombination;
            if (i == elts.size() - missing) {
                newCombination = combination;
            } else {
                newCombination = new ArrayList<T>(combination);
            }
            newCombination.add(elts.get(i));
            createCombinations(elts, fullCombinations, newCombination, i + 1, missing - 1);
        }
    }

    private static final class DistanceWithDiff {
        private final Integer distance;
        private final Integer diff;

        public DistanceWithDiff(final Integer distance, final Integer diff) {
            this.distance = distance;
            this.diff = diff;
        }

        public Integer getDistance() {
            return distance;
        }

        public Integer getDiff() {
            return diff;
        }

        @Override
        public String toString() {
            return "DistanceWithDiff{" +
                    "distance=" + distance +
                    ", diff=" + diff +
                    '}';
        }
    }
}
