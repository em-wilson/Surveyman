package controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import models.AnalyticsPageHit;
import models.AnalyticsVisitor;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Request;

public class AnalyzerAPI extends Controller {
	@Before
	public static void permitRemoteOriginRequests()
	{
    	Http.Header hd = new Http.Header();
    	hd.name = "Access-Control-Allow-Origin";
    	hd.values = new ArrayList<String>();
    	hd.values.add("*");
    	Http.Response.current().headers.put("Access-Control-Allow-Origin", hd);
	}

	public static void track(String url, String referrer, String visitorUuid)
	{
		AnalyticsVisitor visitor = null;
		if ( null != visitorUuid ) {
			visitor = AnalyticsVisitor.find("uuid = ?", visitorUuid).first();
		}
		if ( null == visitor )
		{
			visitor = new AnalyticsVisitor();
			visitor.charset = request.headers.get("accept-charset").value();
			visitor.encoding = request.headers.get("accept-encoding").value();
			visitor.language = request.headers.get("accept-language").value();
			visitor.userAgent = request.headers.get("user-agent").value();
			visitor.referrer = referrer;
			visitor.save();
		}
		
		try {
			URL trackedUrl = new URL(url);
			AnalyticsPageHit record = new AnalyticsPageHit();
			
			record.visitorUuid = visitor.uuid;
			record.host = trackedUrl.getHost();
			record.path = trackedUrl.getPath();
			record.querystring = trackedUrl.getQuery();
			record.save();
			
	    	play.utils.Properties settings = new play.utils.Properties();
	        settings.put("frequency", "15");
	        settings.put("url", url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        renderJSON(visitor);
	}
}
