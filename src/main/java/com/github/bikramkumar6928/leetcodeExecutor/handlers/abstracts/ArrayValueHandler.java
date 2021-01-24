package com.github.bikramkumar6928.leetcodeExecutor.handlers.abstracts;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class ArrayValueHandler extends ValueHandler {

    private static final Pattern pattern = Pattern.compile("\\[(.*?)\\]");

    protected String[] getStringArrayFromMatch(String foundMatch) {
        String matchRemovedBrackets = foundMatch.substring(1, foundMatch.length() - 1);
        return Arrays
                .stream(matchRemovedBrackets.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
