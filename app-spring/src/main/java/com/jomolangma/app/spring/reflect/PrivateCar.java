package com.jomolangma.app.spring.reflect;

public class PrivateCar {
   private String color;
   protected void drive(){
	   System.out.println("drive private car! the color is:"+color);
   }
}
