package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. ���� ȣ�� ������ ���α׷����� ���
@Controller
public class Hello {
	
	int iv = 10;	// �ν��Ͻ� ����
	static int cv = 20;	// Ŭ���� ����

	// 2. URL�� �޼��带 ����
//	@RequestMapping("/hello")
	public void main() {	// �ν��Ͻ� �޼��� : iv, cv �� �� ��� ����
		System.out.println("Hello");	// ���������� ����� ������ ������ �ֿܼ� ����
		System.out.println(cv);	// 20
		System.out.println(iv);	// 10
	}
	
//	@RequestMapping("/hello")
	public static void main2() {	// static �޼��� : cv�� ��� ����
		System.out.println("Hello - static");
		System.out.println(cv);	// 20
//		System.out.println(iv);	// ����
	}
	
	@RequestMapping("/hello")
	private void main3() {
		System.out.println("Hello - private");
		System.out.println(cv);
		System.out.println(iv);
	}
}
