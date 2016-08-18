package edu.ucla.cs.cs144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URLEncoder;

import java.io.PrintWriter;
import java.util.Enumeration;

public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
    	
    	response.setContentType("text/xml");
    	PrintWriter out = response.getWriter();
    	
    	String query = request.getParameter("q");
    	// query must be encoded in to the format : query = "superman%20batman";
    	query = URLEncoder.encode(query, "UTF-8").replace("+", "%20");
    	URL url = new URL("http://google.com/complete/search?output=toolbar&q=" + query);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder writer = new StringBuilder();
            //String writer = "";
            String line = "";

            while ((line = reader.readLine()) != null) 
            {
                 writer.append(line);   
            	//writer += line;
            }

            out.println(writer.toString());
            //out.println(writer);
            
            reader.close();
            
            out.flush();
            out.close();
            conn.disconnect();
        }
        else
        {
        	out.println("The query does not exist! Our program encounters a problem! ");
        	out.println("ResponseCode:"+conn.getResponseCode());
        	out.flush();
        	out.close();
        	conn.disconnect();
        }
        
    }
}
