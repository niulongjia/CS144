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



public class ItemServlet extends HttpServlet implements Servlet 
{
    static final String columnSeparator = "|*|";
    static int maxDescriptionLength = 4000;
    static int httpServerPort = 1448;
    static int httpSecureServerPort = 8443;
    
    public ItemServlet() {}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////// Based On MyParser.java///////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

   // static DocumentBuilder builder;
    /*   
    static class MyErrorHandler implements ErrorHandler 
    {
        
        public void warning(SAXParseException exception)
        throws SAXException 
        {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException 
        {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException 
        {
            exception.printStackTrace();
            System.out.println("There should be no errors in the supplied XML files.");
            System.exit(3);
        }
        
    }
    */
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)*/
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }
    static String getAttributeTextByTagNameNR(Element e, String tagName)
    {
    	org.w3c.dom.NamedNodeMap e_Attr = e.getAttributes();
    	for (int p=0;p<e_Attr.getLength();p++)
    	{
    		if (e_Attr.item(p).getNodeName().equals(tagName))
    			return e_Attr.item(p).getNodeValue();
    	}
    	return "";
    }
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) 
    {
    	if (money.length()==0) return "";
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    static String stripDateTime(String dateTime) 
    {
    	
        if (dateTime.length()==0)
            return "";
        else 
        {
            //TIMESTAMP has a range of '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC
        	Date myDate=new Date(dateTime);
        	try
        	{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String result=format.format(myDate);
            //System.out.println("Successfully parsed!");
            return result;
        	}
        	catch(Exception e)
        	{
        		System.out.println("Error Formatting!");
        		return dateTime;
        	}
        }

    }
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////// Based On MyParser.java///////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here

        try 
        {
        	HttpSession session = request.getSession(true);
        	response.setContentType("text/html");
        	PrintWriter out = response.getWriter();
        	
        	AuctionSearchClient asc = new AuctionSearchClient();
        	String itemId=request.getParameter("ItemID");
        	
            if (itemId == null || itemId.length()==0) 
            {
                request.getRequestDispatcher("/EmptyItemidItemInterface.jsp").forward(request, response);
		        out.flush();
		    	out.close();
                return;
            }
            
        	String XML_Data = asc.getXMLDataForItemId(itemId);
        	//out.println(XML_Data.length());
        	int XMLisEmpty = 0;
        	
        	
        	
            // Can not find Item ID
            if (XML_Data == null || XML_Data.length()==0) 
            {
            	//out.println("XML_Data is empty!");
            	XMLisEmpty = 1;
                request.setAttribute("IR",new ItemResult());
                request.setAttribute("XMLisEmpty", XMLisEmpty);
                request.setAttribute("Bid_Result_Array",new BidResult[0]);
                request.getRequestDispatcher("/EmptyItemInterface.jsp").forward(request, response);
		        out.flush();
		    	out.close();
                return;
            }
          
            //out.println("XML_Data is valid!");
        	
        	// Set up DocumentBuilderFactory
    	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           factory.setValidating(false);
           factory.setIgnoringElementContentWhitespace(true);      
           DocumentBuilder builder = factory.newDocumentBuilder();
           // builder.setErrorHandler(new MyErrorHandler());
         
           StringReader reader = new StringReader(XML_Data);
           Document doc = builder.parse(new InputSource(reader));
         
	          // Element Item = getElementByTagNameNR( doc.getDocumentElement(), "Item");// The layer of Items
	            Element Item = doc.getDocumentElement(); // The layer of Item.
		        // Start loading data. 
		        String ItemID="";
		     	String Name="";
		     	Element[] Category_List;
		     	String Currently="";
		     	String Buy_Price="";
		     	String First_Bid="";
		     	String Number_of_Bids="";
		     	String Seller_Country="";
		     	String Seller_Location="";
		     	String Stripped_Seller_Country="";
		     	String Stripped_Seller_Location="";
		     	String Seller_Latitude="";
		     	String Seller_Longitude="";

		     	String Started="";
		     	String Ends="";
		     	String Seller_UserID="";
		    	String Seller_Rating="";
		     	String Description="";
		     	
		     	String Bidder_UserID="";
		    	String Bidder_Rating="";
		    	String Bidder_Location="";
		    	String Bidder_Country="";
		    	String Time="";
		    	String Amount="";
		     	//out.println("Name:"+Name);

		    	ItemID = getAttributeTextByTagNameNR(Item, "ItemID");
		    	Name = getElementTextByTagNameNR(Item, "Name"); //Name=Name.replaceAll("\"", "`"); //replace " to ` to avoid mismatch in MySQL		
		    	Currently = strip(getElementTextByTagNameNR(Item, "Currently"));		
		    	Buy_Price = strip(getElementTextByTagNameNR(Item, "Buy_Price"));		    	 		
		    	First_Bid = strip(getElementTextByTagNameNR(Item, "First_Bid"));		    	 
		    	Number_of_Bids = getElementTextByTagNameNR(Item, "Number_of_Bids");	    	  
		    	Seller_Location = getElementTextByTagNameNR(Item, "Location");//Seller_Location=Seller_Location.replaceAll("\"", "`"); //replace " to ` to avoid mismatch in MySQL
		    	Stripped_Seller_Location = Seller_Location.replace("\"", "");
		    	Stripped_Seller_Location = Stripped_Seller_Location.replace("'", "");
		    	
		    	//Get Seller_Latitude and Seller_Longitude below
		    	Element SL = getElementByTagNameNR(Item, "Location");
		    	Seller_Latitude = getAttributeTextByTagNameNR(SL, "Latitude");
		    	Seller_Longitude = getAttributeTextByTagNameNR(SL, "Longitude");
		
		    	Seller_Country = getElementTextByTagNameNR(Item, "Country"); 	
		    	Stripped_Seller_Country = Seller_Country.replace("\"", "");
		    	Stripped_Seller_Country = Stripped_Seller_Country.replace("'", "");
		    	
		    	Started = stripDateTime(getElementTextByTagNameNR(Item, "Started"));		
		    	Ends = stripDateTime(getElementTextByTagNameNR(Item, "Ends"));
		    	//Seller_UserID 
		    	Element S = getElementByTagNameNR(Item, "Seller");
		    	Seller_UserID = getAttributeTextByTagNameNR(S,"UserID");
		    	Seller_Rating = getAttributeTextByTagNameNR(S,"Rating");
		    	
		    	Description = getElementTextByTagNameNR(Item, "Description"); //Description=Description.replaceAll("\"", "`"); Description=truncate(Description);
		        if (Description.length() > maxDescriptionLength)
		            Description = Description.substring(0, maxDescriptionLength); // truncate string to 4000 chars
		        
		    	Category_List = getElementsByTagNameNR(Item, "Category");
		        ArrayList<String> Category_Content = new ArrayList<String>();
		        for (Element category : Category_List) 
		        {
		        	Category_Content.add(category.getTextContent());
		        }
		        String[] Category_Content_Array = new String[Category_Content.size()];
		        Category_Content_Array = Category_Content.toArray(Category_Content_Array);
		        
		    	ItemResult IR = new ItemResult(  ItemID, Name, Category_Content_Array, Currently,Buy_Price, First_Bid, Number_of_Bids, 
		    									 Seller_Country, Seller_Location,   Stripped_Seller_Country, Stripped_Seller_Location, 
		    									 Seller_Latitude, Seller_Longitude,  
	    		          						 Started, Ends,  Seller_UserID,  Seller_Rating,  Description) ;

		    	// Bids
		    	Element Bids = getElementByTagNameNR(Item, "Bids");
		    	Element []Bid_Array = getElementsByTagNameNR(Bids, "Bid");
		    	
		    	 ArrayList<BidResult> Bid_Result = new ArrayList<BidResult>();
		    	for (int i=0;i<Bid_Array.length;i++)
		    	{
		    		
		    		Time = stripDateTime(getElementTextByTagNameNR(Bid_Array[i], "Time")); 		
		    		Amount = strip(getElementTextByTagNameNR(Bid_Array[i], "Amount")); 
		    		
		    		Element Bidder = getElementByTagNameNR(Bid_Array[i], "Bidder");
		    		Bidder_UserID = getAttributeTextByTagNameNR(Bidder,"UserID");
		    		Bidder_Rating = getAttributeTextByTagNameNR(Bidder,"Rating");
		    			    		  
		    		Bidder_Location = getElementTextByTagNameNR(Bidder, "Location"); 		    		 
		    		Bidder_Country = getElementTextByTagNameNR(Bidder, "Country"); 
		   		    
		    		Bid_Result.add(new BidResult(Bidder_UserID, Bidder_Rating, Time, Amount,  Bidder_Location, Bidder_Country));
		    	}
		    	Collections.sort(Bid_Result);
		        BidResult[] Bid_Result_Array = new BidResult[Bid_Result.size()];
		        Bid_Result_Array = Bid_Result.toArray(Bid_Result_Array);
		        
		    	
		        request.setAttribute("IR",IR);
		        request.setAttribute("Bid_Result_Array",Bid_Result_Array);
		        request.setAttribute("XMLisEmpty", XMLisEmpty);
		        //ItemID, Item Name and Buy_Price 
		        
		        session.setAttribute("ItemID",ItemID);
		        session.setAttribute("Name",Name);
		        session.setAttribute("Buy_Price",Buy_Price);
		        session.setAttribute("httpServerPort",httpServerPort); // 1448
		        session.setAttribute("httpSecureServerPort",httpSecureServerPort); // 8443
		        request.getRequestDispatcher("/itemInterface.jsp").forward(request, response);
		        

		        out.flush();
		    	out.close();

        }
        catch(Exception e)
        {
        	
        	System.exit(0);
        }

         
    }
}
