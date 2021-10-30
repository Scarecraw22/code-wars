package com.codewars.multiplication;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Multiplication {

    public static void main(String[] args) {
        System.out.println(multiTable(2));
    }

    public static String multiTable(int num) {
        return IntStream.rangeClosed(1, 10)
                .boxed()
                .map(n -> String.format("%d * %d = %d", n, num, n*num))
                .collect(Collectors.joining("\n"));
    }
}
