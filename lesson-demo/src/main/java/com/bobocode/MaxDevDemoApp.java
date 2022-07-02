package com.bobocode;

import com.bobocode.Partner.Seniority;

public class MaxDevDemoApp {
    public static void main(String[] args) {
        var dev1 = new Developer("Scala Developer", Seniority.JUNIOR);
        var dev2 = new Developer("Java Developer", Seniority.SENIOR);
        // this is possible due to Comparable<? super T>
        Developer maxDev = max(dev1, dev2);
        System.out.println(maxDev);
    }

    public static <T extends Comparable<? super T>> T max(T o1, T o2) {
        return o1.compareTo(o2) > 0 ? o1 : o2;
    }
}
