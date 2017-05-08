package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductEdit1
 */
@WebServlet("/ProductEdit1")
public class ProductEdit1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String pid = request.getParameter("id");
		int id=Integer.parseInt(pid);
		String name = request.getParameter("pname");
		String price = request.getParameter("pprice");
		String status = request.getParameter("pstatus");
		
		HttpSession session = request.getSession(true);
		String n = (String) session.getAttribute("name");
		
		if(n.equals("admin")){
			out.println("<a href='adminview.html'>Click For Home Page</a>");		
		}
		else{
			
			out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
		}
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
		      PreparedStatement ps =con.prepareStatement("update productlist set name=?,price=?,status=? where id =?");
		      
		      
		      ps.setString(1, name);
		      ps.setString(2, price);
		      ps.setString(3, status);
		      ps.setInt(4, id);
		      
		      int i = ps.executeUpdate();
		      
		      if(i>0){
		    	out.print("update successful");  
		    	out.print("<br>");
		    	
		      }
		      else{  
		            out.println("Sorry! unable to update record");  
		        } 
		      
		}
		catch(Exception ex){ex.printStackTrace();} 

	}

}
