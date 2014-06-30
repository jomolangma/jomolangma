package com.jomolangma.app.mybatis.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValueValidator {
	
	/**
	 * 表示指定的成员变量是否可为null，默认值true
	 * @return
	 */
	boolean nullable() default false;
	/**
	 * 表示指定的成员变量的最小值，只针对数值型(Number的子类)，默认值1
	 * @return
	 */
	long minValue() default 1;
	/**
	 * 表示指定的成员变量的最大值，只针对数值型(Number的自类)，默认值Long.MAX_VALUE;
	 */
	long maxValue() default Long.MAX_VALUE;
	/**
	 * 表示指定的成员变量的长度大小，目前支持的类型有String，Collection的子类以及Map的子类
	 * @return
	 */
	int length() default Integer.MAX_VALUE;

}
