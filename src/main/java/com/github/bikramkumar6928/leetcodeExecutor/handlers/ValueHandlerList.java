package com.github.bikramkumar6928.leetcodeExecutor.handlers;

import com.github.bikramkumar6928.leetcodeExecutor.exceptions.UnimplementedClassException;
import lombok.NonNull;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ValueHandlerList {
    private static List<ValueHandler> valueHandlers;

    @NonNull
    public static ValueHandler getObject(Class<?> clazz){
        List<ValueHandler> valueHandlerList = getValueHandlerList();
        return valueHandlerList
                .stream()
                .filter(valueHandler -> valueHandler.getClazz().equals(clazz))
                .findAny().orElseThrow(() -> new UnimplementedClassException(clazz));
    }

    @NonNull
    private static List<ValueHandler> getValueHandlerList() {
        if(Objects.isNull(valueHandlers))
            valueHandlers = calculateValueHandlers();
        return valueHandlers;
    }

    @NonNull
    private static List<ValueHandler> calculateValueHandlers() {
        Reflections reflections = new Reflections("com.github.bikramkumar6928.leetcodeExecutor.handlers");
        Set<Class<? extends ValueHandler>> valueHandlerSet = reflections.getSubTypesOf(ValueHandler.class);
        return valueHandlerSet
                .stream()
                .map(ValueHandlerList::getObjectFromMethod)
//                Remove if there were errors while creating the object
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static ValueHandler getObjectFromMethod(Class<? extends ValueHandler> valueHandlerClazz) {
        if(Modifier.isAbstract(valueHandlerClazz.getModifiers())){
            return null;
        }
        ValueHandler valueHandler = null;
        try {
            valueHandler = valueHandlerClazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        return valueHandler;
    }
}
