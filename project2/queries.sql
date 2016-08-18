-- Take Speical Care of CR/LF Issue!

-- question 1
-- Another version of p1
/*
SELECT COUNT(*)
FROM
(SELECT Bidder.Bidder_UserID AS UserID FROM Bidder LEFT JOIN Seller ON Bidder.Bidder_UserID=Seller.Seller_UserID
UNION
SELECT Seller.Seller_UserID AS UserID FROM Bidder RIGHT JOIN Seller ON Bidder.Bidder_UserID=Seller.Seller_UserID)userid;
*/
SELECT COUNT(*)
FROM
(SELECT Bidder_UserID FROM Bidder AS UserID 
UNION
SELECT Seller_UserID  FROM Seller AS UserID) AS userid;-- Must use alias such as userid


-- question 2
SELECT COUNT(*) FROM Item WHERE BINARY Seller_Location="New York";

-- question 3
SELECT COUNT(*) FROM
(
SELECT ItemID, COUNT(Category) AS NumberOfCategory
FROM ItemCategory
GROUP BY ItemID
) AS itemid
WHERE NumberOfCategory = 4;

-- question 4 (1046740686)
SELECT ItemID FROM
  (
    SELECT ItemID, Amount FROM
    (
    SELECT Item.ItemID,Item.Buy_Price,Item.Ends,Bid.Amount
    FROM Item INNER JOIN Bid
    ON Item.ItemID=Bid.ItemID
    )AS A -- After Merge
    WHERE Buy_Price IS NULL AND Ends>"2001-12-20 00:00:01" 
  )  AS B -- After Filter
  WHERE Amount=(SELECT MAX(Amount) 
                FROM 
                 (
                  SELECT ItemID, Amount FROM
                   (
					 SELECT Item.ItemID,Item.Buy_Price,Item.Ends,Bid.Amount
					 FROM Item INNER JOIN Bid
					 ON Item.ItemID=Bid.ItemID
				   )AS A -- After Merge
				   WHERE Buy_Price IS NULL AND Ends>"2001-12-20 00:00:01" 
				 ) AS B -- After Filter
				);



-- question 5 (3130)
SELECT COUNT(*) FROM Seller WHERE Seller_Rating>1000;

-- question 6 (6717)
SELECT COUNT(*)
FROM Bidder
JOIN Seller
ON Bidder.Bidder_UserID=Seller.Seller_UserID;

-- question 7 
-- Find the number of categories that include <at least one item with a bid of more than $100>.
        SELECT COUNT(*) FROM 
		(
		  SELECT DISTINCT Category 
		  FROM
		   (
		   SELECT DISTINCT Category, Amount
		   FROM ItemCategory JOIN Bid
		   ON ItemCategory.ItemID=Bid.ItemID
		   ) AS A
		   WHERE A.Amount IS NOT NULL AND A.Amount>100
		 ) AS B;

		  
		  /*
		  SELECT DISTINCT Category FROM
          (
          SELECT Category, COUNT(ItemID) AS NumberOfID
          FROM ItemCategory
          GROUP BY Category
          ) AS C -- Category satisfying cond_1
          WHERE NumberOfID>=1;
		  */
		  
		 /* SELECT DISTINCT Category 
	      FROM ItemCategory JOIN
	      (
		    SELECT ItemID
	        FROM Item
	        WHERE Currently IS NOT NULL AND Currently>100
		  ) AS A -- ItemID satisfying cond_2
		    ON A.ItemID=ItemCategory.ItemID;	*/
			
	 /*
	 SELECT B.Category
	 FROM 
	     (
		   SELECT DISTINCT Category FROM
          (
          SELECT Category, COUNT(ItemID) AS NumberOfID
          FROM ItemCategory
          GROUP BY Category
          ) AS C -- Category satisfying cond_1
          WHERE NumberOfID>=1
	    ) AS D
	 JOIN 
	    (
		  SELECT DISTINCT Category 
	      FROM ItemCategory JOIN
	      (
		    SELECT ItemID
	        FROM Item
	        WHERE Currently IS NOT NULL AND Currently>100
		  ) AS A -- ItemID satisfying cond_2
		    ON A.ItemID=ItemCategory.ItemID	  
	    ) AS B -- Category satisfying cond_2 
	 ON B.Category=D.Category;
	 */

	 




