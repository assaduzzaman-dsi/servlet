package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin
 */
//@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n = request.getParameter("aname");
		String p = request.getParameter("apassword");
		
		
		
		if(n.equals("admin")&& p.equals("admin123")){
			out.print("Welcome "+n);
			
			HttpSession session=request.getSession();  
	        session.setAttribute("name",n);
			
			RequestDispatcher rd = request.getRequestDispatcher("/adminview.html");
			rd.include(request, response);			
		}
		else{
			out.print("UserName or password error");
			RequestDispatcher rd = request.getRequestDispatcher("/admin.html");
			rd.include(request, response);
		}
	}

}
