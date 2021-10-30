package com.codewars.josephus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JosephusPermutation {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        for (int i = 0; i< 10000000; i++) {
            josephusPermutation(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), 3);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {

        List<T> result = new LinkedList<>();
        int lastIndex = 0;
        while (!items.isEmpty()) {
            int indexToRemove = (lastIndex + k - 1);
            if (indexToRemove > items.size() - 1) {
                indexToRemove = indexToRemove % items.size();
            }
            lastIndex = indexToRemove;
            result.add(items.remove(indexToRemove));
        }
        return result;
    }
}
