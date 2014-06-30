package com.jomolangma.app.spring.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateCarReflect {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Throwable {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.jomolangma.app.spring.reflect.PrivateCar");
		PrivateCar pcar = (PrivateCar) clazz.newInstance();

		Field colorFld = clazz.getDeclaredField("color");
		colorFld.setAccessible(true);
		colorFld.set(pcar, "红色");

		Method driveMtd = clazz.getDeclaredMethod("drive", (Class[]) null);
		// Method driveMtd = clazz.getDeclaredMethod("drive");
		driveMtd.setAccessible(true);
		driveMtd.invoke(pcar, (Object[]) null);
	}
}
