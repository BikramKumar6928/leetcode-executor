package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.abstracts.ArrayValueHandler;

import java.util.Arrays;

public class IntArrayValueHandler extends ArrayValueHandler {

    @Override
    protected Object processMatch(String foundMatch) {
        String[] stringArray = getStringArrayFromMatch(foundMatch);
        return Arrays.stream(stringArray).map(Integer::parseInt).mapToInt(integer -> integer).toArray();
    }

    @Override
    public Class<?> getClazz() {
        return int[].class;
    }
}
