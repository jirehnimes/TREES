// Global

// Global variables
var map;
var oMap = document.getElementById('map');

// Instantiate GoogleMapsApi class
var gMap = new GoogleMapsApi();

// Initialize Google map
if (gMap.init(map, oMap).loadMap()) {
	console.log('Google map load successful!');
} else {
	console.error('Error loading map.');
}

// Document ready
$(function() {
	// Local variables

	console.log(aTrees);

	// Collection of markers
	var aTreeMarkers = [];

	// GeoJson of Montalban
	var oMontalban = {};
	var oMarikina = {};
	var oSanMateo = {};

	// Info box DOM
	var oInfoBox = $('.info-column');

	// Center map button
	 
	// Center map base element
	var oCenterMapDiv = document.createElement('div');

	// Center map callback function
	var oCenterMapFunc = function(oMap, oLocation) {
		oMap.setCenter(oLocation);
	}

	// Center map button properties
	var oCenterMapBtnProp = {
		height: '50px',
		width: '50px',
		backgroundColor: 'green',
		border: '2px solid #fff',
		borderRadius: '3px',
		boxShadow: '0 2px 6px rgba(0, 0, 0, .3)',
		cursor: 'pointer',
		marginBottom: '22px',
		textAlign: 'center',
		title: 'Click to recenter the map'
	}

	// Center map button text properties
	var oCenterMapBtnTextProp = {
		color: 'white',
		fontFamily: 'Roboto,Arial,sans-serif',
		fontSize: '20px',
		lineHeight: '50px',
		paddingLeft: '5px',
		paddingRight: '5px',
		innerHTML: '<i class="fa fa-crosshairs"></i>',
	}

	// Center map button object
	// To add in Google map
	var oCenterMapBtn = gMap.createControlBtn(oCenterMapDiv, oCenterMapFunc, oCenterMapBtnProp, oCenterMapBtnTextProp)
		.addControlBtn();

	// For GeoJSON Data
	$.getJSON('geojson/MONTALBAN.json', function(oResults) {
		oMontalban = oResults;
		gMap.loadGeoJson(oMontalban, aSensorData, aTrees, oInfoBox);
	});
	// $.getJSON('geojson/MARIKINA.json', function(oResults) {
	// 	oMarikina = oResults;
	// 	gMap.loadGeoJson(oMarikina, aSensorData);
	// });
	// $.getJSON('geojson/SANMATEO.json', function(oResults) {
	// 	oSanMateo = oResults;
	// 	gMap.loadGeoJson(oSanMateo, aSensorData);
	// });

	// Load tree markers
	aTrees.forEach(function(oItem, iIndex) {
		var _oLocation = {
			lat: parseFloat(oItem.x_coordinate),
			lng: parseFloat(oItem.y_coordinate)
		};
		var _oOptions = {
			position: _oLocation,
			title: oItem.name,
			icon: 'img/tree.png'
		}

		aTreeMarkers.push(gMap.createMarker(_oOptions));
	});

});