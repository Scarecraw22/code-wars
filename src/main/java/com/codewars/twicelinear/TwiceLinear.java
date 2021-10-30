package com.codewars.twicelinear;

import java.util.*;

public class TwiceLinear {

    public static void main(String[] args) {
        System.out.println(dblLinear(10));
        System.out.println(dblLinear(20));
        System.out.println(dblLinear(30));
        System.out.println(dblLinear(50));
    }

    public static int dblLinear(int n) {
        if (n == 0) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i = 0;
        while (list.size() < n*2) {
            int prevElem = list.get(i);
            int y = prevElem * 2 + 1;
            int z = prevElem * 3 + 1;
            if (!list.contains(y)) {
                list.add(y);
            }
            if (!list.contains(z)) {
                list.add(z);
            }
            list.sort(Comparator.comparingInt(integer -> integer));
            i++;
        }
        return list.get(n);
    }
}