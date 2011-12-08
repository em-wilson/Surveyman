package controllers;

import java.util.*;

import play.*;
import play.mvc.*;
import play.utils.*;

import models.*;

public class InterceptController extends Controller {
	@Before
	public static void permitRemoteOriginRequests()
	{
    	Http.Header hd = new Http.Header();
    	hd.name = "Access-Control-Allow-Origin";
    	hd.values = new ArrayList<String>();
    	hd.values.add("*");
    	Http.Response.current().headers.put("Access-Control-Allow-Origin", hd);
	}

    public static void settings(String uuid) {
        play.utils.Properties settings = new play.utils.Properties();
        settings.put("frequency", "0");
        if ( null != uuid ) {
            Intercept intercept = Intercept.find("byUuid", uuid).first();

            if ( null != intercept ) {
                settings.put("frequency", intercept.frequency.toString());
                renderJSON(settings);
            }
        }
        renderText("Error locating Intercept");
    }
    
    public static void accounts() {
    	play.utils.Properties settings = new play.utils.Properties();
        settings.put("frequency", "15");
        renderJSON(settings);
    }
}