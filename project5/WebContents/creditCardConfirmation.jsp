<%@ page import = "edu.ucla.cs.cs144.*" %>
<%@ page import = "java.util.*" %>
<% 
   String ItemID = (String) session.getAttribute("ItemID");
   String Name = (String) session.getAttribute("Name");
   String Buy_Price = (String) session.getAttribute("Buy_Price");
   String CreditCardNum = (String) session.getAttribute("CreditCardNum");
   String CreditCardNumHide = (String) session.getAttribute("CreditCardNumHide");
   String serverName = (String) request.getServerName();
   int serverPort = (Integer) request.getServerPort();
   String getContextPath = (String) request.getContextPath();
   boolean isSecure = (boolean) request.isSecure();
   
   int httpServerPort = (Integer) session.getAttribute("httpServerPort");
   int httpSecureServerPort = (Integer) session.getAttribute("httpSecureServerPort");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Project 5 eBay Payment Confirmation</title>
		<link rel="stylesheet" type="text/css" href="css/autosuggest.css" />  
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> 
		<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css" />  		
		<link rel="stylesheet" type="text/css" href="css/interface.css" />
    </head>
    <body>

        <div class= "PagePart">    
			<div class="col-md-12 text-center">
			<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/index.html"><button class="button">ebay Search Home</button></a>
			&nbsp;&nbsp;&nbsp;
			<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/keywordSearch.html"><button class="button">ebay Keyword Search</button></a>
			&nbsp;&nbsp;&nbsp;
			<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/getItem.html"><button class="button">ebay ItemID Search</button></a>
			</div>
			<br>
			

			<div class="col-md-12 eBayIcon_rs text-center">
			    <span class="e" >e</span><!--
				--><span class="b">b</span><!--
				--><span class="a">a</span><!--
				--><span class="y">y</span><!--
				--><span>&nbsp;&nbsp;Payment&nbsp;&nbsp;Confirmation</span>								
			</div>			

			<br>
			
			<div class="col-md-6 col-md-offset-3">
				<br>
				<br>
				<br>
				<br>
				<table class="table table-hover">
					<tr>
						<td style=" font-weight: bold;">Tags</td>
						<th scope="col">&nbsp;Tag Information</th>
					</tr>
					<tr>
						<th scope="row">ItemID</th>
						<td>&nbsp;<%=ItemID%></td>
					</tr>
					<tr>
						<th scope="row">Item Name</th>
						<td>&nbsp;<%=Name%></td>
					</tr>
					<tr>
						<th scope="row">Buy_Price</th>
						<td>&nbsp;<%=Buy_Price%></td>
					</tr>
					<tr>
						<th scope="row">Credit Card Number:</th>
						<td>&nbsp;<%=CreditCardNumHide%></td>
					</tr>
					<tr>
						<th scope="row">Credit Card Number:</th>
						<td>&nbsp;<%=new Date(session.getLastAccessedTime())%></td>
					</tr>
				</table>

				
			</div>


        </div>
    </body>
</html>