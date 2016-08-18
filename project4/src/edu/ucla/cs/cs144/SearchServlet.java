package edu.ucla.cs.cs144;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Enumeration;

import edu.ucla.cs.cs144.AuctionSearchClient;
import edu.ucla.cs.cs144.SearchResult;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
    	
    	 
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

		  String query=request.getParameter("q");
		  //out.println(query);
		  if (query==null || query.length() == 0)
		  {			  
			  request.getRequestDispatcher("/EmptyQuerySearchInterface.jsp").forward(request, response);
			  
			  out.flush();
			  out.close();
			  return;  
		  }
		  
		  String values_Skip = request.getParameter("numResultsToSkip");
		  int numResultsToSkip = Integer.parseInt(values_Skip);
		  //out.println(" Skip: "+numResultsToSkip);
		  
		  String values_Return = request.getParameter("numResultsToReturn");
		  int numResultsToReturn = Integer.parseInt(values_Return);
		  //out.println(" Return: "+ numResultsToReturn);
		 
		  AuctionSearchClient asc = new AuctionSearchClient();
		  SearchResult[] SR = asc.basicSearch(query, numResultsToSkip, numResultsToReturn);
		 
		  int totalSum = 1000000;
		  SearchResult[] SR_Total = asc.basicSearch(query, 0, totalSum);

		  if (SR_Total.length == 0)
		  {			  
			  request.getRequestDispatcher("/EmptySearchInterface.jsp").forward(request, response);
			  
			  out.flush();
			  out.close();
			  return;  
		  }
		  while(SR_Total.length == totalSum)
		  {
			  totalSum = totalSum + 1000000;
		      SR_Total = asc.basicSearch(query, 0, totalSum);
		  }
		  totalSum = SR_Total.length;
		  
	        request.setAttribute("SR", SR);
	        request.setAttribute("totalSum", totalSum);
	        request.setAttribute("numResultsToSkip", numResultsToSkip);
	        request.setAttribute("query", query);
	        request.setAttribute("numResultsToReturn", numResultsToReturn);
		  request.getRequestDispatcher("/searchInterface.jsp").forward(request, response);

		out.flush();
        out.close();
    }
}
