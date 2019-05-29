package com.vytra.services;

import com.vytra.services.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

	@Test
	public void contextLoads() {
	/*	Class<?> c = Company.class;
		Table table = c.getAnnotation(Table.class);
		String tableName = table.name();
		for(Field field : Company.class.getDeclaredFields()){
			Class type = field.getType();
			String name = field.getName();
			Annotation[] annotations = field.getDeclaredAnnotations();
			annotations[0].annotationType().getTypeName();
		}*/
	}

}
