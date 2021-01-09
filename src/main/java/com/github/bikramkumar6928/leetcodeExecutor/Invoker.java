package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandlerUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Invoker {
    public static void invoke(Class<?> clazz, String input){
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        ListMultimap<Method,Object> mapListObject = ArrayListMultimap.create();
        methods.stream()
                .filter(Invoker::isPublicMethod)
                .forEach(method ->{
                    List<Class<?>> parameterClasses = Arrays.asList(method.getParameterTypes());
                    parameterClasses.forEach(parameterClass ->{
                        ValueHandler valueHandler = ValueHandlerUtils.getObject(parameterClass);
                        mapListObject.put(method, valueHandler.takeInput(input));
                    });
                });
        log.trace("The map is {}", mapListObject);
        Object classObject = getClassObject(clazz);
        methods.forEach(method -> {
                    Object[] parameters = getParametersForMethod(method, mapListObject);
                    callMethod(method, classObject, parameters);
                });

    }

    private static void callMethod(Method method, Object classObject, Object[] parameters){
        try {
            method.invoke(classObject, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to call method" , e);
        }
    }

    private static Object[] getParametersForMethod(Method method, ListMultimap<Method, Object> mapListObject) {
        return mapListObject.get(method).toArray();
    }

    @NonNull
    private static Object getClassObject(Class<?> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to instantiate object", e);
        }
    }

    private static boolean isPublicMethod(Method method) {
        return method.getModifiers() == Modifier.PUBLIC;
    }
}
