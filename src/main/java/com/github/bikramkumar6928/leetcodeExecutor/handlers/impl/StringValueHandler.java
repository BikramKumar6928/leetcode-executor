package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

import java.util.regex.Pattern;

public class StringValueHandler extends ValueHandler {
    private static final Pattern pattern = Pattern.compile("\"(.*?)\"");

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    protected Object processMatch(String foundMatch) {
        return foundMatch.substring(1,foundMatch.length() - 1);
    }

    @Override
    public Class<?> getClazz() {
        return String.class;
    }
}
