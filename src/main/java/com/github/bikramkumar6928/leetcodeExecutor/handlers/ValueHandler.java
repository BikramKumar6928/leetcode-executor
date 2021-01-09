package com.github.bikramkumar6928.leetcodeExecutor.handlers;

public abstract class ValueHandler {

    public static final String pattern = " \\n";

    public abstract Object takeInput(String value);

    public abstract Class<?> getClazz();
}
