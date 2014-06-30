package com.jomolangma.app.performance.ch3.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class TestList {
	private static final int CIRCLE1 = 100000;
	protected List<Object> list;
	
	protected void testAddTail(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<500000;i++){
			list.add(obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelTail(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(list.size()-1);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelFirst(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(0);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelMiddle(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(list.size()>>1);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testAddFirst(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<50000;i++){
			list.add(0, obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	
	//====add tail
	@Test
	public void testAddTailArrayList() {
		list=new ArrayList<Object>();
		testAddTail("testAddTailArrayList");
	}
	
	//@Test
	public void testAddTailVector() {
		list=new Vector<Object>();
		testAddTail("testAddTailVector");
	}
	
	@Test
	public void testAddTailLinkedList() {
		list=new LinkedList<Object>();
		testAddTail("testAddTailLinkedList");
	}
	
	//====add first
	//@Test
	public void testAddFirstArrayList() {
		list=new ArrayList<Object>();
		testAddFirst("testAddFirstArrayList");
	}
	
	//@Test
	public void testAddFirstVector() {
		list=new Vector<Object>();
		testAddFirst("testAddFirstVector");
	}
	
	//@Test
	public void testAddFirstLinkedList() {
		list=new LinkedList<Object>();
		testAddFirst("testAddFirstLinkedList");
	}
	
	//====delete tail
	//@Test
	public void testDeleteTailArrayList() {
		list=new ArrayList<Object>();
		
		testDelTail("testDeleteTailArrayList");
	}
	
	//@Test
	public void testDeleteTailVector() {
		list=new Vector<Object>();
		testDelTail("testDeleteTailVector");
	}
	
	//@Test
	public void testDeleteTailLinkedList() {
		list=new LinkedList<Object>();
		testDelTail("testDeleteTailLinkedList");
	}
	
	//====delete first
	//@Test
	public void testDeleteFirstArrayList() {
		list=new ArrayList<Object>();
		testDelFirst("testDeleteFirstArrayList");
	}
	
	//@Test
	public void testDeleteFirstVector() {
		list=new Vector<Object>();
		testDelFirst("testDeleteFirstVector");
	}
	
	//@Test
	public void testDeleteFirstLinkedList() {
		list=new LinkedList<Object>();
		testDelFirst("testDeleteFirstLinkedList");
	}
	
	//@Test
	public void testDeleteMiddleLinkedList() {
		list=new LinkedList<Object>();
		testDelMiddle("testDeleteMiddleLinkedList");
	}
	
	//@Test
	public void testDeleteMiddleArrayList() {
		list=new ArrayList<Object>();
		testDelMiddle("testDeleteMiddleArrayList");
	}
}
