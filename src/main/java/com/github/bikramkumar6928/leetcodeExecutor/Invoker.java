package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.beans.UpdatedInputAndParameter;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.handlers.ValueHandlerList;
import com.github.bikramkumar6928.leetcodeExecutor.utils.ValueHandlerUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Invoker {
    public static void invoke(Object classObject, String input){
        Class<?> clazz = classObject.getClass();
        Map<String, Method> publicMethods = getPublicMethodsFromClass(clazz);
        if(publicMethods.size() == 1){
            Method publicMethod = publicMethods.values().iterator().next();
            executeSinglePublicMethod(classObject, input, publicMethod);
        }
        else{
            executeMultiplePublicMethod(classObject, input, publicMethods);
        }
    }

    public static void invoke(Class<?> clazz, String input){
        Object classObject = getClassObject(clazz);
        invoke(classObject, input);
    }

    private static void executeMultiplePublicMethod(Object classObject, String input, Map<String, Method>  publicMethods) {
        if(StringUtils.isEmpty(input)){
            throw new IllegalArgumentException("Input cannot be empty for multiple public methods.");
        }
        UpdatedInputAndParameter methodCallOrderInputAndParameter = getUpdatedInputAndParameter(input, String[].class);
        input = methodCallOrderInputAndParameter.getUpdatedInput();
        String[] methodCallOrder = (String[]) methodCallOrderInputAndParameter.getParameter();
        UpdatedInputAndParameter methodParametersInputAndParameter = getUpdatedInputAndParameter(input, String[].class);
        String[] methodInputs = (String[]) methodParametersInputAndParameter.getParameter();
        String remainingInput = methodParametersInputAndParameter.getUpdatedInput();
        if(StringUtils.isNotEmpty(remainingInput)){
            log.warn("Input should be empty after all parameters. Input: {}", remainingInput);
        }
        if(methodInputs.length != methodCallOrder.length){
            throw new IllegalArgumentException("Number of method calls and parameters sets are not same");
        }
        for (int i = 0; i < methodCallOrder.length; i++) {
            String methodName = methodCallOrder[i];
            String methodInput = methodInputs[i];
            Method method = publicMethods.get(methodName);
//            If method name is class name, it is a constructor. Create an object for this.
            if(StringUtils.equals(methodName,classObject.getClass().getName())){
                List<Object> parameterOrder = getSingleMethodParameters(input, method);
                classObject = getClassObject(classObject.getClass(), parameterOrder.toArray());
                continue;
            }
            executeSinglePublicMethod(classObject, methodInput, method);
        }
    }

    private static void executeSinglePublicMethod(Object classObject, String input,  Method publicMethod) {
        List<Object> parameterOrder = getSingleMethodParameters(input, publicMethod);
        log.trace("The map is {}", parameterOrder);
        Object[] parameters = parameterOrder.toArray();
        Object output = callMethod(publicMethod, classObject, parameters);
        log.info("Output is \n{}", ValueHandlerUtils.getPrintableString(output));
    }

    @NonNull
    private static List<Object> getSingleMethodParameters(String input, Method publicMethod) {
        List<Object> parameterOrder = new ArrayList<>();
        Class<?>[] parameterClasses = publicMethod.getParameterTypes();
        for (Class<?> parameterClass : parameterClasses) {
            UpdatedInputAndParameter updatedInputAndParameter = getUpdatedInputAndParameter(input, parameterClass);
            input = updatedInputAndParameter.getUpdatedInput();
            parameterOrder.add(updatedInputAndParameter.getParameter());
        }
        return parameterOrder;
    }

    @NonNull
    private static UpdatedInputAndParameter getUpdatedInputAndParameter(String input, Class<?> parameterClass) {
        ValueHandler valueHandler = ValueHandlerList.getObject(parameterClass);
        return valueHandler.takeInput(input);
    }

    @NonNull
    private static Map<String, Method> getPublicMethodsFromClass(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!isPublicMethod(method)) {
                continue;
            }
            methodMap.put(method.getName(), method);
        }
        return methodMap;
    }

    private static Object callMethod(Method method, Object classObject, Object[] parameters){
        try {
            method.setAccessible(true);
            return method.invoke(classObject, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to call method" , e);
        }
    }

    @NonNull
    private static Object getClassObject(Class<?> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Unable to instantiate object", e);
        }
    }

    @NonNull
    private static Object getClassObject(Class<?> clazz, Object[] parameters) {
        try {
            return clazz.getConstructor().newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Unable to instantiate object", e);
        }
    }

    private static boolean isPublicMethod(Method method) {
        return method.getModifiers() == Modifier.PUBLIC;
    }
}
