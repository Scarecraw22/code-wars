package com.codewars.observedpin;

import java.util.*;

/**
 * Source: https://www.codewars.com/kata/5263c6999e0f40dee200059d
 */
public class ObservedPin {

    private static final Map<Character, Set<Character>> PIN_MAP = Map.of(
            '1', Set.of('1', '2', '4'),
            '2', Set.of('1', '2', '3', '5'),
            '3', Set.of('2', '3', '6'),
            '4', Set.of('1', '4', '5', '7'),
            '5', Set.of('2', '4', '5', '6', '8'),
            '6', Set.of('3', '5', '6', '9'),
            '7', Set.of('4', '7', '8'),
            '8', Set.of('0', '5', '7', '8', '9'),
            '9', Set.of('6', '8', '9'),
            '0', Set.of('0', '8')
    );

    public static void main(String[] args) {

        long currentTime = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            getPINs("1122");
        }
        System.out.println(System.currentTimeMillis() - currentTime);
    }

    public static List<String> getPINs(String observed) {
        if (observed == null || observed.isEmpty()) {
            return Collections.emptyList();
        }
        final List<String> combinations = new ArrayList<>();
        PIN_MAP.get(observed.charAt(0)).forEach(value -> combinations.add(String.valueOf(value)));

        String restOfPin = observed.substring(1);
        for (char pinCharacter : restOfPin.toCharArray()) {
            List<String> updatedCombinations = new ArrayList<>();
            combinations.forEach(actualCombination -> PIN_MAP.get(pinCharacter).forEach(character -> {
                updatedCombinations.add(actualCombination + character);
            }));
            combinations.clear();
            combinations.addAll(updatedCombinations);
        }

        return combinations;
    }
}
