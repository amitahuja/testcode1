package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
 
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

 
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONSerializer;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONSerializer;
 
import util.LoanException;
import util.ExceptionInformation;

@WebServlet("/getExceptions")
public class getExceptions extends HttpServlet  {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //get the store Id from the request

 
        //get out Grid paging parameters
        String start = request.getParameter("start");
 
        //printwriter to send the JSON response back
        PrintWriter out = response.getWriter();
        //set content type
        response.setContentType("text/html");
 
        //create a new JSON array to send the list of items
        //JSONArray arrayObj=new JSONArray();
 
        //get arraylist of customers based on the request 
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        ArrayList<LoanException> exceptionList = exceptionInformation.getExceptions();
 
        //loop thru the array list to populate the JSON array
        //out.println("getting exceptions size "+ exceptionList.size() );
        String json_data = "{\"data\":[";
        for(int i=0;i<exceptionList.size();i++){
 
            //get customer Object
        	
        	
            LoanException loan_exc = exceptionList.get(i);
            String loan_id = loan_exc.getLoanId();
            String exc_type = loan_exc.getExcType();
            String desc = loan_exc.getDesc();
            json_data = json_data + "{\"loan_id\":\"" + loan_id +  "\",\"exc_type\":\"" + exc_type + "\",\"desc\":\"" + desc + "\"},";
            //out.println("loan_id is" + loan_id);
            //this creates a JSON object from bean object
            //JSONObject customerObj = JSONObject.fromObject(customer);
            //add to array list
            //arrayObj.add(customerObj);
        }
        json_data = json_data + "]}";
 
        //Create a JSON object to wrap your JSOn array and provide the root element items
        //JSONObject myObj = new JSONObject();
        //sets success to true
        //myObj.put("success", true);
        //set the JSON root to items
        //myObj.put("exception", arrayObj);
        //set the total number of Items
        //myObj.put("totalCount", exceptionInformation.getTotalCount(storeId));
 
        //convert the JSON object to string and send the response back
        //out.println(myObj.toString());
        String json_data2 = "{\"data\":[{\"loan_id\":1,\"exc_type\":\"Name1\",\"desc\":\"Lastname1\"},]}";
        out.println(json_data);
        out.close();
    }
}
