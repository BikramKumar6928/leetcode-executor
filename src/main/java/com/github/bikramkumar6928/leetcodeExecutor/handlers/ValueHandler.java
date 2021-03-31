package com.github.bikramkumar6928.leetcodeExecutor.handlers;

import com.github.bikramkumar6928.leetcodeExecutor.beans.UpdatedInputAndParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class ValueHandler {

    protected abstract Pattern getPattern();

    public UpdatedInputAndParameter takeInput(String value) {
        if(StringUtils.isEmpty(value)){
            Object randomGeneratedObject = getRandom();
            log.info("Randomly generated object for class {} is: \n{}", getClazz(), getPrintableObject(randomGeneratedObject));
            return UpdatedInputAndParameter
                    .builder()
                    .updatedInput(value)
                    .parameter(randomGeneratedObject)
                    .build();
        }
        Matcher matcher = getPattern().matcher(value);
        boolean isMatchFound = matcher.find();
        if(!isMatchFound){
            throw new RuntimeException("Match not for Integer in string " + value);
        }
        String foundMatch = matcher.group().trim();
        Object processedMatch = processMatch(foundMatch);
        log.info("Input object for class {} is: \n{}", getClazz(), getPrintableObject(processedMatch));
        return UpdatedInputAndParameter
                .builder()
                .parameter(processedMatch)
                .updatedInput(value.substring(matcher.end()))
                .build();
    }

    protected abstract Object getRandom();

    protected String getPrintableObject(Object object){
        return object.toString();
    }

    protected abstract Object processMatch(String foundMatch);

    protected abstract Class<?> getClazz();
}
