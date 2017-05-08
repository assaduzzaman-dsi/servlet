package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserList
 */
//@WebServlet("/UserList")
public class UserList extends HttpServlet {
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
			
			 List<UserDetails> list=new ArrayList<UserDetails>();  
			 
			 out.println("<a href='adminview.html'>Click For Home Page</a>");
			 out.print("<br>");
			
			 try{  
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
					
					Statement stm = con.createStatement();
					
					
					ResultSet rs = stm.executeQuery("select * from registeruser");
					
					  while(rs.next()){  
			                UserDetails u=new UserDetails();  
			                u.setId(rs.getInt(1));  
			                u.setName(rs.getString(2));  
			                u.setPassword(rs.getString(3));  
			                u.setEmail(rs.getString(4));  
			                u.setCountry(rs.getString(5));  
			                
			                list.add(u);
			            }  
					
					  
					  out.print("<table border='1' width='100%'");  
				        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>"); 
					
					for(UserDetails u:list){  
				         out.print("<tr><td>"+u.getId()+"</td><td>"+u.getName()+"</td><td>"+u.getPassword()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCountry()+"</td><td><a href='UserEdit?id="+u.getId()+"'>edit</a></td><td><a href='UserDelete?id="+u.getId()+"'>delete</a></td></tr>");  
				        }
			    out.print("</table>");
			    
			   
		}
			    catch (Exception e2) {e2.printStackTrace();}
			    finally{out.close();} 
	        
			
		}
		else{
			
			out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
			 out.print("<br>");
		
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			
			Statement stm = con.createStatement();
			
			out.print("<table width=50% border=1>");  
			out.print("<caption>User List:</caption>");
			
			ResultSet rs = stm.executeQuery("select * from registeruser");
			
			ResultSetMetaData rdms = rs.getMetaData();
			int total = rdms.getColumnCount();
			
			out.print("<tr>");
			
			for(int i=1;i<=total;i++){
				out.print("<th>"+rdms.getColumnName(i)+"</th>");
			}
			out.print("</tr>");
			
			while(rs.next())  
			{  
			out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");  
			                  
			} 
	    out.print("</table>");
	}
		 catch (Exception e2) {e2.printStackTrace();}
		
	    finally{out.close();}
	}
	}

}
