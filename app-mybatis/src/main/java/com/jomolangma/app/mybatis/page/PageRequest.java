package com.jomolangma.app.mybatis.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求信息
 */
public class PageRequest implements Serializable {

	private static final long serialVersionUID = 9092186838918641382L;

	/**
	 * 过滤参数
	 */
	private Map<String,Object> filters;

	/**
	 * 页号码,页码从1开始
	 */
	private int pageNumber;

	/**
	 * 分页大小
	 */
	private int pageSize;

	public PageRequest() {

		this(0, 0, null);
	}

	public PageRequest(int pageNumber, int pageSize) {

		this(pageNumber, pageSize, new HashMap<String, Object>());
	}

	public PageRequest(int pageNumber, int pageSize, Map<String, Object> filters) {

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.filters = filters;
	}

	public Map<String,Object> getFilters() {

		return filters;
	}

	public void setFilters(Map<String,Object> filters) {

		this.filters = filters;
	}

	public int getPageNumber() {

		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {

		this.pageNumber = pageNumber;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public void setPageNumberAndSize(int start, int limit) {

		this.pageSize = limit;
		this.pageNumber = start / limit;
	}
	
	public void addFilter(String key,Object value) {
		if (filters == null) {
			filters = new HashMap<String,Object>();
		}
		filters.put(key, value);
	}
	
	public int getOffset() {
		int offset = (pageNumber - 1) * pageSize;
		offset = offset > 0 ? offset : 0;
		return offset;
	}
}
