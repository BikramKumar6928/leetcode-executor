package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.beans.UpdatedInputAndParameter;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandlerUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Invoker {
    public static void invoke(Class<?> clazz, String input){
        List<Method> publicMethods = Arrays
                .stream(clazz.getDeclaredMethods())
                .filter(Invoker::isPublicMethod)
                .filter(Invoker::removeMainMethod)
                .collect(Collectors.toList());
        ListMultimap<Method,Object> mapListObject = ArrayListMultimap.create();
        for (Method publicMethod : publicMethods) {
            Class<?>[] parameterClasses = publicMethod.getParameterTypes();
            for (Class<?> parameterClass : parameterClasses) {
                ValueHandler valueHandler = ValueHandlerUtils.getObject(parameterClass);
                UpdatedInputAndParameter updatedInputAndParameter = valueHandler.takeInput(input);
                input = updatedInputAndParameter.getUpdatedInput();
                mapListObject.put(publicMethod, updatedInputAndParameter.getParameter());
            }
        }
        log.trace("The map is {}", mapListObject);
        Object classObject = getClassObject(clazz);
        for (Method method : publicMethods) {
            Object[] parameters = getParametersForMethod(method, mapListObject);
            callMethod(method, classObject, parameters);
        }

    }

    private static boolean removeMainMethod(Method method) {
        return !StringUtils.equals(method.getName(), "main");
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
