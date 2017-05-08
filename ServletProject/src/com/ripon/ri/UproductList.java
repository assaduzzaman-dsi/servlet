package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UproductList
 */
//@WebServlet("/UproductList")
public class UproductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	  //  String n=request.getParameter("name");  
	  //  out.print("Welcome "+n);
	    //HttpSession session=request.getSession();  
       // session.setAttribute("name",n);
        
        out.print("<br>");
        out.print("<a href='logout'>LogOut</a>");
        
	    out.print("<br/>for quick search");
	    RequestDispatcher rd = request.getRequestDispatcher("/search.html");
	    rd.include(request, response);
	    
	    try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			
			Statement stm = con.createStatement();
			
			out.print("<table width=50% border=1>");  
			out.print("<caption>Product List:</caption>");  
			
			ResultSet rs = stm.executeQuery("select * from productlist");
			
			ResultSetMetaData rdms = rs.getMetaData();
			int total = rdms.getColumnCount();
			out.print("<tr>");
			for(int i=1;i<=total;i++){
				out.println("<th>"+rdms.getColumnName(i)+"</th>");
			}
			out.print("</tr>");
			
			while(rs.next())  
			{  
			out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");  
			                  
			} 
	    out.print("</table>");
	    
	    
}
	    catch (Exception e2) {e2.printStackTrace();}
	    finally{out.close();} 
	}

}
