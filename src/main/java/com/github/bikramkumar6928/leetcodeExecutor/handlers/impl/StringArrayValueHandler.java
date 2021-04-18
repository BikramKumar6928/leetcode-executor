package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.abstracts.ArrayValueHandler;

public class StringArrayValueHandler extends ArrayValueHandler {
    private final StringValueHandler stringValueHandler = new StringValueHandler();

    @Override
    protected Object getRandom() {
        return stringValueHandler.getRandom();
    }

    @Override
    protected Object processMatch(String foundMatch) {
        return stringValueHandler.processMatch(foundMatch);
    }

    @Override
    protected Class<?> getClazz() {
        return String[].class;
    }
}
