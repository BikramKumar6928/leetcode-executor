package com.github.bikramkumar6928.leetcodeExecutor.handlers;

public abstract class ValueHandler {

    public abstract Pattern getPattern();

    public abstract Object takeInput(String value);

    public abstract Class<?> getClazz();
}
