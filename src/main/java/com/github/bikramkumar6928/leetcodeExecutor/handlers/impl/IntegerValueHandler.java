package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerValueHandler extends ValueHandler {

    private static final Pattern pattern = Pattern.compile("\\d+\\s+");

    @Override
    public Object takeInput(String value) {
        String requiredValue = getRequiredValue(value);
        return Integer.parseInt(requiredValue);
    }

    private String getRequiredValue(String value) {
        Matcher matcher = pattern.matcher(value);
        boolean isMatchFound = matcher.find();
        if(!isMatchFound){
            throw new RuntimeException("Match not for Integer in string " + value);
        }
        String foundMatch = matcher.group();
        return foundMatch.trim();
    }

    @Override
    public Class<?> getClazz() {
        return Integer.class;
    }
}
