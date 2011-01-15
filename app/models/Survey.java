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
@Table(name="tblSurveys")
public class Survey extends Model {
    @Required
    public String title;
    public Date createdAt;

    @Lob
    @Required
    @MaxSize(10000)
    public String content;

    @Required
    @ManyToOne
    public User owner;

    public Survey(User owner, String title, String content) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
    }
}
