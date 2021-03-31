package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.abstracts.ArrayValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.utils.ValueHandlerUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class IntArrayValueHandler extends ArrayValueHandler {

    private static final int LENGTH = 10;

    @Override
    protected Object getRandom() {
        return Arrays
                .stream(new Integer[LENGTH])
                .map(integer -> ValueHandlerUtils.generateRandomInteger())
                .mapToInt(integer -> integer)
                .toArray();
    }

    @Override
    protected Object processMatch(String foundMatch) {
        String[] stringArray = getStringArrayFromMatch(foundMatch);
        return Arrays.stream(stringArray).map(Integer::parseInt).mapToInt(integer -> integer).toArray();
    }

    @Override
    protected Class<?> getClazz() {
        return int[].class;
    }

    @Override
    public String getPrintableObject(Object object) {
        assertThat(object, instanceOf(getClazz()));
        int[] objectAsIntArray = (int[]) object;
        return Arrays
                .stream(objectAsIntArray)
                .boxed()
                .collect(Collectors.toList())
                .toString();
    }
}
