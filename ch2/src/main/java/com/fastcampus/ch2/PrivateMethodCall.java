package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main3();	// private라서 호출 불가
		
		// Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
		// java.lang.reflect
		
		// Hello클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		Class<?> helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();	// Class객체가 가진 정보로 객체 생성
		Method main3 = helloClass.getDeclaredMethod("main3");	// helloClass 객체의 Method 정보를 가져옴
		main3.setAccessible(true);	// private인 main3()을 호출하게 한다
		
		main3.invoke(hello);	// hello.main3();
	}
	
}