Part B:
1. Relational Schema Design



Item(ItemID,Name,Currently,Buy_Price,First_Bid,Number_of_Bids,Seller_Location,
Seller_Latitude,Seller_Longitude,Seller_Country,Started,Ends,Seller_UserID,Description)

---ItemID is the primary key.



ItemCategory(ItemID,Category)

---(ItemID,Category) is the primary key.



Bid(ItemID,Bidder_UserID,Time,Amount)

---(ItemID,Bidder_UserID,Time) is the primary key.



Bidder(Bidder_UserID,Bidder_Rating,Bidder_Location,Bidder_Country)

---Bidder_UserID is the primary key.



Seller(Seller_UserID,Seller_Rating)

---Seller_UserID is the primary key.



2.List all completely nontrivial functional dependencies that hold on each relation, excluding those that effectively specify keys.

For the relation Item, the completely nontrivial functional dependencies are:
ItemID -> Name, Currently, Buy_Price,First_Bid, Number_of_Bids, Bids, Location, Country, Started, Ends, Seller.UserID, Description

For the relation Bid, the completely nontrivial functional dependencies are:
ItemID, UserID,Time-> Amount

For the relation User, the completely nontrivial functional dependencies are:
UserID->Rating, Location, Country

3.why you feel it is advantageous to use non-BCNF relations.

No redundancy from FD.



4.why you feel it is advantageous to use non-4NF relations.

No redundancy from FD and MVD.

