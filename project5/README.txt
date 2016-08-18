Q1: For which communication(s) do you use the SSL encryption? 
(4)->(5) && (5)->(6)

Q2: How do you ensure that the item was purchased exactly at the Buy_Price of that particular item?
ItemID, Name and Buy_Price (If exists) are related together and retrieved from HttpSession, and the 
information comes directly from oak server, and HttpSession carries information on our Tomcat server,
the information is never sent to client for change. Thus Buy_Price is exactly that of the particular item.
