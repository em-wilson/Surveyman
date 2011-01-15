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
public class Intercept extends Model {
    @Required
    @ManyToOne
    public Survey survey;

    public String uuid;

    public String label;

    public Float frequency;

    public Date createdAt;

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public Intercept( String uuid, Survey survey, Float frequency, String label ) {
        this.uuid = uuid;
        this.survey = survey;
        this.frequency = frequency;
        this.label = label;
        this.createdAt = new Date();
    }

    
}
