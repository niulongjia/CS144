<%@ page import = "edu.ucla.cs.cs144.*" %> 
<%@ page import = "java.util.*" %> 
<%
		 ItemResult IR = (ItemResult) request.getAttribute("IR");
		 BidResult[] Bid_r = (BidResult[])request.getAttribute("Bid_Result_Array");
		 String[] Category_r = IR.getCategories(); 
		 int XMLisEmpty = (Integer) request.getAttribute("XMLisEmpty");
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
    <title>Project 5 Get Item</title>
	<link rel="stylesheet" type="text/css" href="css/autosuggest.css" />  
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> 
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css" />  		
	<link rel="stylesheet" type="text/css" href="css/interface.css" />
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDfEBaQTRX8HoobM9tfBYvqS_MOn_xR5Qs"
    async defer></script>
	<script type="text/javascript">
        function isEmpty(s) 
		{
			return (!s || 0 === s.length);
		}
		function initialize() 
		{
			   var geocoder;
			   var latlng; 
			   var mapOptions;
			   var map;
			   var address;
			   
				geocoder = new google.maps.Geocoder();				
				mapOptions = 
					{
					  zoom: 8,
					  center: new google.maps.LatLng(45.0, 45.0),
					  mapTypeId: google.maps.MapTypeId.ROADMAP
					};
				map = new google.maps.Map(document.getElementById("map"), mapOptions);
				
			     if ( !isEmpty(<%=IR.getLatitude()%>) && !isEmpty(<%=IR.getLongitude()%>)) 
				 {
					 //console.log("Latitude and Longitude valid !"+ parseFloat(<%=IR.getLatitude()%>)+"   "+ parseFloat(<%=IR.getLongitude()%>));
					latlng = new google.maps.LatLng(parseFloat(<%=IR.getLatitude()%>), parseFloat(<%=IR.getLongitude()%>));
					map.setCenter(latlng);
					var marker = new google.maps.Marker({
													map: map,
													position: latlng
													});
				 }
				 if ( isEmpty(<%=IR.getLatitude()%>) || isEmpty(<%=IR.getLongitude()%>)) 
				 {
					var Location = "<%=IR.getStrippedLocation()%>";
					var Country = "<%=IR.getStrippedCountry()%>";
					if ( (!isEmpty(Location)) && (!isEmpty(Country)) )
					{
						//console.log("Try Country and Location Info... "+Country+" "+Location);
						address = Location + ',' + Country;
					}
					if ( (!isEmpty(Location)) && (isEmpty(Country)) )
					{
						//console.log("Try Location Info... "+Country+" "+Location);
						address = Location ;
					}
					if ( (isEmpty(Location)) && (!isEmpty(Country)) )
					{
						//console.log("Try Country Info... "+Country+" "+Location);
						address = Country;
					}
					if ( isEmpty(Location) && isEmpty(Country) ) 
					{
						//console.log("Location Info void!");
						address = "USA";
					}
				
					geocoder.geocode( {'address': address},
								  function(results, status) 
									{
										if (status == google.maps.GeocoderStatus.OK) 
										{
											//console.log("Latitude and Longitude invalid ! But Country and Location valid !");
											map.setCenter(results[0].geometry.location);
											map.fitBounds(results[0].geometry.viewport);
											map.setZoom(8);
											var marker = new google.maps.Marker
												({
												map: map,
												position: results[0].geometry.location
												});
										} 
										else 
										{
											//console.log("Location info totally invalid !");
											geocoder.geocode( { 'address': "<%= "USA" %>"},
																function(results, status) 
																{
																	if (status == google.maps.GeocoderStatus.OK)
																	{
																		map.setCenter(results[0].geometry.location);
																		map.fitBounds(results[0].geometry.viewport);
																		var marker = new google.maps.Marker
																			({
																			map: map,
																			position: results[0].geometry.location
																			});
																	}
																}
															)
										}
									}
								);
			  }
		}
			  //window.alert(5 + 6);
			  //document.write(5 + 6);
			  //console.log(results[0].geometry.location);
	</script>
	
