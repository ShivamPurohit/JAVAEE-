package com.codesp07;

import java.io.PrintWriter;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/addAlien")
public class MyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException
	{
		PrintWriter out = response.getWriter();
		System.out.println("in servlet");
		int aid = Integer.parseInt(request.getParameter("aid"));
		String aname = request.getParameter("aname");
		out.println("Welcome " + aname);
	}

}
