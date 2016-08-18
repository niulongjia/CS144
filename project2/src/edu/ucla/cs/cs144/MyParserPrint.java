/* CS144
 *
 * Parser skeleton for processing item-???.xml files. Must be compiled in
 * JDK 1.5 or above.
 *
 * Instructions:
 *
 * This program processes all files passed on the command line (to parse
 * an entire diectory, type "java MyParser myFiles/*.xml" at the shell).
 *
 * At the point noted below, an individual XML file has been parsed into a
 * DOM Document node. You should fill in code to process the node. Java's
 * interface for the Document Object Model (DOM) is in package
 * org.w3c.dom. The documentation is available online at
 *
 * http://java.sun.com/j2se/1.5.0/docs/api/index.html
 *
 * A tutorial of Java's XML Parsing can be found at:
 *
 * http://java.sun.com/webservices/jaxp/
 *
 * Some auxiliary methods have been written for you. You may find them
 * useful.
 */

package edu.ucla.cs.cs144;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

//import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference.Elements;

import org.xml.sax.ErrorHandler;
import org.w3c.dom.*;


class MyParserPrint {
    
    static final String columnSeparator = "|*|";
    static DocumentBuilder builder;
    
    static final String[] typeName = {
	"none",
	"Element",
	"Attr",
	"Text",
	"CDATA",
	"EntityRef",
	"Entity",
	"ProcInstr",
	"Comment",
	"Document",
	"DocType",
	"DocFragment",
	"Notation",
    };
    
    static class MyErrorHandler implements ErrorHandler {
        