</head>
<body onload="initialize()">

    <div class="PagePart">
		<div class="col-md-12 text-center">
		<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/index.html"><button class="button">ebay Search Home</button></a>
		&nbsp;&nbsp;&nbsp;
		<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/keywordSearch.html"><button class="button">ebay Keyword Search</button></a>
		&nbsp;&nbsp;&nbsp;
		<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/getItem.html"><button class="button">ebay ItemID Search</button></a>
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
			<form role="form" class="FormContent" action="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/item" method="GET">
				
				<input class="btn btn-default btn-md" name="submit" type="submit" value="Go!" style="float: right"/>
				
				<div class="col-md-11 form-group" style="overflow: hidden;">
					<input class="form-control" name="ItemID" type="text" placeholder="Enter Item ID :)"/>    		
				</div>

				<br/>						
			</form>
		</div>
		<div class = "col-md-4">	
			<h1 class="text-center"> Item Info </h1>
			<table class="table table-hover">
				<tr>
					<td style=" font-weight: bold;">Tags</td>
					<th scope="col">&nbsp;Tag Information</th>
				</tr>
				<tr>
					<th scope="row">ItemID</th>
					<td>&nbsp;<%=IR.getItemID()%></td>
				</tr>
				<tr>
					<th scope="row">Item Name</th>
					<td>&nbsp;<%=IR.getName()%></td>
				</tr>
				
				<%
				for (int i=0;i<Category_r.length;i++)
				{
				%>
				<tr>
					<th scope="row">Categories <%=i+1%></th>
					<td>&nbsp;<%=Category_r[i]%></td>
				</tr>
				<%
				}
				%>
				

				<tr>
					<th scope="row">Currently($)</th>
					<td>&nbsp;<%=IR.getCurrently()%></td>
				</tr>
				<tr>
					<th scope="row">Buy_Price($)</th>
					<td>&nbsp;<%=IR.getBuyPrice()%></td>
				</tr>
				<tr>
					<th scope="row">First_Bid($) </th>
					<td>&nbsp;<%=IR.getFirstBid()%></td>
				</tr>
				<tr>
					<th scope="row">NumOfBids </th>
					<td>&nbsp;<%=IR.getNumBids()%></td>
				</tr>
				<tr>
					<th scope="row">Seller Country</th>
					<td>&nbsp;<%=IR.getCountry()%></td>
				</tr>
				<tr>
					<th scope="row">Seller Location </th>
					<td>&nbsp;<%=IR.getLocation()%></td>
				</tr>
				<tr>
					<th scope="row">Seller Latitude</th>
					<td>&nbsp;<%=IR.getLatitude()%></td>
				</tr>
				<tr>
					<th scope="row">Seller Longitude </th>
					<td>&nbsp;<%=IR.getLongitude()%></td>
				</tr>

				<tr>
					<th scope="row">Bidding Starting Time </th>
					<td>&nbsp;<%=IR.getStarted()%></td>
				</tr>
				<tr>
					<th scope="row">Bidding Ending Time </th>
					<td>&nbsp;<%=IR.getEnds()%></td>
				</tr>
				<tr>
					<th scope="row">Seller_UserID </th>
					<td>&nbsp;<%=IR.getSellerID()%></td>
				</tr>
				<tr>
					<th scope="row">Seller_Rating </th>
					<td>&nbsp;<%=IR.getRating()%></td>
				</tr>
			</table>		
			
		<%
		if (IR.getBuyPrice().length()>0)
		{
		%>
			<div class="text-center">
			<a href="http://<%=serverName%>:<%=httpServerPort%><%=getContextPath%>/creditCardInput.jsp"><button>Pay Now</button></a>
			</div>
		<%
		}
		%>
			
		</div>
		<div class = "col-md-4">
			<h1 class="text-center">Description</h1>
			<p><%=IR.getDescription()%></p>
		</div>
		<div class = "col-md-4 text-center">
			<h1 class="text-center">Google Map</h1>
			<div id="map" style="width: 400px; height: 480px;"></div>
		</div>
		<div class="col-md-12">
		    <%
			if (Bid_r.length>0)
			{
			%>
			<h1 class="text-center">Bidding Info</h1>
			<table class="table table-hover">
				<thead>
				  <tr>
					<th>Bidder Ranking</th>
					<th>Bidder_UserID</th>
					<th>Bidder_Rating</th>
					<th>Bidder_Location</th>
					<th>Bidder_Country</th>
					<th>Time</th>
					<th>Amount($)</th>
				  </tr>
				</thead>
				<tbody>
					
						<%
						for (int i=0;i<Bid_r.length;i++)
						{
						%>
						<tr>
							<td><%=i+1%></td>
							<td><%=Bid_r[i].getBidder_UserID()%></td>
							<td><%=Bid_r[i].getBidder_Rating()%></td>
							<td><%=Bid_r[i].getBidder_Location()%></td>
							<td><%=Bid_r[i].getBidder_Country()%></td>
							<td><%=Bid_r[i].getTime()%></td>
							<td><%=Bid_r[i].getAmount()%></td>
						</tr>
						<%
						}
						%>
														  
				</tbody>
			</table>
			<%
			}
			%>
		</div>
	</div>
	
</body>
</html>