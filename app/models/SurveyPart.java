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
@Table(name="tblSurveysParts")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING, length = 5)
public abstract class SurveyPart extends Model {
    public Date createdAt;

    @Required
    @ManyToOne
    public Survey survey;

    @Required
    public int display_order;

    public SurveyPart(Survey parent) {
        this(parent, 1);
    }

    public SurveyPart(Survey survey, int display_order) {
        this.survey = survey;
        this.display_order = display_order;
        this.createdAt = new Date();
    }
}
