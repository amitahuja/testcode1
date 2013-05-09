package util;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;         
import java.sql.ResultSet;          
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;  
//import java.sql.Connection;         
import java.sql.DriverManager;
//import java.sql.ResultSet;          
//import java.sql.SQLException;       
//import java.sql.PreparedStatement;  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
 
import javax.naming.Context;        
import javax.naming.InitialContext; 
import javax.sql.DataSource;        
 
public class ExceptionInformation {           
 
    Connection conn = null;            
    PreparedStatement stmt = null;     
    String sql = null;
 
    //Get list of customers for a specific store Id with Extjs paging paramaters start and limit
    public ArrayList<LoanException> getExceptions() { 
 
        ArrayList<LoanException> customerList = new ArrayList<LoanException>();   
 
        try {      
        	String sql = "Select * from exception";
        	Properties prop = new Properties();
    		prop.put("user","amit");
    		prop.put("password","amit");
    		Class.forName ("com.mysql.jdbc.Driver");//.newInstance ();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",prop);
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
 
            while(rs.next()){ 
                LoanException customer = new LoanException();
                customer.setLoanId(rs.getString("loan_id").trim());
                customer.setDesc(rs.getString("exc_desc").trim());
                customer.setExcType(rs.getString("exc_type").trim());
                
                //customer.setDesc(rs.getString("country").trim());
                customerList.add(customer);
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
 
        return customerList;
 
    }  
}   