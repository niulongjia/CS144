#!/bin/bash
chmod +x runLoad.sh
# Run the drop.sql batch file to drop existing tables
# Inside the drop.sql, you sould check whether the table exists. Drop them ONLY if they exists.
mysql CS144 < drop.sql

# Run the create.sql batch file to create the database and tables
mysql CS144 < create.sql

# Compile and run the parser to generate the appropriate load files
ant run-all

# If the Java code does not handle duplicate removal, do this now
sort Item.dat | uniq > Item_uniq.dat
sort ItemCategory.dat | uniq > ItemCategory_uniq.dat
sort Bid.dat | uniq > Bid_uniq.dat
sort Bidder.dat | uniq > Bidder_uniq.dat
sort Seller.dat | uniq > Seller_uniq.dat

# Run the load.sql batch file to load the data
mysql CS144 < load.sql

# Remove all temporary files

rm Item.dat 
rm Item_uniq.dat
rm ItemCategory.dat 
rm ItemCategory_uniq.dat
rm Bid.dat 
rm Bid_uniq.dat
rm Bidder.dat 
rm Bidder_uniq.dat
rm Seller.dat 
rm Seller_uniq.dat




