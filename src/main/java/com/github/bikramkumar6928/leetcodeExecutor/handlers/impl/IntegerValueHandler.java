package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;

public class IntegerValueHandler extends ValueHandler {

    @Override
    public Object takeInput(String value) {
        return 5;
    }

    @Override
    public Class<?> getClazz() {
        return Integer.class;
    }
}
