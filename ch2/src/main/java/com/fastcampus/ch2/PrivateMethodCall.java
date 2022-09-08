package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main3();	// private�� ȣ�� �Ұ�
		
		// Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ ��� ����
		// java.lang.reflect
		
		// HelloŬ������ Class��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		Class<?> helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();	// Class��ü�� ���� ������ ��ü ����
		Method main3 = helloClass.getDeclaredMethod("main3");	// helloClass ��ü�� Method ������ ������
		main3.setAccessible(true);	// private�� main3()�� ȣ���ϰ� �Ѵ�
		
		main3.invoke(hello);	// hello.main3();
	}
	
}
