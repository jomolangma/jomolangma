package com.jomolangma.app.performance.ch3.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 *-Xmx512M -Xms512M ʹ���������Բ��Խ����һ��Ӱ�� 
 * @author Administrator
 *
 */
public class TestListCapacity {
	protected List<Object> list;
	
	protected void testAddTail(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<1000000;i++){
			list.add(obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	@Test
	public void testAddTailArrayList() {
		list=new ArrayList<Object>();
		testAddTail("testAddTailArrayList");
	}	
	
	@Test
	public void testAddTailArrayListCapacity() {
		list=new ArrayList<Object>(1000000);
		testAddTail("testAddTailArrayListCapacity");
	}
	
}
