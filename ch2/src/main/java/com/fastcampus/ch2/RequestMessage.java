package com.fastcampus.ch2;

import java.io.InputStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMessage {
	@RequestMapping("/requestMessage")
	public void main(HttpServletRequest request) throws Exception {
		// 요청의 헤더와 메시지까지 모두 출력해보기
		// 1. request line
		String requestLine = request.getMethod();       // GET 또는 POST
		requestLine += " " + request.getRequestURI();   // /ch2/requestMessage
		
		String queryString = request.getQueryString();  // year=2021&month=10&day=1
		requestLine += queryString == null ? "" : "?"+queryString;  
		requestLine += " " + request.getProtocol();     // HTTP/1.1
		System.out.println(requestLine);		

		
		// 2. request headers
		Enumeration<String> e = request.getHeaderNames();

		while (e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name + ":" + request.getHeader(name));
		}
		
		// 3. request body - POST일 때만 해당, GET은 body가 없음(CONTENT_LENGTH=-1)
		final int CONTENT_LENGTH = request.getContentLength();
		System.out.println("(body content length="+CONTENT_LENGTH+")");
		
		if(CONTENT_LENGTH > 0) {	// body가 있을 때(POST일 때)
			byte[] content = new byte[CONTENT_LENGTH];

			InputStream in = request.getInputStream();
			in.read(content, 0, CONTENT_LENGTH);
			
			System.out.println(); // empty line
			System.out.println(new String(content, "utf-8")); // year=2021&month=10&day=1
		}  // if
	} // main
}