package controllers;

import java.util.*;

import play.*;
import play.mvc.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        Survey frontSurvey = Survey.find("order by createdAt desc").first();
        List<Survey> olderSurveys = Survey.find(
                "order by createdAt desc"
        ).from(1).fetch(10);
        render(frontSurvey, olderSurveys);
    }

    public static void signup() {
        render();
    }

    /**
     * Save new user
     */
    public static void save() {
        /* Not implemented */
    }
}