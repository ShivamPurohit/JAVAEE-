package com.codesp07;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		
		int k = i + j;
		
//Calling one servlet from another
//1.requestDispatcher   and 2. Redirect

		//Now, to share this data of k from one to another servlet we use :-
		//Session Management   (k and value)
			//req.setAttribute("k",k);
		
	//using request Dispatcher
//		RequestDispatcher rd = req.getRequestDispatcher("sq");
//		rd.forward(req, res);
	
		//Using reDirect - this comes under session management 
		//session management can be done with session or cookies.
		//   res.sendRedirect("sq?k="+k);   //url rewriting
		
		
	//Session Management
//		HttpSession session = req.getSession();
//		session.setAttribute("k",k);
//		res.sendRedirect("sq");
		
		//cookie
		Cookie cookie = new Cookie("k",k+"");
		res.addCookie(cookie);
		res.sendRedirect("sq");
		
		
//Printing the result to webpage		
//		PrintWriter out = res.getWriter();
//		out.println("The result is : " + k);
	}
}
