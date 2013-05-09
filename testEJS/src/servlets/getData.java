package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;         
import java.sql.ResultSet;          
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;//ArrayList;
//import java.lang.*;
//import com.mysql.jdbc.*;
 
import javax.naming.Context;        
import javax.naming.InitialContext; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;        

@WebServlet("/data")
public class getData extends HttpServlet {  
	Connection conn = null;            
    PreparedStatement stmt = null;     
    String sql = null;
 
	 public void doGet(HttpServletRequest request,
             HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html");
PrintWriter out = response.getWriter();
String title = "Reading From A Database Table";
String docType =
"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
"Transitional//EN\">\n";

out.println
  ("<!DOCTYPE html>\n" +
   "<html>\n" +
   "<head><title>A Test Servlet for me</title></head>\n" +
   "<body bgcolor=\"#fdf5e6\">\n" +
   "<h1>Hello Test Data</h1>\n" +
   "<p>Simple servlet for getting data and loading.</p><table border=\"1\"><th>Id</th><th>Name</th><th>Address</th><th>State</th>\n");// +
   //"</body></html>");
	sql = "Select * from owners order by state,name";
        //" from Customer ";// where store_id = ? order by first_name,last_name LIMIT ?,?";                      

	try{
		//Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		//conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
		
		Properties prop = new Properties();
		prop.put("user","amit");
		prop.put("password","amit");
		Class.forName ("com.mysql.jdbc.Driver");//.newInstance ();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/extjs",prop);
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){ 
        	out.println("<tr><td>" + rs.getInt("id") +  "</td><td>"   + rs.getString("name") + "</td><td>" + rs.getString("address") + "</td><td>" +   rs.getString("state") + "</td>" +  "</tr>");
        }
	}
	catch(Exception e){out.println(e);}   
	finally{}
        //stmt.setInt(1,Integer.parseInt(storeId)); 
        //stmt.setInt(2,Integer.parseInt(start)); 
        //stmt.setInt(3,Integer.parseInt(limit)); 
	out.println("</body></html>");
}
 
    
    //Get list of Clients for a specific store Id with Extjs paging paramaters start and limit

    //this method gets us the Total Number of Items that we have in our database
    public int getTotalCount(String storeId) { 
 
        int totalCount = 0; 
 
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
 
            sql = "Select count(*) from Client where store_id = ?";                      
 
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(storeId)); 
 
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){ 
                totalCount = rs.getInt(1);
                break;
            }                                                                         
 
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              
 
 
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){System.out.println(e);}                      
 
        finally {                                                       
             
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
 
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }              
 
        return totalCount;
 
    }  
 
    //insert new Client information
    
}   




