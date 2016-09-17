// Global

// Global variables
var map;
var oMap = document.getElementById('map');
var oLocation = {
	lat: 14.639681,
	lng: 121.1179541
};
var oOptions = {
	center: oLocation,
	zoom: 12
};

// Document ready
$(function() {
	// Local variables
	var aMarkers = [];

	// Google Map API Methods

	/**
	 * Google map initializer
	 * Asynchronous
	 * @return boolean
	 */
	var initMap = function() {
		if (google && this.map === undefined) {
			console.log('To load map.');
			map = new google.maps.Map(this.oMap, this.oOptions);
			console.log('Map loaded.');
			return true;
		}
		console.error('Google is undefined.');
		return false;
	}

	/**
	 * To create Google map marker
	 * @param  string sName     name of marker
	 * @param  string sTitle    title of marker
	 * @param  object oLocation latitude and longitude of marker
	 * @return boolean
	 */
	var createMarker = function(sName, sTitle = '', oLocation = this.oLocation) {
		if (google) {
			var _oMarker = new google.maps.Marker({
				position: oLocation,
				title: sTitle
			});

			_oMarker.setMap(map);

			aMarkers[sName] = _oMarker;
			console.log('Marker created.');
			return true;
		}
		console.error('Google is undefined.');
		return false;
	}

	/**
	 * To delete a Google map marker
	 * @param  object oMarker
	 * @return boolean
	 */
	var deleteMarker = function(oMarker) {
		if (oMarker !== null) {
			oMarker.setMap(null);
			console.log('Marker deleted.');
			return true;
		}
		console.error('Marker is null/undefined.');
		return false;
	}

	// Initialize Google map
	initMap();

});