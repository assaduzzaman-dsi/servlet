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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
//@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String n=request.getParameter("name");  
	    String p=request.getParameter("password");  
	          
	    if(LoginDao.validate(n, p)){  
	       
	        RequestDispatcher rd = request.getRequestDispatcher("/UserView.html");
			rd.include(request, response);
	        
			out.print("<br>");
		out.print("Login successful ");
		out.print("<br>");
		out.print("welcome "+n);
		out.print("<br>");
		
		HttpSession session=request.getSession();  
        session.setAttribute("name",n);
        
       
		
        List<UserDetails> list=new ArrayList<UserDetails>();  

        
        try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
    		      
    		PreparedStatement ps=con.prepareStatement(  
    		"select * from registeruser where name=?");
    		
    		ps.setString(1, n);
    		
    		ResultSet rs = ps.executeQuery();
    		
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
	         out.print("<tr><td>"+u.getId()+"</td><td>"+u.getName()+"</td><td>"+u.getPassword()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCountry()+"</td><td><a href='UserEdit?id="+u.getId()+"'>edit email or password</a></td></tr>");  
	        }
    out.print("</table>");
    		
    		
    		
    	}
    	catch(Exception e){System.out.println(e);}
		
	    }  
	    else{  
	        out.print("Sorry username or password error");  
	        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
	        rd.include(request,response);  
	    }  
	          
	    out.close();  
	           
	}

}
