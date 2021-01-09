package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

public class IntArrayValueHandler extends ValueHandler {
    @Override
    public Object takeInput(String value) {
        return new int[]{1, 2, 3};
    }

    @Override
    public Class<?> getClazz() {
        return int[].class;
    }
}
