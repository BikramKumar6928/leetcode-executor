package com.github.bikramkumar6928.leetcodeExecutor.sampleClasses;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public void testMethod(Integer integer, int[] intArray){
        System.out.println("Sample Class with integer and int double array parameters");
        List<Integer> list = new ArrayList<>();
        for (int i : intArray) {
            list.add(i);
        }
        System.out.println("Passed integer " + integer + " and array " + list.toString());

    }
}
