package com.codewars.besttravel;

import java.util.*;

public class BestTravel {

    public static void main(String[] args) {

        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        int n = BestTravel.chooseBestSum(163, 3, ts);
        System.out.println(n);

    }

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        System.out.println("Max distance: " + t);
        System.out.println("Number of Cities: " + k);
        System.out.println("Cities: " + ls);
        List<List<Integer>> combinations = createCombinations(ls, k);

        Integer result = combinations.stream()
                .map(combination -> combination.stream().reduce(0, Integer::sum))
                .filter(distance -> distance <= t)
                .map(distance -> new DistanceWithDiff(distance, Math.abs(t - distance)))
                .peek(System.out::println)
                .min(Comparator.comparing(DistanceWithDiff::getDiff))
                .map(DistanceWithDiff::getDistance)
                .orElse(null);

        System.out.println("Found result: " + result);
        return result;
    }

//    private static <T> void subsetsOf(List<T> objects, int k, int index, Set<T> tempSet, List<Set<T>> finalSet) {
//        if (tempSet.size() == k) {
//            finalSet.add(new HashSet<>(tempSet));
//            return;
//        }
//        if (index == objects.size())
//            return;
//
//        T object = objects.get(index);
//        tempSet.add(object);
//        subsetsOf(objects, k, index+1, tempSet, finalSet);
//
//        tempSet.remove(object);
//        subsetsOf(objects, k, index+1, tempSet, finalSet);
//    }

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

        // we don't need to go over elts.size() - missing because then the combination cannot be completed, too few left
        for (int i = index; i <= elts.size() - missing; i++) {
            List<T> newCombination;
            if (i == elts.size() - missing) {
                // optimization: avoid dereferencing the final combination, reuse
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
