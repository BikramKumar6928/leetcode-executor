package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

import java.util.regex.Pattern;

public class IntegerValueHandler extends ValueHandler {

    private static final Pattern pattern = Pattern.compile("\\d+\\s*");

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected Object processMatch(String foundMatch) {
        return Integer.parseInt(foundMatch);
    }

    @Override
    protected Class<?> getClazz() {
        return Integer.class;
    }
}
