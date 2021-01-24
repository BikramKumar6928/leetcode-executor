package com.github.bikramkumar6928.leetcodeExecutor.handlers;

import com.github.bikramkumar6928.leetcodeExecutor.beans.UpdatedInputAndParameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValueHandler {

    public abstract Pattern getPattern();

    public UpdatedInputAndParameter takeInput(String value) {
        Matcher matcher = getPattern().matcher(value);
        boolean isMatchFound = matcher.find();
        if(!isMatchFound){
            throw new RuntimeException("Match not for Integer in string " + value);
        }
        String foundMatch = matcher.group().trim();
        Object processedMatch = processMatch(foundMatch);
        return UpdatedInputAndParameter
                .builder()
                .parameter(processedMatch)
                .updatedInput(value.substring(matcher.end()))
                .build();
    }

    protected abstract Object processMatch(String foundMatch);

    public abstract Class<?> getClazz();
}
