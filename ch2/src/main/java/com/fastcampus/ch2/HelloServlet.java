package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// 서블릿이 초기화될때 자동 호출되는 메서드
		// 서블릿의 초기화 작업 담당
		super.init();
		System.out.println("[HelloServlet] init() is called");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청이 올 때마다 호출
		// 1. 입력
		// 2. 처리
		// 3. 출력
		super.service(req, resp);
		System.out.println("[HelloServlet] service() is called");
	}

	@Override
	public void destroy() {
		// 뒷처리 - 서블릿이 메모리에서 제거될 때 자동 호출
		// 서블릿이 갱신되어 리로드 되거나, 웹 애플리케이션이 종료될 때
		super.destroy();
		System.out.println("[HelloServlet] destroy() is called");
	}
	
}
