package com.baobaotao.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 *<br><b>类描述:</b>
 *<pre>所示PO的父类</pre>
 *@see
 *@since
 */
public class BaseDomain implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038001599431276800L;

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
