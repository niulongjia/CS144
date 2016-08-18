<!DOCTYPE html>
<html> 
<head>
    <title>Project 4 Get Item</title>
	<link rel="stylesheet" type="text/css" href="css/autosuggest.css" />  
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> 
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css" />  		
	<link rel="stylesheet" type="text/css" href="css/interface.css" />
</head>
<body>
    <div class="PagePart">
		<div class="col-md-12 text-center">
		<a href="index.html"><button class="button">ebay Search Home</button></a>
		&nbsp;&nbsp;&nbsp;
		<a href="keywordSearch.html"><button class="button">ebay Keyword Search</button></a>
		&nbsp;&nbsp;&nbsp;
		<a href="getItem.html"><button class="button">ebay ItemID Search</button></a>
		</div>
		<br>
         <div class="eBayIcon col-md-12 text-center">
			<span class="e" >e</span><!--
			--><span class="b">b</span><!--
			--><span class="a">a</span><!--
			--><span class="y">y</span><!--
			--><span>&nbsp;&nbsp;Item ID&nbsp;&nbsp;Search</span>								
		</div>
		<br>
		<div class="col-md-6 col-md-offset-3 text-center">
			<form role="form" class="FormContent" action="/eBay/item" method="GET">
				<input class="btn btn-default btn-md" name="submit" type="submit" value="Go!" style="float: right"/>
				
				<div class="col-md-11 form-group" style="overflow: hidden;">
					<input class="form-control" name="ItemID" type="text" placeholder="Enter Item ID :)"/>    		
				</div>

				<br/>						
			</form>
		</div>
		<div class="col-md-12 text-center">
			 <h3> We can not find items based on your ID search.</h3>	
			 <h3> Please try again :)</h3>		
		</div>
	</div>
	
</body>
</html>