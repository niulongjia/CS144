/*
Item
(ItemID,Name,Currently,Buy_Price,First_Bid,Number_of_Bids,Seller_Location,Seller_Latitude,Seller_Longitude,Seller_Country,Started,Ends,Seller_UserID,Description)...1
*/
CREATE TABLE Item
(
ItemID VARCHAR(500) PRIMARY KEY NOT NULL,
Name VARCHAR(500),
Currently DECIMAL(8,2),
Buy_Price DECIMAL(8,2),
First_Bid DECIMAL(8,2),
Number_of_Bids INTEGER,
Seller_Location VARCHAR(500),
Seller_Latitude VARCHAR(500),
Seller_Longitude VARCHAR(500),
Seller_Country VARCHAR(500),
Started TIMESTAMP,
Ends TIMESTAMP,
Seller_UserID VARCHAR(500),
Description VARCHAR(4000)
);
/*
ItemCategory(ItemID,Category)
*/
CREATE TABLE ItemCategory
(
ItemID VARCHAR(500) NOT NULL,
Category VARCHAR(500) NOT NULL,
PRIMARY KEY(ItemID,Category)
);
/*
Bid(ItemID,Bidder_UserID,Time,Amount)
*/
CREATE TABLE Bid
(
ItemID VARCHAR(500) NOT NULL,
Bidder_UserID VARCHAR(500) NOT NULL,
Time TIMESTAMP NOT NULL,
Amount DECIMAL(8,2),
PRIMARY KEY(ItemID,Bidder_UserID,Time)
);
/*
Bidder(Bidder_UserID,Bidder_Rating,Bidder_Location,Bidder_Country)
*/
CREATE TABLE Bidder
(
Bidder_UserID VARCHAR(500) PRIMARY KEY NOT NULL,
Bidder_Rating INTEGER,
Bidder_Location VARCHAR(500),
Bidder_Country VARCHAR(500)
);
/*
Seller(Seller_UserID,Seller_Rating)
*/
CREATE TABLE Seller
(
Seller_UserID VARCHAR(500) PRIMARY KEY NOT NULL,
Seller_Rating INTEGER
);
