if (typeof console == 'undefined') {
  console = {log: function() {}};
}

var SWAnalyzer = function(uuid) {
    var _uuidKey = "visitorUuid";
    this.track = function() {
	console.log('tracking ' + location.href);

	var trackVars = {
	    url: location.href,
	    visitorUuid: getCookie(_uuidKey)
	};

        $.getJSON('http://localhost:9000/analyzer/track', trackVars, function(data) {
		setVisitorId( data.uuid );
	});
    }

    setCookie = function(key, value, expires ) {
	document.cookie = key + "=" + escape(value) + "; path=/" + ((expires == null) ? "" : ";expires=" + expires.toGMTString());
    };

    getCookie = function(key) {
	var dc = document.cookie;
	var cname = key + "=";

	if (dc.length > 0) {
		begin = dc.indexOf(cname);
		if (begin != -1) {
			begin += cname.length;
			end = dc.indexOf(";", begin);
			if (end == -1) end = dc.length;
			return unescape(dc.substring(begin, end));
		}
	}
	return null; 
    };

    /**
     * Visitor ID is stored for 31 days
     */
    setVisitorId = function(value) {
	var expires = new Date();
	expires.setTime(expires.getTime() + (1000*60*60*24*31));
	setCookie( _uuidKey, value, expires );
    };

};
