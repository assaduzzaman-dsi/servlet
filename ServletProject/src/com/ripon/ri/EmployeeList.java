package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.java.ripon.Emp;

/**
 * Servlet implementation class EmployeeList
 */
//@WebServlet("/EmployeeList")
public class EmployeeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 
		        List<Eu> list=new ArrayList<Eu>();  
		
		 out.println("<a href='adminview.html'>Click For Home Page</a>");
		 out.print("<br>");
		
		 try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
				
				Statement stm = con.createStatement();
				
				 
				
				ResultSet rs = stm.executeQuery("select * from emregister");
				
				  while(rs.next()){  
		                Eu e=new Eu();  
		                e.setId(rs.getInt(1));  
		                e.setName(rs.getString(2));  
		                e.setPassword(rs.getString(3));  
		                e.setEmail(rs.getString(4));  
		                e.setDesignation(rs.getString(5));
		                e.setSalary(rs.getString(6));
		                e.setCountry(rs.getString(7));  
		                
		                list.add(e);
		            }  
				
				  
				  out.print("<table border='1' width='100%'");  
			        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Designation</th><th>Salary</th><th>Country</th><th>Edit</th><th>Delete</th></tr>"); 
				
				for(Eu e:list){  
			         out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getEmail()+"</td><td>"+e.getDesignation()+"</td><td>"+e.getSalary()+"</td><td>"+e.getCountry()+"</td><td><a href='EmployeeEdit?id="+e.getId()+"'>edit</a></td><td><a href='EmployeeDelete?id="+e.getId()+"'>delete</a></td></tr>");  
			        }
		    out.print("</table>");
		    
		   
	}
		    catch (Exception e2) {e2.printStackTrace();}
		    finally{out.close();} 
		
	}

}
