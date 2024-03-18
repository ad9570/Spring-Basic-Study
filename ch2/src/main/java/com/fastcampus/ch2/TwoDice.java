package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
	@RequestMapping("/rollDice")
	public void rollDice(HttpServletResponse response) throws IOException {
		Random rd = new Random();
		int idx1 = rd.nextInt(6) + 1;
		int idx2 = rd.nextInt(6) + 1;

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		/*out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src = 'resources/img/dice" + idx1 + ".jpg'>");
		out.println("<img src = 'resources/img/dice" + idx2 + ".jpg'>");
		out.println("</body>");
		out.println("</html>");*/
		out.println(
				"""
				<html>
				<head></head>
				<body>
				<img src = 'resources/img/dice%s.jpg'/>
				<img src = 'resources/img/dice%s.jpg'/>
				</body>
				</html>
				""".formatted(idx1, idx2)
		);
	}
}