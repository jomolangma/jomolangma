package com.jomolangma.app.spring.context;

import java.util.List;

public class Boss {
	private String name;
	private Car car;
	private List<String> hourseList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public List<String> getHourseList() {
		return hourseList;
	}
	public void setHourseList(List<String> hourseList) {
		this.hourseList = hourseList;
	}
}
