package com.github.bikramkumar6928.leetcodeExecutor.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

//Only static methods allowed
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueHandlerUtils {
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 300;


    public static int generateRandomInteger(){
        return RandomUtils.nextInt(LOWER_LIMIT, UPPER_LIMIT);
    }
}
