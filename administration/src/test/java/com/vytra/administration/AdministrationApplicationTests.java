/*
package com.vytra.administration;

import com.vytra.administration.entity.parametrics.Country;
import com.vytra.administration.entity.parametrics.DocumentType;
import com.vytra.administration.entity.security.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministrationApplicationTests {

    @Test
    public void contextLoads() {
    }


    */
/*	@Test
        public void contextLoads() {
            long time = new Date().getTime();

            String field = "asd@asd.com_"; /// unique fields
            String newValue = field + "_" + time;
            // SAVE FOR DELETE_AT

            /////////////////////////////////////////

            // RESTORE DELETED
            String deletedField = field + "_" + time; // obtain fo db
            String restiredValue = deletedField.replaceAll("_" + time + "$", "");
        }
        *//*

    @Test
    public void testMethod() {
        User user = new User();
        User user2 = new User();

        user.setUsername("maria");
        user2.setUsername("Mario");

        Object obj = (Object) user;

        //

        Class<?> cls = obj.getClass();

        for (Field field : cls.getDeclaredFields()) {
            Class type = field.getType();
            String name = field.getName();
            List<Boolean> isUnique = new ArrayList<>();
            Annotation[] annotations = field.getDeclaredAnnotations();
            Arrays.stream(field.getDeclaredAnnotations()).forEach(x->{
                if(x.annotationType().getTypeName().equalsIgnoreCase("javax.persistence.Column") && x.toString().contains("unique=true")){
                    Method methods = Arrays.stream(obj.getClass().getMethods()).filter(j -> j.getName().equalsIgnoreCase("get" + name.substring(0, 1).toUpperCase() + name.substring(1))).findFirst().get();
                    field.setAccessible(true);
                    String fromName = methods.getName();
                    String toName = fromName.replace("get", "set");
                    try {
                        Method toMetod = obj.getClass().getMethod(toName, methods.getReturnType());
                        Object value = methods.invoke(obj, (Object[]) null);
                        if (value != null) {
                            toMetod.invoke(obj, "pornoo");
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });

            for (int i = 0; i < annotations.length; i++) {
                if (annotations[i].annotationType().getTypeName().equalsIgnoreCase("javax.persistence.Column") && annotations[i].toString().contains("unique=true")) {
                    Method methods = Arrays.stream(obj.getClass().getMethods()).filter(x -> x.getName().equalsIgnoreCase("get" + name.substring(0, 1).toUpperCase() + name.substring(1))).findFirst().get();
                    field.setAccessible(true);
                    String fromName = methods.getName();
                    String toName = fromName.replace("get", "set");
                    try {
                        Method toMetod = obj.getClass().getMethod(toName, methods.getReturnType());
                        Object value = methods.invoke(obj, (Object[]) null);
                        if (value != null) {
                            toMetod.invoke(obj, "pornoo");
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }


                    long time = new Date().getTime();
                    String campo = "asd@asd.com_";
                    String newValue = campo + "_" + time;
                }

            }


        }

    }

    @Test
    public void setField() {
        User user = new User();
        user.setUsername("maria");

        User user2 = new User();
        user2.setUsername("mario");
        Object targetObject = user;
        String fieldName = "username";
        Object fieldValue = user2;


        Field field;
        try {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        Class superClass = targetObject.getClass().getSuperclass();
        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }
        if (field == null) {
            //return false;
        }
        field.setAccessible(true);
        try {
            field.set(targetObject, fieldValue);
            //return true;
        } catch (IllegalAccessException e) {
            //return false;
        }
    }


    @Test
    public void update() {
        DocumentType documentTypeOld = new DocumentType();
        DocumentType documentTypenew = new DocumentType();

        documentTypeOld.setId(1L);
        documentTypeOld.setName("exD");
        documentTypeOld.setDescription("nada");
        Country c = new Country();
        c.setId(2L);
        documentTypeOld.setCountry(c);

        documentTypenew.setId(2L);
        Country c1 = new Country();
        c1.setId(1L);
        documentTypenew.setCountry(c1);


    }


    @Test
    public void merge() {
        DocumentType documentTypeOld = new DocumentType();
        DocumentType documentTypenew = new DocumentType();

        documentTypeOld.setId(1L);
        documentTypeOld.setName("exD");
        documentTypeOld.setDescription("nada");
        Country c = new Country();
        c.setId(2L);
        documentTypeOld.setCountry(c);

        documentTypenew.setId(2L);
        Country c1 = new Country();
        c1.setId(1L);
        documentTypenew.setCountry(c1);

        Object obj = documentTypeOld;
        Object update = documentTypenew;
        if (!obj.getClass().isAssignableFrom(update.getClass())) {
            //return null;
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
                } catch (Exception e) {
                    e.printStackTrace();
                    //return null;
                }
            }
        }
        //return obj;
    }

    @Test
    public void beanProperties() {
        DocumentType documentTypeOld = new DocumentType();
        DocumentType documentTypenew = new DocumentType();

        documentTypeOld.setId(1L);
        documentTypeOld.setName("exD");
        documentTypeOld.setDescription("nada");
        Country c = new Country();
        c.setId(2L);
        documentTypeOld.setCountry(c);

        documentTypenew.setId(2L);
        Country c1 = new Country();
        c1.setId(1L);
        documentTypenew.setCountry(c1);

        User user = new User();
        user.setUsername("esteban");

        Object obj = documentTypeOld;
        Object update = documentTypenew;
        Object bean = user;

        try {
            Map<String, Object> map = new HashMap<>();
            Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class)
                    .getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .forEach(pd -> { // invoke method to get value
                        try {
                            Object value = pd.getReadMethod().invoke(bean);
                            if (value != null) {
                                map.put(pd.getName(), value);
                            }
                        } catch (Exception e) {
                            // add proper error handling here
                        }
                    });
            map.size();
        } catch (IntrospectionException e) {
            // and here, too
            Collections.emptyMap();
        }

    }


}

*/
