package com.github.bikramkumar6928.leetcodeExecutor.utils;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandlerList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;

//Only static methods allowed
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueHandlerUtils {
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 300;


    public static int generateRandomInteger(){
        return RandomUtils.nextInt(LOWER_LIMIT, UPPER_LIMIT);
    }

    public static String getPrintableString(Object output) {
        if(Objects.isNull(output)){
            return null;
        }
        Class<?> clazz = output.getClass();
        try {
            return ValueHandlerList.getObject(clazz).getPrintableObject(output);
        }
        catch (Exception e){
            return "[Non-standard object]";
        }
    }
}
