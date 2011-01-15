package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Admin extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.firstName + " " + user.lastName);
        }
    }

    public static void index() {
        List<Survey> surveys = Survey.find("owner.email", Security.connected()).fetch();
        render(surveys);
    }
}
