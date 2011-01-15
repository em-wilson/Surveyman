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
public class InterceptAppearance extends Model {
    @Required
    @ManyToOne
    public Intercept intercept;

    public Date createdAt;
    public Date appearanceAt;
    public Date yesResponseAt;
    public Date noResponseAt;

    public InterceptAppearance( Intercept intercept ) {
        this.intercept = intercept;
        this.createdAt = new Date();
        this.appearanceAt = null;
        this.yesResponseAt = null;
        this.noResponseAt = null;
    }

    
}
