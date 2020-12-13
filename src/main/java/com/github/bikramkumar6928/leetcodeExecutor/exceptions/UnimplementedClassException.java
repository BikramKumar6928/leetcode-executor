package com.github.bikramkumar6928.leetcodeExecutor.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnimplementedClassException extends RuntimeException{
    public UnimplementedClassException(Class<?> clazz){
        super(String.format("Class %s is not implemented", clazz.getName()));
    }
}
