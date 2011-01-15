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
public class User extends Model {
    @Email
    @Required
    public String email;

    @Required
    public String password;
    public String firstName;
    public String lastName;
    public boolean isAdmin;

    public User(String email, String password, String firstName, String lastName ) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
