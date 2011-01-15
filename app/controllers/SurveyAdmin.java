package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class SurveyAdmin extends Controller {

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

    public static void form(Long id) {
        if(id != null) {
            Survey survey = Survey.findById(id);
            render(survey);
        }
        render();
    }

    public static void view(Long id) {
        Survey survey = null;
        if ( null != id ) {
            survey = Survey.findById(id);
        }

        if (null == survey) {
            SurveyAdmin.index();
        } else {
            List<SurveyPart> parts =
                    SurveyPart.find("bySurvey", survey).fetch();
            List<models.Intercept> intercepts =
                    models.Intercept.find("bySurvey", survey).fetch();
            render(survey, intercepts, parts);
        }
    }

    /**
     * Display the create/edit form for a single survey question or part
     * @param id
     */
    public static void question( Long surveyId, Long questionId ) {
        if ( null == surveyId ) {
            index();
        }
        if ( null != questionId ) {
            SurveyPart question = SurveyPart.findById(questionId);
        } else {
            render("@newquestion");
        }
    }

    public static void save(Long id, String title, String content) {
        Survey survey;
        if (null == id) {
            // Create survey
            User owner = User.find("byEmail", Security.connected()).first();
            survey = new Survey(owner, title, content);
        } else {
            // Retrieve survey
            survey = Survey.findById(id);
            // Edit
            survey.title = title;
            survey.content = content;
        }

        // Validate
        validation.valid(survey);
        if(validation.hasErrors()) {
            render("@form", survey);
        }
        // Save
        survey.save();
        index();
    }
}
