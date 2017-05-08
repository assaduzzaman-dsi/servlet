package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeProfile
 */
@WebServlet("/EmployeeProfile")
public class EmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
         
          
        HttpSession session=request.getSession(true);  
        if(session!=null){  
        String name=(String)session.getAttribute("name");  
          
        out.print("Hello, "+name+" Welcome to Profile");  
        
        try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");  
    		Connection con1=DriverManager.getConnection(  
    		"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
    		      
    		PreparedStatement ps1=con1.prepareStatement(  
    		"select * from emregister where name=?");
    		
    		ps1.setString(1, name);
    		
    		ResultSet rs = ps1.executeQuery();
    		
    		List<Eu> list=new ArrayList<Eu>();
    		
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
	        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Designation</th><th>Salary</th><th>Country</th><th>Edit</th></tr>"); 
		
		for(Eu e:list){  
	         out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getEmail()+"</td><td>"+e.getDesignation()+"</td><td>"+e.getSalary()+"</td><td>"+e.getCountry()+"</td><td><a href='EmployeeEdit?id="+e.getId()+"'>edit email or password</a></td></tr>");  
	        }
    out.print("</table>");
    		
    		
    		
    	}
    	catch(Exception e){System.out.println(e);}
		
		 
      }
	}

}
