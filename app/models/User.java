package models;

import net.sf.oval.constraint.Email;
import play.data.validation.*;
import play.db.jpa.Model;

import javax.persistence.*;

@Entity
public class User extends Model {


    @Email
    @Required
    public String email;

    @Required
    @MaxSize(1000)
    public String password;

    @Required
    @MinSize(8)
    public String fullname;
    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    @Override
    public String toString() {
        return email;
    }
}
