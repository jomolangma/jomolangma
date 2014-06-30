package com.jomolangma.app.mybatis.basic;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.jomolangma.app.mybatis.basic.annotation.FieldValueValidator;
import com.jomolangma.app.mybatis.util.StringUtil;

public abstract class BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	private void checkNullable(boolean nullable, Object value, String fieldName) {
		if (!nullable) {
			if (value == null)
				throw new RuntimeException(StringUtil.formatMessage("{0} can't be null.", fieldName));
		}
	}

	private void checkLength(int length, Field field, Object value, String fieldName) {
		if (value == null)
			return;
		// String的长度不能大于指定的长度
		// Collection类内包含的对象数量不能大于指定的大小
		// Map类内包含的对象数量不能大于指定的大小
		if ((field.getType().equals(String.class) && ((String) value).length() > length)
				|| (Collection.class.isAssignableFrom(field.getType()) && ((Collection<?>) value)
						.size() > length)
				|| (Map.class.isAssignableFrom(field.getType()) && ((Map<?, ?>) value)
						.size() > length))
			throw new RuntimeException(
					StringUtil.formatMessage("size of {0} must not more than {1}.", fieldName, length));
	}

	private void checkMinValue(long minValue, Object value, String fieldName) {
		if (value == null)
			return;
		if ((value instanceof Integer && ((Integer) value).longValue() < minValue)
				|| (value instanceof Byte && ((Byte) value).longValue() < minValue)
				|| (value instanceof Short && ((Short) value).longValue() < minValue)
				|| (value instanceof Float && ((Float) value).floatValue() < minValue)
				|| (value instanceof Double && ((Double) value).doubleValue() < minValue)
				|| (value instanceof BigInteger && ((BigInteger) value)
						.compareTo(BigInteger.valueOf(minValue)) < 0)
				|| (value instanceof BigDecimal && ((BigDecimal) value)
						.compareTo(BigDecimal.valueOf(minValue)) < 0)) {
			throw new RuntimeException(
					StringUtil.formatMessage("{0} must not less than {1}(actual {2}).",	fieldName, minValue, value));
		}
	}

	private void checkMaxValue(long maxValue, Object value, String fieldName) {
		if (value == null)
			return;
		if ((value instanceof Integer && ((Integer) value).longValue() > maxValue)
				|| (value instanceof Byte && ((Byte) value).longValue() > maxValue)
				|| (value instanceof Short && ((Short) value).longValue() > maxValue)
				|| (value instanceof Float && ((Float) value).floatValue() > maxValue)
				|| (value instanceof Double && ((Double) value).doubleValue() > maxValue)
				|| (value instanceof BigInteger && ((BigInteger) value)
						.compareTo(BigInteger.valueOf(maxValue)) > 0)
				|| (value instanceof BigDecimal && ((BigDecimal) value)
						.compareTo(BigDecimal.valueOf(maxValue)) > 0)) {
			throw new RuntimeException(
					StringUtil.formatMessage("{0} must not more than {1}(actual {2}).", fieldName, maxValue, value));
		}
	}

	public void checkDataEntry() {
		Field[] fields = this.getClass().getDeclaredFields();
		Object obj;
		for (Field field : fields) {
			FieldValueValidator annotation = field
					.getAnnotation(FieldValueValidator.class);
			if (annotation != null) {
				try {
					field.setAccessible(true);
					obj = field.get(this);
					String fieldName = this.getClass().getSimpleName() + "." + field.getName();
					checkNullable(annotation.nullable(),obj,fieldName);
					checkLength(annotation.length(),field,obj,fieldName);
					checkMinValue(annotation.minValue(),obj,fieldName);
					checkMaxValue(annotation.maxValue(),obj,fieldName);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
