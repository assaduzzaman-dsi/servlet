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
 * Servlet implementation class EmployeeRegister
 */
//@WebServlet("/EmployeeRegister")
public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String n = request.getParameter("ename");
		String p = request.getParameter("epassword");
		String e = request.getParameter("eemail");		
		String d = request.getParameter("edesignation");
		String s = request.getParameter("esalary");
		String c = request.getParameter("ecountry");
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		      PreparedStatement ps =con.prepareStatement("insert into emregister(name,password,email,designation,salary,country) values(?,?,?,?,?,?)");  
		  
		
		      ps.setString(1, n);
		      ps.setString(2, p);
		      ps.setString(3, e);
		      ps.setString(4, d);
		      ps.setString(5, s);
		      ps.setString(6, c);
		      
		      if( n=="" || p=="" || e=="" || d=="" || s=="" || c=="" ){
		    	  out.print("Please fillup necessary field");
		    	  RequestDispatcher rd = request.getRequestDispatcher("/Eregister.html");
		    	  rd.include(request, response);
		    	  
		      }
		      else{
		    	  int i = ps.executeUpdate();
		    	  out.print("registration successful.......");
		    	  RequestDispatcher rd = request.getRequestDispatcher("/adminview.html");
		    	  rd.include(request, response);
		      }	      
		}catch (Exception e2) {System.out.println(e2);}   
		out.close();
	}

}
