package com.codewars.wordfrequency;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordFrequency {

    public static void main(String[] args) {
        System.out.println(top3("In a village of La Mancha, the name of which I have no desire to call to\n" +
                                        "mind, there lived not long since one of those gentlemen that keep a lance\n" +
                                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for\n" +
                                        "coursing. An olla of rather more beef than mutton, a salad on most\n" +
                                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra\n" +
                                        "on Sundays, made ''' away with three-quarters of his income.\n" +
                                        "lhaeqlm gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y gwt'hf'y"));
    }

    public static List<String> top3(String s) {
        Pattern compiledPattern = Pattern.compile("(\\w|')+");
        Matcher matcher = compiledPattern.matcher(s);
        Map<String, Integer> resultMap = new HashMap<>();
        while (matcher.find()) {
            String matchedString = matcher.group().toLowerCase();
            if (resultMap.containsKey(matchedString)) {
                resultMap.put(matchedString, resultMap.get(matchedString) + 1);
            } else {
                resultMap.put(matchedString, 1);
            }
        }

        return resultMap.entrySet()
                .stream()
                .filter(entrySet -> !entrySet.getKey().replaceAll("'", "").isBlank())
                .sorted(Comparator.comparingInt(t -> ((Map.Entry<String, Integer>)t).getValue()).reversed())
                .map(Map.Entry::getKey)
                .limit(3)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
