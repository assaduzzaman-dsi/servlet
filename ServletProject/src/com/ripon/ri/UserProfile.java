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
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
    		"select * from registeruser where name=?");
    		
    		ps1.setString(1, name);
    		
    		ResultSet rs = ps1.executeQuery();
    		
    		List<UserDetails> list=new ArrayList<UserDetails>();  
    		
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
	        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th></tr>"); 
		
		for(UserDetails u:list){  
	         out.print("<tr><td>"+u.getId()+"</td><td>"+u.getName()+"</td><td>"+u.getPassword()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCountry()+"</td><td><a href='UserEdit?id="+u.getId()+"'>edit profile</a></td></tr>");  
	        }
    out.print("</table>");
    		
    		
    		
    	}
    	catch(Exception e){System.out.println(e);}
        }  
	}

}
