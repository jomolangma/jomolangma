package com.jomolangma.app;

@SuppressWarnings("unused")
public class OutClass {
	private int i;
	private static int j;
	
	public void nonStaticMethod(){
		System.out.println("It's a non static method");
	}
	
	public static void staticMethod(){
		System.out.println("It's a static method");
	}
	
	class NonStaticInnerClass{
		//1.编译出错,不能在非静态内部类中声明静态变量跟静态方法
		//private static int x;
		//public static void method1(){}
		
		//2.非静态内部类可以直接访问外部类的静态变量,非静态变量,静态方法跟非静态方法
		public void method2(){
			System.out.println(i);
			System.out.println(j);
			nonStaticMethod();
			staticMethod();
		}
	}

	static class StaticInnerClass{
		//3.静态内部类能够声明静态变量跟静态方法
		private static int x;
		public static void method3(){}
				
		//4.静态内部类不能访问外部类的非静态变量跟非静态方法
		public void method4(){
			//变量出错,静态内部类不能访问外部类的非静态变量跟非静态方法
			//System.out.println(i);
			System.out.println(j);
			//nonStaticMethod();
			staticMethod();
		}
	}
	
	public static void main(String[] args){
		//5.非静态内部类new时,需要引用外部类;而静态内部类可以直接new
		OutClass out = new OutClass();
		OutClass.NonStaticInnerClass nonStaticInnerClass = out.new NonStaticInnerClass();
		
		StaticInnerClass staticInnerClass = new StaticInnerClass();
	}
}
