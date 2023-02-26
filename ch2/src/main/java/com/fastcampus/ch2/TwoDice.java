package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {

	@RequestMapping("/rollDice")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Random rd = new Random();
		int idx1 = rd.nextInt(6) + 1;
		int idx2 = rd.nextInt(6) + 1;

		String contextPath = request.getContextPath();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.println(String.format("""
				<html>
					<head>
					</head>
					<body>
						<img src='%simg/dice%s.jpg'>
						<img src='%simg/dice%s.jpg'>
					</body>
				</html>
				""", contextPath, idx1, contextPath, idx2));
//		out.println("<html>");
//		out.println("<head>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<img src = 'resources/img/dice" + idx1 + ".jpg'>");
//		out.println("<img src = 'resources/img/dice" + idx2 + ".jpg'>");
//		out.println("</body>");
//		out.println("</html>");
	}
	
}
