package edu.ucla.cs.cs144;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class ItemResult {

    private String ItemID="";
    private String Name="";
    private String[] Category_Content_Array;
    private String Currently="";
    private String Buy_Price="";
    private String First_Bid="";
    private String Number_of_Bids="";
    private String Seller_Country="";
    private String Seller_Location="";
    private String Stripped_Seller_Country="";
    private String Stripped_Seller_Location="";
    private String Seller_Latitude="";
    private String Seller_Longitude="";
    private String Started="";
    private String Ends="";
    private String Seller_UserID="";
    private String Seller_Rating="";
    private String Description="";

    public ItemResult() {}

    public ItemResult(String ItemID, String Name, String[] Category_Content_Array, String Currently, String Buy_Price, String First_Bid, String Number_of_Bids, 
    				  String Seller_Country,String Seller_Location,String Stripped_Seller_Country,String Stripped_Seller_Location,
    				  String Seller_Latitude, String Seller_Longitude,  
                      String Started, String Ends, String Seller_UserID, String Seller_Rating, String Description) 
    {
        this.ItemID=ItemID;
        this.Name=Name;
        this.Category_Content_Array = Category_Content_Array;
        this.Currently=Currently;
        this.Buy_Price=Buy_Price;
        this.First_Bid=First_Bid;
        this.Number_of_Bids=Number_of_Bids;
        this.Seller_Country=Seller_Country;
        this.Seller_Location=Seller_Location;
        this.Stripped_Seller_Country=Stripped_Seller_Country;
        this.Stripped_Seller_Location=Stripped_Seller_Location;
        this.Seller_Latitude=Seller_Latitude;
        this.Seller_Longitude=Seller_Longitude;
        this.Started=Started;
        this.Ends=Ends;
        this.Seller_UserID=Seller_UserID;
        this.Seller_Rating=Seller_Rating;
        this.Description=Description;
    }

    public String[] getCategories() {
        return this.Category_Content_Array;
    }

    public String getItemID() {
        return this.ItemID;
    }

    public String getName() {
        return this.Name;
    }

    public String getCurrently() {
        return this.Currently;
    }

    public String getFirstBid() {
        return this.First_Bid;
    }

    public String getBuyPrice() {
        return this.Buy_Price;
    }

    public String getNumBids() {
        return this.Number_of_Bids;
    }

    public String getLocation() {
        return this.Seller_Location;
    }

    public String getCountry() {
        return this.Seller_Country;
    }

    public String getStrippedLocation() {
        return this.Stripped_Seller_Location;
    }

    public String getStrippedCountry() {
        return this.Stripped_Seller_Country;
    }
    
    public String getStarted() {
        return this.Started;
    }

    public String getEnds() {
        return this.Ends;
    }

    public String getLongitude() {
        return this.Seller_Longitude;
    }

    public String getLatitude() {
        return this.Seller_Latitude;
    }

    public String getSellerID() {
        return this.Seller_UserID;
    }

    public String getRating() {
        return this.Seller_Rating;
    }

    public String getDescription() {
        return this.Description;
    }

}