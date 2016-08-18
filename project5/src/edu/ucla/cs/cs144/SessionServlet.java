package edu.ucla.cs.cs144;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Enumeration;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.w3c.dom.*;



public class SessionServlet extends HttpServlet implements Servlet 
{
   
    static int httpServerPort = 1448;
    static int httpSecureServerPort = 8443;
    
    public SessionServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here

        try 
        {
            // Open the existing session
            HttpSession session = request.getSession();
            
        	response.setContentType("text/html");
        	// Get credit card information
            String CreditCardNum = request.getParameter("CreditCardNum");
            if (CreditCardNum==null || CreditCardNum.length()!=16) 
            	{
            	request.getRequestDispatcher("/invalidCreditCard.jsp").forward(request, response);
            	return;
            	}
            char[] CreditCardNumHideChar = CreditCardNum.toCharArray();
            for (int i=0;i<16;i++)
            {
            	if (i<4 || i>11) continue;
            	CreditCardNumHideChar[i]='*';
            }
            String CreditCardNumHide=String.valueOf(CreditCardNumHideChar);
            session.setAttribute("CreditCardNum", CreditCardNum);
            session.setAttribute("CreditCardNumHide", CreditCardNumHide);
            request.getRequestDispatcher("/creditCardConfirmation.jsp").forward(request, response);

        }
        catch(Exception e)
        {
        	
        	System.exit(0);
        }

         
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	 doGet(request, response);
    }
}
