package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class InterceptAdmin extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.firstName + " " + user.lastName);
        }
    }

    public static void form(Long id) {
        List<Survey> surveys = Survey.find("owner.email", Security.connected()).fetch();
        Intercept model = null;
        if(id != null) {
            model = Intercept.findById(id);
        }

        if ( model != null ) {
            render(model, surveys);
        }
        render(surveys);
    }

    public static void index() {
        List<Intercept> intercepts = Intercept.find(
                "survey.owner.email", Security.connected() ).fetch();
        render(intercepts);
    }

    public static void view(Long id) {
        Intercept intercept = null;
        if ( null != id ) {
            intercept = Intercept.findById(id);
        }

        if (null == intercept) {
            SurveyAdmin.index();
        } else {
            List<InterceptAppearance> appearances =
                    InterceptAppearance.find("byIntercept", intercept).fetch();
            render(intercept, appearances);
        }
    }

    public static void save(Long id, Long survey_id, Float frequency, String label) {
        Intercept model = null;
        if (null == id) {
            Survey survey = Survey.findById(survey_id);
            
            // Create intercept
            User owner = User.find("byEmail", Security.connected()).first();
            model = new Intercept( Intercept.getUUID(), survey, frequency, label);
        } else {
            // Retrieve intercept
            model = Intercept.findById(id);
        }

        model.survey = Survey.findById(survey_id);
        model.frequency = frequency;
        model.label = label;

        // Validate
        validation.valid(model);
        if(validation.hasErrors()) {
            List<Survey> surveys = Survey.find("owner.email", Security.connected()).fetch();
            render("@form", model, surveys);
        } else {
            // Save
            model.save();
            view(model.id);
        }
    }


    public static void test ( Long id )
    {
        Intercept model = null;
        if ( null != id ) {
            model = Intercept.findById(id);
        }

        if ( null != model) {
            render(model);
        } else {
            render("@index");
        }
    }
}
