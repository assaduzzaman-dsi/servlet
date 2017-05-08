package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminRegister
 */
//@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String n = request.getParameter("aname");
		String p = request.getParameter("apassword");
		String e = request.getParameter("aemail");
		String c = request.getParameter("acountry");
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		      PreparedStatement ps =con.prepareStatement("insert into registeruser values(?,?,?,?)");  
		  
		      ps.setString(1, n);
		      ps.setString(2, p);
		      ps.setString(3, e);
		      ps.setString(4, c);
		      
		      if(n=="" || p=="" || e=="" || c==""){
		    	  pw.print("Please fillup necessary field");
		    	  RequestDispatcher rd = request.getRequestDispatcher("/aregister.html");
		    	  rd.forward(request, response);
		    	  
		      }
		      else{
		    	  pw.print("registration successful.......");
		      }	      
		}catch (Exception e2) {System.out.println(e2);}   
		pw.close();
	}

}
