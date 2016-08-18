/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */
function KeyWordSuggestions() {}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
KeyWordSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    var requestURL = "/eBay/suggest?q=" + encodeURI(sTextboxValue);
	var XHR = new XMLHttpRequest();
    XHR.open("GET", requestURL);
    XHR.onreadystatechange = function() 
	{
         if (XHR.readyState == 4) 
		 {
             var s = XHR.responseXML.getElementsByTagName('CompleteSuggestion');

             //search for matching suggestion
			 var maxLen = (s.length>5) ? 5: s.length;
			 
             for (var i=0; i < maxLen; i++) 
			 {
                 var text = s[i].childNodes[0].getAttribute("data");
                 aSuggestions.push(text);
             }
         }
         //provide suggestions to the control
         oAutoSuggestControl.autosuggest(aSuggestions, false);
    }

    XHR.send(null);

};