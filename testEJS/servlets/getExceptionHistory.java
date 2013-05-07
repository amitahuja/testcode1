package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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

 
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONSerializer;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONSerializer;
 
import util.LoanException;
import util.ExceptionInformation;

@WebServlet("/getExceptionHist")
public class getExceptionHistory extends HttpServlet  {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    	
    	   Connection conn = null;            
    	    PreparedStatement stmt = null;     
    	    String sql = null;
        //get the store Id from the request

 
        //get out Grid paging parameters
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
        String loan_id_in = request.getParameter("loan_id");
        //out.println("console.log(" + loan_id_in + ")");
        if(loan_id_in== null || loan_id_in.length() == 0 ){
        	return;
        }
       
        loan_id_in.trim();
        //out.println(loan_id_in);
        
        String json_data = "{\"data\":[";
        try {      
        	 sql = "Select * from exception_h where loan_id = '" + loan_id_in + "'";
        	Properties prop = new Properties();
    		prop.put("user","amit");
    		prop.put("password","amit");
    		Class.forName ("com.mysql.jdbc.Driver");//.newInstance ();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",prop);
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                String loan_id = rs.getString("loan_id").trim();
                String exc_type = rs.getString("exc_desc").trim();
                String desc =rs.getString("exc_type").trim();
                String date_inserted =rs.getString("date_inserted").trim();
                String action =rs.getString("action").trim();
                json_data = json_data + "{\"loan_id\":\"" + loan_id +  "\",\"exc_type\":\"" + exc_type + "\",\"desc\":\"" + desc +  "\",\"date_inserted\":\"" + date_inserted +  "\",\"action\":\"" + action + "\"},";
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
 
 
        //printwriter to send the JSON response back
        //PrintWriter out = response.getWriter();
        //set content type
       
        json_data = json_data + "]}";
 
        //convert the JSON object to string and send the response back
        //out.println(myObj.toString());
        String json_data2 = "{\"data\":[{\"loan_id\":1,\"exc_type\":\"Name1\",\"desc\":\"Lastname1\"},]}";
        out.println(json_data);
        out.close();
    }
}
