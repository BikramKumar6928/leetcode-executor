package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandlerUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Invoker {
    public static void invoke(Class<?> clazz, String input){
        StringBuilder stringBuilder = new StringBuilder(input);
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        Multimap<Method,Object> mapListObject = ArrayListMultimap.create();
        methods.stream()
                .filter(Invoker::isPublicMethod)
                .forEach(method ->{
                    List<Class<?>> parameterClasses = Arrays.asList(method.getParameterTypes());
                    parameterClasses.forEach(parameterClass ->{
                        ValueHandler valueHandler = ValueHandlerUtils.getObject(parameterClass);
                        mapListObject.put(method, valueHandler.takeInput(stringBuilder));
                    });
                });
//        TODO: Add implementation of calling object
        System.out.println(mapListObject);
    }

    private static boolean isPublicMethod(Method method) {
        return method.getModifiers() == Modifier.PUBLIC;
    }
}