        public void warning(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException {
            exception.printStackTrace();
            System.out.println("There should be no errors " +
                               "in the supplied XML files.");
            System.exit(3);
        }
        
    }
    
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
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
    
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) 
    {
    	if (money.equals("NULL")) return "NULL";
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    static String stripDateTime(String dateTime) 
    {
    	
        if (dateTime.equals("NULL"))
            return "NULL";
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
    
    static String truncate(String text) 
    {
    	int length=3998;
        if (text.length()<length)
            return text;
        else
        	return text.substring(0, length);

    }
    
    /* Process one items-???.xml file.
     */
    static void processFile(File xmlFile) throws IOException//Always remember to throw Exception 
    {
        Document doc = null;
        try {
            doc = builder.parse(xmlFile);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        catch (SAXException e) {
            System.out.println("Parsing error on file " + xmlFile);
            System.out.println("  (not supposed to happen with supplied XML files)");
            e.printStackTrace();
            System.exit(3);
        }
        
        /* At this point 'doc' contains a DOM representation of an 'Items' XML
         * file. Use doc.getDocumentElement() to get the root Element. */
        System.out.println("Successfully parsed - " + xmlFile);
        
        /* Fill in code here (you will probably need to write auxiliary
            methods). */
        // Create each file for each table
        String f1="Item.dat";
        String f2="ItemCategory.dat";
        String f3="Bid.dat";
        String f4="Bidder.dat";
        String f5="Seller.dat";
        BufferedWriter out1=new BufferedWriter(new FileWriter(f1,true)); 
        BufferedWriter out2=new BufferedWriter(new FileWriter(f2,true)); 
        BufferedWriter out3=new BufferedWriter(new FileWriter(f3,true)); 
        BufferedWriter out4=new BufferedWriter(new FileWriter(f4,true)); 
        BufferedWriter out5=new BufferedWriter(new FileWriter(f5,true)); 

        Element []Item_List=getElementsByTagNameNR( (Element)doc.getFirstChild(), "Item");
        //System.out.println(Item_List.length);
        int count=0;
        
        for (int i=0;i<Item_List.length;i++)
        {
        	Element Item = Item_List[i];
/*
Below Construct Item(ItemID,Name,Currently,Buy_Price,First_Bid,Number_of_Bids,Seller_Location,Seller_Latitude,Seller_Longitude,Seller_Country,Started,Ends,Seller_UserID,Description)...1
Below Construct ItemCategory(ItemID,Category)...2   
*/ 	
        	String ItemID="";
        	String Name="";
        	String Currently="";
        	String Buy_Price="";
        	String First_Bid="";
        	String Number_of_Bids="";
        	String Seller_Location="";
        	String Seller_Latitude="";
        	String Seller_Longitude="";
        	String Seller_Country="";
        	String Started="";
        	String Ends="";
        	String Seller_UserID="";
        	String Description="";
        	
        	org.w3c.dom.NamedNodeMap ItemID_List = Item.getAttributes();
        	if (ItemID_List != null && ItemID_List.getLength()==1)
        	{
        		ItemID=ItemID_List.item(0).getNodeValue();
        	}
        	Name = getElementTextByTagNameNR(Item, "Name"); Name=Name.replaceAll("\"", "\\\\\""); //replace " to \" to avoid mismatch in MySQL
        	  if (Name=="") Name="NULL";
        	  else Name="\""+Name+"\"";
        	Currently = getElementTextByTagNameNR(Item, "Currently");
        	  if (Currently=="") Currently="NULL";
        	  //else Currently="\""+Currently+"\"";
        	Buy_Price = getElementTextByTagNameNR(Item, "Buy_Price");
        	  if (Buy_Price=="") Buy_Price="NULL";
        	  //else Buy_Price="\""+Buy_Price+"\"";
        	First_Bid = getElementTextByTagNameNR(Item, "First_Bid");
        	  if (First_Bid=="") First_Bid="NULL";
        	  //else First_Bid="\""+First_Bid+"\"";
        	Number_of_Bids = getElementTextByTagNameNR(Item, "Number_of_Bids");
        	  if (Number_of_Bids=="") Number_of_Bids="NULL";
        	  
        	Seller_Location = getElementTextByTagNameNR(Item, "Location");Seller_Location=Seller_Location.replaceAll("\"", "\\\\\""); //replace " to \" to avoid mismatch in MySQL
        	  if (Seller_Location=="") Seller_Location="NULL";
        	  else Seller_Location="\""+Seller_Location+"\"";
        	  
        	//Get Seller_Latitude and Seller_Longitude below
        	Element SL=getElementByTagNameNR(Item, "Location");
        	org.w3c.dom.NamedNodeMap SL_Attr = SL.getAttributes();
        	for (int p=0;p<SL_Attr.getLength();p++)
        	{
        		if (SL_Attr.item(p).getNodeName().equals("Latitude"))
        			Seller_Latitude = SL_Attr.item(p).getNodeValue();
        		if (SL_Attr.item(p).getNodeName().equals("Longitude"))
        			Seller_Longitude = SL_Attr.item(p).getNodeValue();
        	}
        	  if (Seller_Latitude=="") Seller_Latitude="NULL";
        	  if (Seller_Longitude=="") Seller_Longitude="NULL";
        	Seller_Country = getElementTextByTagNameNR(Item, "Country"); 
        	  if (Seller_Country=="") Seller_Country="NULL";
        	  else Seller_Country="\""+Seller_Country+"\"";
        	  
        	Started = getElementTextByTagNameNR(Item, "Started");
        	  if (Started=="") Started="NULL";
        	Ends = getElementTextByTagNameNR(Item, "Ends");
        	  if (Ends=="") Ends="NULL";
        	//Seller_UserID 
        	Element S = getElementByTagNameNR(Item, "Seller");
        	org.w3c.dom.NamedNodeMap S_Attr = S.getAttributes();
        	for (int p=0;p<S_Attr.getLength();p++)
        	{
        		if (S_Attr.item(p).getNodeName().equals("UserID"))
        			Seller_UserID = S_Attr.item(p).getNodeValue();
        	}
        	Seller_UserID=Seller_UserID.replaceAll("\"", "\\\\\"");//replace " to \" to avoid mismatch in MySQL
        	  if (Seller_UserID=="") Seller_UserID="NULL";
        	  else Seller_UserID="\""+Seller_UserID+"\"";
        	  
        	Description = getElementTextByTagNameNR(Item, "Description"); Description=Description.replaceAll("\"", "\\\\\""); Description=truncate(Description);
        	  if (Description=="") Description="NULL";
        	  else Description="\""+Description+"\"";
        	  
        	out1.write(ItemID+","+Name+","+strip(Currently)+","+strip(Buy_Price)+","+strip(First_Bid)+","+Number_of_Bids+","+
        	           Seller_Location +","+Seller_Latitude+","+Seller_Longitude+","+Seller_Country+","+
        			   stripDateTime(Started)+","+stripDateTime(Ends)+","+Seller_UserID+","+Description);

        	out1.newLine();
        	
        	
        	Element []Category_List=getElementsByTagNameNR(Item, "Category");
        	String Category="";
        	for (int j=0;j<Category_List.length;j++)
        	{
        		Category=Category_List[j].getFirstChild().getNodeValue(); 
        		  if (Category=="") Category="NULL";
        		  else Category="\""+Category+"\"";
        		out2.write(ItemID+","+Category);
        		out2.newLine();
        	}
/*
  Below Construct Bid(ItemID,Bidder_UserID,Time,Amount)...3
  Below Construct (Bidder_UserID,Bidder_Rating,Bidder_Location,Bidder_Country)...4
*/
        	String Bidder_UserID="";
        	String Bidder_Rating="";
        	String Bidder_Location="";
        	String Bidder_Country="";
        	String Time="";
        	String Amount="";
        	Element Bids = getElementByTagNameNR(Item, "Bids");
        	Element []Bid_List = getElementsByTagNameNR(Bids, "Bid");
        	for (int i4=0;i4<Bid_List.length;i4++)
        	{
        		Element Bidder = getElementByTagNameNR(Bid_List[i4], "Bidder");
        		Time = getElementTextByTagNameNR(Bid_List[i4], "Time"); 
        		  if (Time=="") Time="NULL";
        		Amount = getElementTextByTagNameNR(Bid_List[i4], "Amount"); 
        		  if (Amount=="") Amount="NULL";
        		org.w3c.dom.NamedNodeMap Bidder_Attr = Bidder.getAttributes(); 
        		for (int j4=0;j4<Bidder_Attr.getLength();j4++)
        		{
        			if (Bidder_Attr.item(j4).getNodeName().equals("UserID"))
        			    Bidder_UserID = Bidder_Attr.item(j4).getNodeValue();
        			if (Bidder_Attr.item(j4).getNodeName().equals("Rating"))
        			    Bidder_Rating = Bidder_Attr.item(j4).getNodeValue();
        		}
        		Bidder_UserID=Bidder_UserID.replaceAll("\"", "\\\\\"");//replace " to \" to avoid mismatch in MySQL
        		  //if (Bidder_UserID=="") Bidder_UserID="NULL";
        		  if (Bidder_UserID!="") Bidder_UserID="\""+Bidder_UserID+"\"";
        		  if (Bidder_Rating=="") Bidder_Rating="NULL";
        		  //else Bidder_Rating="\""+Bidder_Rating+"\"";
        		Bidder_Location = getElementTextByTagNameNR(Bidder, "Location"); 
        		  if (Bidder_Location=="") Bidder_Location="NULL";
        		  else Bidder_Location="\""+Bidder_Location+"\"";
        		Bidder_Country = getElementTextByTagNameNR(Bidder, "Country"); 
        		  if (Bidder_Country=="") Bidder_Country="NULL";
        		  else Bidder_Country="\""+Bidder_Country+"\"";
        		out3.write(ItemID+","+Bidder_UserID+","+stripDateTime(Time)+","+strip(Amount));
        		out3.newLine();
        		out4.write(Bidder_UserID+","+Bidder_Rating+","+Bidder_Location+","+Bidder_Country);
        		out4.newLine();
        		
        	}
        	
//Below Construct Seller(Seller_UserID,Seller_Rating)...5        	
        	String Seller_Rating="";
        	Element Seller=getElementByTagNameNR(Item, "Seller");
        	org.w3c.dom.NamedNodeMap Seller_Attr = Seller.getAttributes();
        	for (int i5=0;i5<Seller_Attr.getLength();i5++)
        	{
        		if (Seller_Attr.item(i5).getNodeName().equals("UserID"))
        			Seller_UserID = Seller_Attr.item(i5).getNodeValue();
        		if (Seller_Attr.item(i5).getNodeName().equals("Rating"))
        			Seller_Rating = Seller_Attr.item(i5).getNodeValue();
        	}
        	Seller_UserID=Seller_UserID.replaceAll("\"", "\\\\\"");
        	//if (Seller_UserID=="") Seller_UserID="NULL";
        	if (Seller_UserID!="") Seller_UserID="\""+Seller_UserID+"\"";
        	if (Seller_Rating=="") Seller_Rating="NULL";
        	//else Seller_Rating="\""+Seller_UserID+"\"";
        		out5.write(Seller_UserID+","+Seller_Rating);
        		out5.newLine();
        	
        	
        }
        out1.flush();//Flush the memory.
        out1.close();//Close the stream
        out2.flush();
        out2.close();
        out3.flush();
        out3.close();
        out5.flush();
        out5.close();
        out4.flush();
        out4.close();
        
        /**************************************************************/
        



        //recursiveDescent(doc, 0);

    }
    
    public static void recursiveDescent(Node n, int level) {
        // adjust indentation according to level
        for(int i=0; i<4*level; i++)
            System.out.print(" ");
        
        // dump out node name, type, and value  
        String ntype = typeName[n.getNodeType()];
        String nname = n.getNodeName();
        String nvalue = n.getNodeValue();
        
        System.out.println("Type = " + ntype + ", Name = " + nname + ", Value = " + nvalue);
        
        // dump out attributes if any
        org.w3c.dom.NamedNodeMap nattrib = n.getAttributes();
        if(nattrib != null && nattrib.getLength() > 0)
            for(int i=0; i<nattrib.getLength(); i++)
                recursiveDescent(nattrib.item(i),  level+1);
        
        // now walk through its children list
        org.w3c.dom.NodeList nlist = n.getChildNodes();
        
        for(int i=0; i<nlist.getLength(); i++)
            recursiveDescent(nlist.item(i), level+1);
    }  
    
    public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MyParser [file] [file] ...");
            System.exit(1);
        }
        
        
        /* Initialize parser. */
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);      
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
        }
        catch (FactoryConfigurationError e) {
            System.out.println("unable to get a document builder factory");
            System.exit(2);
        } 
        catch (ParserConfigurationException e) {
            System.out.println("parser was unable to be configured");
            System.exit(2);
        }
        try {
        /* Process all files listed on command line. */
          for (int i = 0; i < args.length; i++) 
          {
            File currentFile = new File(args[i]);
            processFile(currentFile);
          }
        }
        catch(IOException e)
        {
        	System.exit(3);
        }
    }
}
