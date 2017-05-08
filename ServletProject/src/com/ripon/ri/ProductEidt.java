package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductEidt
 */
@WebServlet("/ProductEidt")
public class ProductEidt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
		String n = (String) session.getAttribute("name");
		out.print("<br>");
		if(n.equals("admin")){
			out.println("<a href='adminview.html'>Click For Home Page</a>");		
		}
		else{
			
			out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
		}
		 out.print("<br>");
		out.println("<h1>Update ProductList</h1>"); 
		String sd = request.getParameter("id");
		int id = Integer.parseInt(sd);
		
		
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		   PreparedStatement ps =con.prepareStatement("select * from productlist where id=?");
		   ps.setInt(1, id);
		   ResultSet rs= ps.executeQuery();
		   
		   while(rs.next()){
			   rs.getInt(1);
			   rs.getString(2);
			   rs.getString(3);
			   rs.getString(4);
			   
			   
			   out.print("<form action='ProductEdit1' method='post'>");  
		        out.print("<table>");  
		        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+rs.getInt(1)+"'/></td></tr>");  
		        out.print("<tr><td>Name:</td><td><input type='text' name='pname' value='"+rs.getString(2)+"'/></td></tr>");  
		        out.print("<tr><td>price:</td><td><input type='text' name='pprice' value='"+rs.getString(3)+"'/>   </td></tr>");  
		        out.print("<tr><td>Status:</td><td>");  
		        out.print("<select name='pstatus' style='width:150px'>");  
		        out.print("<option>Available</option>");  
		        out.print("<option>NotAvailable</option>");    
		        out.print("</select>");  
		        out.print("</td></tr>");  
		        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
		        out.print("</table>");  
		        out.print("</form>");  
		          
		        out.close();  
		   }
		   
		   
		   con.close();
		}
		catch(Exception ex){ex.printStackTrace();}  
		
		
	}

}
