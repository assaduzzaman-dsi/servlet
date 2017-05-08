package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
//@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		          
		String st = request.getParameter("name");
		
		out.print("<br>");
		out.print("<a href='logout'>LogOut</a>");
		
				          
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		              
		PreparedStatement ps=con.prepareStatement("select * from productlist where name=?");  
		ps.setString(1, st);
		
		out.print("<table width=50% border=1>");  
		out.print("<caption>Product Search Result:</caption>");
		
		ResultSet rs = ps.executeQuery();
		
		ResultSetMetaData rsm = rs.getMetaData();
		int r = rsm.getColumnCount();
		
		out.print("<tr>");
		
		for(int i=1;i<=r;i++){
			out.print("<th>"+rsm.getColumnName(i)+"</th>");
		}
		out.print("</tr>");  
		
		while(rs.next())  
		{  
		out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");  
		                  
		} 
    out.print("</table>");
		
    
		}catch (Exception e2) {e2.printStackTrace();}  
		          
		finally{out.close();}  
		  
		}  
		}  


