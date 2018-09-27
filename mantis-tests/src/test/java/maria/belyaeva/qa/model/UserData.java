package maria.belyaeva.qa.model;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "id")
    private int id;

    @Transient
    private String password = "password";

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
