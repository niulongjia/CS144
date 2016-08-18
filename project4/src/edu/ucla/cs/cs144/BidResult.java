package edu.ucla.cs.cs144;

public class BidResult implements Comparable<BidResult> 
{
    private String Bidder_UserID="";
    private String Bidder_Rating="";
    private String Bidder_Location="";
    private String Bidder_Country="";
    private String Time="";
    private String Amount="";

    public BidResult() {}
    
    public int compareTo(BidResult other) 
    {
        // Descending order
        return other.getTime().compareTo(this.getTime());
    }

    public BidResult(String id, String rating, String time, String amount, String location, String country)
    {
        this.Bidder_UserID = id;
        this.Bidder_Rating = rating;
        this.Time = time;
        this.Amount = amount;
        this.Bidder_Location = location;
        this.Bidder_Country = country;
    }
    public String getBidder_UserID() {
        return Bidder_UserID;
    }

    public String getBidder_Rating() {
        return Bidder_Rating;
    }

    public String getBidder_Location() {
        return Bidder_Location;
    }

    public String getBidder_Country() {
        return Bidder_Country;
    }

    public String getTime() {
        return Time;
    }

    public String getAmount() {
        return Amount;
    }
}