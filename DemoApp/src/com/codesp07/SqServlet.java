package com.codesp07;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/sq")
public class SqServlet extends HttpServlet {
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//	int k = Integer.parseInt(req.getParameter("k"));//url redirect
			
			//Session Management
//			HttpSession session = req.getSession();
//			int k = (int)session.getAttribute("k");
			
		//Cookie
			int k = 0;
			Cookie cookies[] = req.getCookies();
			
			for(Cookie c: cookies) {
				if(c.getName().equals("k"))
					k = Integer.parseInt(c.getValue());
			}
			
			k = k*k;
			PrintWriter out = res.getWriter();
			out.println("<html><body bgcolor='cyan'>");
			out.println("Square is : " + k);
			out.println("</body></html>");
			
			System.out.println("sq is called");
		}
}
