<%@ page import = "edu.ucla.cs.cs144.*" %>
<%@ page import = "java.util.*" %>
<% 
   SearchResult[] SR = (SearchResult[])request.getAttribute("SR");
   SearchResult[] SR_NextPage = (SearchResult[])request.getAttribute("SR_NextPage");
   String query = (String) request.getAttribute("query");
   int numResultsToSkip = (Integer)request.getAttribute("numResultsToSkip");
   int numResultsToReturn = (Integer)request.getAttribute("numResultsToReturn");
   int totalSum = (Integer)request.getAttribute("totalSum");
%>

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
        <div class= "PagePart">    
			<div class="col-md-12 text-center">
			<a href="index.html"><button class="button">ebay Search Home</button></a>
			&nbsp;&nbsp;&nbsp;
			<a href="keywordSearch.html"><button class="button">ebay Keyword Search</button></a>
			&nbsp;&nbsp;&nbsp;
			<a href="getItem.html"><button class="button">ebay ItemID Search</button></a>
			</div>
			<br>
			
			<div class="col-md-12">
			<div class="eBayIcon_rs text-center">
			    <span class="e" >e</span><!--
				--><span class="b">b</span><!--
				--><span class="a">a</span><!--
				--><span class="y">y</span><!--
				--><span>&nbsp;&nbsp;Keyword&nbsp;&nbsp;Search</span>								
			</div>
			<br>
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

			<div class="col-md-6 col-md-offset-3">
					<br>
					<br>
					<br>
					<br>
				<table class="table table-hover">
					<thead>
					  <tr>
						<th>Item ID</th>
						<th>Item Name</th>
					  </tr>
					</thead>
					<tbody>
										  
				<% for (int i=0;i<SR.length;i++)  
				{ 
				%>
					<tr>
					<td><a href="/eBay/item?ItemID=<%=SR[i].getItemId()%>"> <%=SR[i].getItemId()%>  </a> </td>
					<td><a href="/eBay/item?ItemID=<%=SR[i].getItemId()%>"> <%=SR[i].getName()%> </a> </td>
					</tr>
				<%
				}
				%>
					  
					</tbody>
				</table>
				
				<%
				if (numResultsToSkip-numResultsToReturn >=0)
				{
				%>
				<a id="Previous Page" href="search?q=<%=query%>&numResultsToSkip=<%=numResultsToSkip-numResultsToReturn%>&numResultsToReturn=<%=numResultsToReturn%>">
						Previous Page</a>
				<%
				}
				%>
				
				<%
				if (numResultsToSkip+numResultsToReturn < totalSum)
				{
				%>
				<a id="Next Page" href="search?q=<%=query%>&numResultsToSkip=<%=numResultsToSkip+numResultsToReturn%>&numResultsToReturn=<%=numResultsToReturn%>">
							Next Page</a>
				<%
				}
				%>
				<%int pageStart = numResultsToSkip+1;%>
				<%int pageEnd = numResultsToSkip+numResultsToReturn>totalSum ? totalSum: numResultsToSkip+numResultsToReturn;%>
				<p> Item Returned: <%=pageStart%> ~ <%=pageEnd%> / <%=totalSum%> </p>
			</div>


        </div>
    </body>
</html>