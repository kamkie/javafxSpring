package net.devops.javafxspring.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReflectionUtil {

    public static List<Method> getGetters(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.getName().startsWith("get") && !method.getName().startsWith("getClass"))
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());
    }

    public static List<Field> getFields(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());
    }

    public static Object invokeMethodSafe(Method method, Object o) {
        Object invokeResult = null;
        try {
            invokeResult = method.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("error", e);
        }
        return invokeResult;
    }

    public static Object getFieldValueSafe(Field field, Object o) {
        Object invokeResult = null;
        try {
            field.setAccessible(true);
            invokeResult = field.get(o);
        } catch (IllegalAccessException e) {
            log.error("error", e);
        }
        return invokeResult;
    }
}
