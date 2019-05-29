package com.vytra.administration.Util;

import com.vytra.administration.entity.BaseEntity;
import com.vytra.administration.entity.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);


    /**
     * @param obj
     * @param update
     * @return
     */
    public static Object merge(Object obj, Object update) {
        if (!obj.getClass().isAssignableFrom(update.getClass())) {
            return null;
        }

        Method[] methods = obj.getClass().getMethods();

        for (Method fromMethod : methods) {
            if (fromMethod.getDeclaringClass().equals(obj.getClass())
                    && fromMethod.getName().startsWith("get")) {

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                    Object value = fromMethod.invoke(update, (Object[]) null);
                    if (value != null) {
                        toMetod.invoke(obj, value);
                    }
                } catch (NoSuchMethodException e) {
                    LOG.info("no posee metodo set o get");
                    //NO tiene metodo set o get
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return obj;
    }


    public static Object logicalDelete(Object obj) {
        Class<?> cls = obj.getClass();
        for (Field field : cls.getDeclaredFields()) {
            Class type = field.getType();
            String name = field.getName();
            List<Boolean> isUnique = new ArrayList<>();
            Annotation[] annotations = field.getDeclaredAnnotations();
            Arrays.stream(field.getDeclaredAnnotations()).forEach(x -> {
                if (x.annotationType().getTypeName().equalsIgnoreCase("javax.persistence.Column") && x.toString().contains("unique=true")) {
                    Method methods = Arrays.stream(obj.getClass().getMethods()).filter(j -> j.getName().equalsIgnoreCase("get" + name.substring(0, 1).toUpperCase() + name.substring(1))).findFirst().get();
                    field.setAccessible(true);
                    String fromName = methods.getName();
                    String toName = fromName.replace("get", "set");
                    try {
                        Method toMetod = obj.getClass().getMethod(toName, methods.getReturnType());
                        Object value = methods.invoke(obj, (Object[]) null);
                        if (value != null) {
                            toMetod.invoke(obj, changeUniqueString(value.toString()));
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return obj;
    }


    public static String changeUniqueString(String value){
        long time = new Date().getTime();
        String newValue = value + "_" + time;
        return newValue;
    }

}
