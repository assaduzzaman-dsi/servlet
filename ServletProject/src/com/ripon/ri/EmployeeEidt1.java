package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class EmployeeEidt1
 */
@WebServlet("/EmployeeEidt1")
public class EmployeeEidt1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        HttpSession session=request.getSession(true); 
		String n=(String)session.getAttribute("name");
		
		if(n.equals("admin")){
          
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
        String name=request.getParameter("ename");  
        String password=request.getParameter("epassword");  
        String email=request.getParameter("eemail");
        String designation =request.getParameter("edesignation");
        String salary=request.getParameter("esalary");
        String country=request.getParameter("ecountry");  
        
        try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
		      PreparedStatement ps =con.prepareStatement("update emregister set name=?,password=?,email=?,designation=?,salary=?,country=? where id=?");  

		      ps.setString(1, name);
		      ps.setString(2, password);
		      ps.setString(3, email);
		      ps.setString(4, designation);
		      ps.setString(5, salary);
		      ps.setString(6, country);
		      ps.setInt(7, id);
		      
		      
		      int i =ps.executeUpdate();
		      
		      if(i>0){
		    	out.print("Update successful"); 
		    	out.print("<br>");
		    	out.println("<a href='adminview.html'>Click For Home Page</a>");
				 
		      }
		      else{  
		            out.println("Sorry! unable to update record");  
		        }  
		      
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
		}
		
		else{
			
			String sid=request.getParameter("id");  
	        int id=Integer.parseInt(sid);  
	       // String name=request.getParameter("ename");  
	        String password=request.getParameter("epassword");  
	        String email=request.getParameter("eemail");
	       // String designation =request.getParameter("edesignation");
	      //  String salary=request.getParameter("esalary");
	      //  String country=request.getParameter("ecountry");  
	        
	        try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			      PreparedStatement ps =con.prepareStatement("update emregister set password=?,email=? where id=?");  

			     // ps.setString(1, name);
			      ps.setString(1, password);
			      ps.setString(2, email);
			     // ps.setString(4, designation);
			    //  ps.setString(5, salary);
			    //  ps.setString(6, country);
			      ps.setInt(3, id);
			      
			      
			      int i =ps.executeUpdate();
			      
			      if(i>0){
			    	out.print("Update successful"); 
			    	out.print("<br>");
			    	
			    	
			    	HttpSession session1=request.getSession();  
			        session1.setAttribute("name",n);
			        
			       
					
			        List<Eu> list=new ArrayList<Eu>();  
			        
			        RequestDispatcher rd = request.getRequestDispatcher("/EmployeeView.html");
					rd.include(request, response);

			        
			        try{
			    		Class.forName("oracle.jdbc.driver.OracleDriver");  
			    		Connection con1=DriverManager.getConnection(  
			    		"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			    		      
			    		PreparedStatement ps1=con1.prepareStatement(  
			    		"select * from emregister where name=?");
			    		
			    		ps1.setString(1, n);
			    		
			    		ResultSet rs = ps1.executeQuery();
			    		
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
			      else{  
			            out.println("Sorry! unable to update record");  
			        }  
			      
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
			
		}
	}

}
