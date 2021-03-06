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
 * Servlet implementation class EmployeeEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession(true); 
		String name=(String)session.getAttribute("name");
		
		if(name.equals("admin")){
			
			out.print("<br>");
			// out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
			 out.print("<br>");
			out.println("<h1>Update Profile</h1>"); 
			String sd = request.getParameter("id");
			int id = Integer.parseInt(sd);
			
			
			
			try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			      
			   PreparedStatement ps =con.prepareStatement("select * from registeruser where id=?");
			   ps.setInt(1, id);
			   ResultSet rs= ps.executeQuery();
			   
			   while(rs.next()){
				  
				   
				   out.print("<form action='UserEdit1' method='post'>");  
			        out.print("<table>");  
			        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+rs.getInt(1)+"'/></td></tr>");  
			       out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+rs.getString(2)+"'/></td></tr>");  
			        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+rs.getString(3)+"'/>   </td></tr>");  
			        out.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+rs.getString(4)+"'/></td></tr>");
			        out.print("<tr><td>Country:</td><td>");  
			        out.print("<select name='country' style='width:150px'>");  
			        out.print("<option>Bangladesh</option>");  
			        out.print("<option>USA</option>");  
			        out.print("<option>UK</option>");  
			        out.print("<option>Other</option>");  
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
		
		else{
			out.print("<br>");
			// out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
			 out.print("<br>");
			out.println("<h1>Update Profile</h1>"); 
			String sd = request.getParameter("id");
			int id = Integer.parseInt(sd);
			
			
			
			try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			      
			   PreparedStatement ps =con.prepareStatement("select * from registeruser where id=?");
			   ps.setInt(1, id);
			   ResultSet rs= ps.executeQuery();
			   
			   while(rs.next()){
				  
				   
				   out.print("<form action='UserEdit1' method='post'>");  
			        out.print("<table>");  
			        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+rs.getInt(1)+"'/></td></tr>");  
			       out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+rs.getString(2)+"'/></td></tr>");  
			        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+rs.getString(3)+"'/>   </td></tr>");  
			        out.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+rs.getString(4)+"'/></td></tr>");
			        out.print("<tr><td>Country:</td><td>");  
			        out.print("<select name='country' style='width:150px'>");  
			        out.print("<option>Bangladesh</option>");  
			        out.print("<option>USA</option>");  
			        out.print("<option>UK</option>");  
			        out.print("<option>Other</option>");  
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
}
	


