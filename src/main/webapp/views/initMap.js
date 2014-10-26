// JavaScript Document
var map;                //Map element
var xmlhttp;            //AJAX request
var currentPosition;    //position on map
var currentMarker;           //marker in current position
var markers = [];       //all favorite addresses

google.maps.event.addDomListener(window, 'load', initialize);

//Initializing map in start position
function initialize() {
	GetLocation();
	var defaultMapOptions = {
		center: {lat : 48.9501, lng : 24.701},
		zoom: 14
	};
	map = new google.maps.Map(document.getElementById('map_container'), defaultMapOptions);
	getReq();
	//Adding marker to map in click_point and sending addRequest to server
	//currently unavailable
	/*
	google.maps.event.addListener(map, 'click', function(e) {
	                                               var description = document.getElementById("description").value;
                                                   addMarker(e.latLng, description, "ATM");
                                                   addReq(e.latLng, description);
                                               });
};
*/
}

//Getting location from browser
function GetLocation(){
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(setLocation);
	}
};

//Setting current location in position received from browser
function setLocation(position){
	currentPosition = { lat: position.coords.latitude, lng: position.coords.longitude};
	currentMarker = new google.maps.Marker({
                            position: currentPosition,
                            map: map,
                            title: ""
                        });
    currentMarker.setMap(map);
	map.panTo(currentPosition);
};

//Setting current location in position given as latLng var
function setLocationByLatLng(lat_lng_position){
	addMarker(lat_lng_position, "");
	map.panTo(lat_lng_position);
}

//Adding marker to map
function addMarker(position, title){
	var	marker = new google.maps.Marker({
		position: position,
		map: map,
		title: title
	});
	marker.setMap(map);
	marker.setIcon("views/savedMarker.png");
	markers.push(marker);
	//Removing marker from map and sending request for removing to server
	google.maps.event.addListener(marker, 'click', function removeMarker() {
	                                                   if(window.confirm("Are you sure?")){//If this marker not in current position marker then remove
                                                            marker.setMap(null);
                                                            removeReq(marker.getPosition());
                                                       }
                                                   });
}

//Setting current position to address given in text_field
function setLocationByAddress(){
	var addres_str = document.getElementById("address_string").value;
	if(addres_str != ""){
		var geocoder = new google.maps.Geocoder();
        geocoder.geocode({'address' : addres_str}, function(data, status){
            if(status == google.maps.GeocoderStatus.OK){
            	if(currentMarker != null){
            	    currentMarker.setMap(null);
            	};
            	currentPosition = {lat : data[0].geometry.location.lat(), lng : data[0].geometry.location.lng()};
            	currentMarker = new google.maps.Marker({
                                    	position: currentPosition,
                                    	map: map,
                                    	title: ""
                                    });
                currentMarker.setMap(map);
            	map.panTo(currentPosition);
            } else {
            	window.alert("Address is invalid");
            }
        })
	}
	return false;	
};

//Sending request to server for ATMs
function getReq() {
	xmlhttp=GetXmlHttpObject();

    if (xmlhttp==null){
   		alert ("Your browser does not support Ajax HTTP");
   		return;
  	}
    var url = "/gmplaces/getdata";
    xmlhttp.open("GET", url, true);
    xmlhttp.onreadystatechange = function() {
  		if (xmlhttp.readyState == 4) {
     		if(xmlhttp.status == 200) {
       			parseRequest();
         	}
  		}
	};
    xmlhttp.send(null);
};

//Receiving data about markers from server and adding marker to map
function parseRequest() {
    ATMs = JSON.parse(xmlhttp.responseText);
    for (var i = 0; i < ATMs.length; i++) {
		var address = ATMs[i];
   		addMarker({"lat" : address.latitude, "lng" : address.longitude}, address.description);
	}
};


//Adding currentPosition to favorites
function addToFav(){
    var description = document.getElementById("description").value;
    if(currentMarker != null){
        addReq(currentMarker.position, description);
        addMarker(currentMarker.position, description);
		currentMarker.setMap(null);
        currentMarker = null;
    } else {
        window.alert("Set up address for adding to favorites");
    }
}
//Sending request for adding to server
function addReq(position, description) {
	xmlhttp=GetXmlHttpObject();

    if (xmlhttp==null){
   		alert ("Your browser does not support Ajax HTTP");
   		return;
  	}
    var url = "/gmplaces/putdata?lat="+position.lat()+"&lng="+position.lng()+"&description="+description;
    xmlhttp.open("GET", url, true);
	xmlhttp.onreadystatechange = function() {
  		if (xmlhttp.readyState == 4) {
     		if(xmlhttp.status == 200) {
       			reqResult = JSON.parse(xmlhttp.responseText);
				if(reqResult.codeAns == "SUCCESS"){
					window.alert("Point was successfully added to XML in server");
				} else {
					window.alert("Point wasn't added to XML in server");
				}
         	}
  		}
	};
    xmlhttp.send(null);
};

//Sending request for removing point from XML on server
function removeReq(position) {
	xmlhttp=GetXmlHttpObject();

    if (xmlhttp==null){
   		alert ("Your browser does not support Ajax HTTP");
   		return;
  	}
    var url = "/gmplaces/removedata?lat="+position.lat()+"&lng="+position.lng();
    xmlhttp.open("GET", url, true);
	xmlhttp.onreadystatechange = function() {
  		if (xmlhttp.readyState == 4) {
     		if(xmlhttp.status == 200) {
       			reqResult = JSON.parse(xmlhttp.responseText);
				if(reqResult.codeAns == "SUCCESS"){
					window.alert("Point was successfully removed from XML in server");
				} else {
					window.alert("Point wasn't removed from XML in server");
				}
         	}
  		}
	};
    xmlhttp.send(null);
};

//Clearing all favorites from server and map
function clearAll(){
	//Sending request to server to delete all favorites
  if(window.confirm("Are you sure?")){	
    xmlhttp=GetXmlHttpObject();

    if (xmlhttp==null){
        alert ("Your browser does not support Ajax HTTP");
       		return;
    }
    var url = "/gmplaces/clearalldata";
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
	//Deleting all markers from map
	for(var i = 0; i < markers.length; i++){
		markers[i].setMap(null);
	}
	markers = [];
  }
}

//Creating GET HTTP request object
function GetXmlHttpObject(){
    if (window.XMLHttpRequest){
       	return new XMLHttpRequest();
    }
    if (window.ActiveXObject){
      	return new ActiveXObject("Microsoft.XMLHTTP");
    }
 	return null;
}
//Drawing circle 
function drawCircle(){
    var circleRadius = document.getElementById("circleRadius").value;
    if(circleRadius != "") {
        var circleOptions = {
              strokeColor: '#0000FF',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              fillColor: '#0000FF',
              fillOpacity: 0.05,
              map: map,
              center: currentPosition,
              radius: parseInt(circleRadius)
            };
        try{//error if it is first running of this function
            circleAroundMe.setMap(null);
        } finally{
            circleAroundMe = new google.maps.Circle(circleOptions);
        }
    }

}


//