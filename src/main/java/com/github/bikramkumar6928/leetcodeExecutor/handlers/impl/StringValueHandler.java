package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Pattern;

public class StringValueHandler extends ValueHandler {
    private static final Pattern pattern = Pattern.compile("\"(.*?)\"");
    private static final int LENGTH = 10;

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected Object getRandom() {
        return RandomStringUtils.randomAlphanumeric(LENGTH);
    }

    @Override
    protected Object processMatch(String foundMatch) {
        return foundMatch.substring(1,foundMatch.length() - 1);
    }

    @Override
    protected Class<?> getClazz() {
        return String.class;
    }
}
