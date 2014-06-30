package com.jomolangma.app.performance.ch3.teststr.split.junit;

import org.junit.Test;

public class StringSplitFunctionTest {

	@Test
	public void testSplit() {
		String re[]="a;b,c:d".split("[;|,|:]");
		for(String s:re){
			System.out.println(s);
		}
	}

	
}
