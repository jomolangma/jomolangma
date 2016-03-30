package com.jomolangma.app.spring.anno;

import java.lang.reflect.Method;
import com.jomolangma.app.spring.anno.ForumService;
import com.jomolangma.app.spring.anno.NeedTest;

public class TestAnnotation {

	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		Class clazz = ForumService.class;
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			NeedTest nt = method.getAnnotation(NeedTest.class);
			if (nt != null) {
				if (nt.value()) {
					System.out.println(method.getName() + "true");
				} else {
					System.out.println(method.getName() + "false");
				}
			}
		}
	}
}
