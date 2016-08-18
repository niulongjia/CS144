<!DOCTYPE html>
<html> 
<head>
	<title>Project 4 Keyword Search Results</title>
	<script type="text/javascript" src="js/autosuggest.js"></script>
	<script type="text/javascript" src="js/suggestions.js"></script>
	<link rel="stylesheet" type="text/css" href="css/autosuggest.css" />  
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> 
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css" />  		
	<link rel="stylesheet" type="text/css" href="css/interface.css" />
	<script type="text/javascript">
		window.onload = function () 
		{
			//console.log("@Debug");
			var oTextbox = new AutoSuggestControl(document.getElementById("query"), new KeyWordSuggestions());        
		}
	</script>
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
			--><span>&nbsp;&nbsp;Keyword&nbsp;&nbsp;Search</span>								
		</div>
		<div class="col-md-6 col-md-offset-3 text-center">
			<form role="form" class="FormContent" action="/eBay/search" method="GET">
				<input class="btn btn-default btn-md" name="submit" type="submit" value="Go!" style="float: right"/>
				<div class="col-md-11 form-group" style="overflow: hidden;">
					<input class="form-control" name="q" type="text" id="query" placeholder="Enter Keywords :)"/>    		
					<input name="numResultsToSkip" type="hidden" value="0" />
					<input name="numResultsToReturn" type="hidden" value="20" />			
				</div>					
			</form>
		</div>

		<div class="col-md-12 text-center">
				<br>
				<br>
				<br>
				<br>
			 <h3> We can not find items based on your Keyword search.</h3>	
			 <h3> Please try again :)</h3>		
		</div>
	</div>
	
</body>
</html>