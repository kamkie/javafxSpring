package net.devops.javafxspring.common.util
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

@Slf4j
@CompileStatic
public class ReflectionUtil {

    static List<Method> getGetters(Class clazz) {
        return clazz.methods.findAll { it.getName().startsWith("get") && !it.getName().startsWith("getClass") }.toList()
    }

    static List<Field> getFields(Class clazz) {
        return clazz.declaredFields.each { Field field -> field.setAccessible(true) }.toList()
    }

    static Object invokeMethodSafe(Method method, Object o) {
        Object invokeResult = null;
        try {
            invokeResult = method.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("error", e);
        }
        return invokeResult;
    }

    static Object getFieldValueSafe(Field field, Object o) {
        Object invokeResult = null;
        try {
            invokeResult = field.get(o);
        } catch (IllegalAccessException e) {
            log.error("error", e);
        }
        return invokeResult;
    }
}
