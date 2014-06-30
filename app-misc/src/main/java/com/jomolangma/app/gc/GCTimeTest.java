package com.jomolangma.app.gc;

import java.util.HashMap;

public class GCTimeTest {
	static HashMap<Long, byte[]> map = new HashMap<Long, byte[]>();
	
	public static void main(String[] args){
		long beginTime = System.currentTimeMillis();
		
		for (int i=0;i<10000;i++){
			if (map.size()*512/1024/1024>400){
				map.clear();
				System.out.println("Clean up");
			}
			
			byte[] bytes;
			for (int j=0;j<100;j++){
				bytes=new byte[512];
				map.put(System.nanoTime(), bytes);
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(beginTime - endTime);
	}

}
