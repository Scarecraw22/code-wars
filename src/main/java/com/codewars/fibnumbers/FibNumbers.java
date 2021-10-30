package com.codewars.fibnumbers;

import java.util.Arrays;

public class FibNumbers {

    public static void main(String[] args) {

    }

    public static long[] productFib(long prod) {
        if (prod == 0) {
            return new long[]{0, 1, 1};
        } else {
            long num1 = 0;
            long num2 = 1;
            while (num1 * num2 < prod) {
                long sum = num2 + num1;
                num1 = num2;
                num2 = sum;
                if (num2 * num1 == prod) {
                    return new long[]{num1, num2, 1};
                }
            }
            return new long[]{num1, num2, 0};
        }
    }
}
