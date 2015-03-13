package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package domain
 */
public class User implements java.io.Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String birthdayDate;
    private List<User> friends;
    private List<Group> groups;

    public User(String firstname, String lastname, String password, String email, String birthdayDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.friends = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    /* Empty constructor */
    public User(){ }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User u){
        this.friends.add(u);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}