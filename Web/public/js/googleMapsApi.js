function GoogleMapsApi() {
	// Map init
	this._map,
	// Map DOM element
	this._oMapDiv,
	// Default location
	this._oLocation = {
		lat: 14.7802000,
		lng: 121.178200
	},
	// Default options
	this._oMapOptions = {
		center: this._oLocation,
		zoom: 12
	},
	// Temp control button
	this._oTmpCtrlBtn,
	/**
	 * Google map initializer
	 * @return boolean
	 */
	this.init = function(map, oMap) {
		this._map = map;
		this._oMapDiv = oMap;
		return this;
	},
	/**
	 * Load Google map on DOM element
	 * @return boolean
	 */
	this.loadMap = function() {
		if (google && this._map === undefined) {
			this._map = new google.maps.Map(this._oMapDiv, this._oMapOptions);
			return true;
		}
		return false;
	},
	/**
	 * To create Google map marker
	 * @param  string sTitle    title of marker
	 * @param  object oLocation latitude and longitude of marker
	 * @return mixed
	 */
	this.createMarker = function(oOption) {
		if (google) {
			var _oMarker = new google.maps.Marker(oOption);

			_oMarker.setMap(this._map);
			
			console.log('Marker created.');
			return _oMarker;
		}
		console.error('Google is undefined.');
		return false;
	},
	/**
	 * To delete a Google map marker
	 * @param  object oMarker
	 * @return boolean
	 */
	this.deleteMarker = function(oMarker) {
		if (oMarker !== null) {
			oMarker.setMap(null);
			console.log('Marker deleted.');
			return true;
		}
		console.error('Marker is null/undefined.');
		return false;
	},
	/**
	 * To create a control button in Google map
	 * @param object   oDiv             div element
	 * @param object   oMap             Google map
	 * @param function callbackFunction callback function
	 * @param object   oButton          button properties
	 * @param object   oButtonText      button text properties
	 */
	this.createControlBtn = function(oDiv, callbackFunction, oButton = null, oButtonText = null) {
		var _controlUI = document.createElement('div');
		var _controlText = document.createElement('div');
		var _that = this;

		// Button properties
		_controlUI.style.backgroundColor = oButton.backgroundColor;
		_controlUI.style.border = oButton.border;
		_controlUI.style.borderRadius = oButton.borderRadius;
		_controlUI.style.boxShadow = oButton.boxShadow;
		_controlUI.style.cursor = oButton.cursor;
		_controlUI.style.marginBottom = oButton.marginBottom;
		_controlUI.style.textAlign = oButton.textAlign;
		_controlUI.title = oButton.title;
		if (oButton.height) {
			_controlUI.style.height = oButton.height;
		}
		if (oButton.width) {
			_controlUI.style.width = oButton.width;
		}
		oDiv.appendChild(_controlUI);

		// Button text properties
		_controlText.style.color = oButtonText.color;
		_controlText.style.fontFamily = oButtonText.fontFamily;
		_controlText.style.fontSize = oButtonText.fontSize;
		_controlText.style.lineHeight = oButtonText.lineHeight;
		_controlText.style.paddingLeft = oButtonText.paddingLeft;
		_controlText.style.paddingRight = oButtonText.paddingRight;
		_controlText.innerHTML = oButtonText.innerHTML;
		_controlUI.appendChild(_controlText);

		// Event function
		_controlUI.addEventListener('click', function() {
		    callbackFunction(_that._map, _that._oLocation);
		});

		this._oTmpCtrlBtn = oDiv;

		return this;
	},
	/**
	 * Add the created button to Google map
	 */
	this.addControlBtn = function() {
		this._map.controls[google.maps.ControlPosition.TOP_RIGHT].push(this._oTmpCtrlBtn);
		return true;
	},
	/**
	 * To create an info window
	 * @param  string sText     text  content
	 * @param  array  aPosition array coordinates
	 * @return object       new instance
	 */
	this.createInfoWindow = function(sText = '', aPosition = null) {
		var _oContent = {
			content: sText,
			position: aPosition
		}
		if (aPosition === null) {
			_oContent = {
				content: sText
			}
		}
		return new google.maps.InfoWindow(_oContent);
	},
	/**
	 * Value to evaluate into a color
	 * @param mixed mValue either float or integer
	 */
	this.setColor = function(mValue, sType) {
		var _sColor;
		switch(sType) {
			case 'particle':
				if (mValue >= 5) {
					_sColor = 'green';
				} else if (mValue > 0) {
					_sColor = 'yellow';
				} else {
					_sColor = 'red';
				}
				return _sColor;
			case 'co2':
				if (mValue >= 40) {
					_sColor = 'green';
				} else if (mValue > 20) {
					_sColor = 'yellow';
				} else {
					_sColor = 'red';
				}
				return _sColor;
		}
	},
	/**
	 * Check if this location belongs to the polygon
	 * @param  object oMarker specific location
	 * @param  array aPolygon area coordinates
	 * @return boolean        if belongs to or not
	 */
	this.markerLocator = function(oMarker, aPolygon) {
		var _polyPoints = aPolygon;       
        var _x = oMarker.x_coordinate;
        var _y = oMarker.y_coordinate;
        var _bInside = false;

        for (var _i = 0, _j = _polyPoints.length - 1; _i < _polyPoints.length; _j = _i++) {
            var _xi = _polyPoints[_i][1], _yi = _polyPoints[_i][0];
            var _xj = _polyPoints[_j][1], _yj = _polyPoints[_j][0];

            var _intersect = ((_yi > _y) != (_yj > _y))
                && (_x < (_xj - _xi) * (_y - _yi) / (_yj - _yi) + _xi);
            if (_intersect) _bInside = !_bInside;
        }

        return _bInside;
	},
	this.getBounds = function(aPolygon) {
		var _bounds = new google.maps.LatLngBounds();
		aPolygon.forEach(function(iItem, iIndex) {
			var _point = new google.maps.LatLng(iItem[0], iItem[1]);
        	_bounds.extend(_point);
		});
		return _bounds.getCenter();
	},
	/**
	 * Load Google map GeoJSON
	 * @param  string sFile geojson file
	 * @param  string sName variable name
	 * @return
	 */
	this.loadGeoJson = function(sFile, aData = null, aTrees = null, oInfoBox= null) {
		var _that = this;
		var _aFeatures = sFile.features;
		var _stateLayer = new google.maps.Data();

		_aFeatures.forEach(function(aItem, iIndex) {
			var _aCoordinates = aItem.geometry.coordinates[0];
			var _aBounds = _that.getBounds(_aCoordinates);

			sFile.features[iIndex].properties.center = _aBounds;

			sFile.features[iIndex].properties.aParticle = [];
			sFile.features[iIndex].properties.aCo2 = [];
			sFile.features[iIndex].properties.aTemperature = [];
			sFile.features[iIndex].properties.aHumidity = [];

			sFile.features[iIndex].properties.particle = 0;
			sFile.features[iIndex].properties.co2 = 0;
			sFile.features[iIndex].properties.temperature = 0;
			sFile.features[iIndex].properties.humidity = 0;

			aData.forEach(function(_oItem, _iIndex) {
				var _bRes = _that.markerLocator(_oItem, _aCoordinates);
				if (_bRes === true) {
					sFile.features[iIndex].properties.aParticle.push(_oItem.particle);
					sFile.features[iIndex].properties.aCo2.push(_oItem.co2);
					sFile.features[iIndex].properties.aTemperature.push(_oItem.temperature);
					sFile.features[iIndex].properties.aHumidity.push(_oItem.humidity);
				}
			});
		});

		_aFeatures.forEach(function(aItem, iIndex) {
			var _tmpParticle = 0;
			var _tmpCo2 = 0;
			var _tmpTemperature = 0;
			var _tmpHumidity = 0;

			aItem.properties.aParticle.forEach(function(_mItem, _iIndex) {
				_tmpParticle += _mItem;
 			});
			aItem.properties.aCo2.forEach(function(_mItem, _iIndex) {
				_tmpCo2 += _mItem;
 			});
 			aItem.properties.aTemperature.forEach(function(_mItem, _iIndex) {
				_tmpTemperature += _mItem;
 			});
 			aItem.properties.aHumidity.forEach(function(_mItem, _iIndex) {
				_tmpHumidity += _mItem;
 			});

 			sFile.features[iIndex].properties.particle = _tmpParticle / aItem.properties.aParticle.length;
 			sFile.features[iIndex].properties.co2 = _tmpCo2 / aItem.properties.aCo2.length;
 			sFile.features[iIndex].properties.temperature = _tmpTemperature / aItem.properties.aTemperature.length;
 			sFile.features[iIndex].properties.humidity = _tmpHumidity / aItem.properties.aHumidity.length;
		});

		_stateLayer.addGeoJson(sFile);
		_stateLayer.setMap(this._map);

		_stateLayer.setStyle(function(feature) {
			var _sName = feature.getProperty('name');
			var _fCo2 = feature.getProperty('co2');
			return {
				title: _sName,
				fillColor: _that.setColor(_fCo2, 'co2'),
				fillOpacity: 0.7,
				strokeColor: '#003300',
				strokeWeight: 2,
				zIndex: 1
			};
		});

		// var testInfo;

		_stateLayer.addListener('mouseover', function(event) {
	  		var _sName = event.feature.getProperty('name');
	  		var _aParticle = event.feature.getProperty('particle');
	  		var _aCo2 = event.feature.getProperty('co2');
	  		var _aTemperature = event.feature.getProperty('temperature');
	  		var _aHumidity = event.feature.getProperty('humidity');

	  		oInfoBox.find('.widget-user-area').text(_sName);
	  		oInfoBox.find('.info-box .particle-info .info-box-number').text(_aParticle);
	  		oInfoBox.find('.info-box .co2-info .info-box-number').text(_aCo2);
	  		oInfoBox.find('.info-box .temperature-info .info-box-number').text(_aTemperature);
	  		oInfoBox.find('.info-box .humidity-info .info-box-number').text(_aHumidity);

		  	// testInfo = _that.createInfoWindow(_sName);
		  	// testInfo.open(_that._map);

		  	_stateLayer.overrideStyle(event.feature, {fillColor: 'white'});
		});

		_stateLayer.addListener('mouseout', function(event) {
		  	// testInfo.close();
		  	_stateLayer.revertStyle();
		});
	}
}