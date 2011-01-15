/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

/**
 *
 * @author mwilson
 */
@Entity
@Table(name = "tblSurveysPartsMultiChoice")
@DiscriminatorValue("multq")
public abstract class MultipleChoiceQuestionSet extends SurveyPart {

    public MultipleChoiceQuestionSet(Survey parent) {
        this(parent, 1);
    }

    public MultipleChoiceQuestionSet(Survey parent, int display_order) {
        super(parent, display_order);
    }
}
