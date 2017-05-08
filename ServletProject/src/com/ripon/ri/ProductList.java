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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductList
 */
//@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 
		HttpSession session=request.getSession(true); 
		String n=(String)session.getAttribute("name");
		
        if(n.equals("admin")){  
        	out.println("<a href='adminview.html'>Click For Home Page</a>");
        	
        	out.print("<br/>For quick search");
    	    RequestDispatcher rd = request.getRequestDispatcher("/search.html");
    	    rd.include(request, response);
    	    
    	    List<Product> list=new ArrayList<Product>();
    		
    		 try{  
    				Class.forName("oracle.jdbc.driver.OracleDriver");  
    				Connection con=DriverManager.getConnection(  
    				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
    				
    				Statement stm = con.createStatement();
    				
    				/*out.print("<table width=50% border=1>");  
    				out.print("<caption>Product List:</caption>");  */
    				
    				ResultSet rs = stm.executeQuery("select * from productlist");
    				
    				while(rs.next()){
    					Product p = new Product();
    					p.setId(rs.getInt(1));
    					p.setProductname(rs.getString(2));
    					p.setPrice(rs.getString(3));
    					p.setStatus(rs.getString(4));
    					
    					list.add(p);
    				}
    				
    				/*ResultSetMetaData rdms = rs.getMetaData();
    				int total = rdms.getColumnCount();
    				out.print("<tr>");
    				for(int i=1;i<=total;i++){
    					out.println("<th>"+rdms.getColumnName(i)+"</th>");
    				}
    				out.print("</tr>");*/
    				
    				out.print("<table border='1' width='100%'");  
    		        out.print("<tr><th>Id</th><th>ProductName</th><th>Price</th><th>Status</th><th>Edit</th><th>Delete</th></tr>"); 
    			
    				
    				for(Product p:list)  
    				{  
    				out.print("<tr><td>"+p.getId()+"</td><td>"+p.getProductname()+"</td><td>"+p.getPrice()+"</td><td>"+p.getStatus()+"</td><td><a href='ProductEidt?id="+p.getId()+"'>edit</a></td><td><a href='ProductDelete?id="+p.getId()+"'>Delete</a></td></tr>");  
    				                  
    				} 
    		    out.print("</table>");
    		    
    		   
    	}
    		    catch (Exception e2) {e2.printStackTrace();}
    		    finally{out.close();} 
    	
		}
		else {
			out.println("<a href='EmployeeView.html'>Click For Home Page</a>");
		
		
		out.print("<br/>For quick search");
	    RequestDispatcher rd = request.getRequestDispatcher("/search.html");
	    rd.include(request, response);
	    
	    List<Product> list=new ArrayList<Product>();
		
		 try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
				
				Statement stm = con.createStatement();
				
				/*out.print("<table width=50% border=1>");  
				out.print("<caption>Product List:</caption>");  */
				
				ResultSet rs = stm.executeQuery("select * from productlist");
				
				while(rs.next()){
					Product p = new Product();
					p.setId(rs.getInt(1));
					p.setProductname(rs.getString(2));
					p.setPrice(rs.getString(3));
					p.setStatus(rs.getString(4));
					
					list.add(p);
				}
				
				/*ResultSetMetaData rdms = rs.getMetaData();
				int total = rdms.getColumnCount();
				out.print("<tr>");
				for(int i=1;i<=total;i++){
					out.println("<th>"+rdms.getColumnName(i)+"</th>");
				}
				out.print("</tr>");*/
				
				out.print("<table border='1' width='100%'");  
		        out.print("<tr><th>Id</th><th>ProductName</th><th>Price</th><th>Status</th><th>Edit</th></tr>"); 
			
				
				for(Product p:list)  
				{  
				out.print("<tr><td>"+p.getId()+"</td><td>"+p.getProductname()+"</td><td>"+p.getPrice()+"</td><td>"+p.getStatus()+"</td><td><a href='ProductEidt?id="+p.getId()+"'>edit</a></td></tr>");  
				                  
				} 
		    out.print("</table>");
		    
		   
	}
		    catch (Exception e2) {e2.printStackTrace();}
		    finally{out.close();} 
		}
	
	}

}
