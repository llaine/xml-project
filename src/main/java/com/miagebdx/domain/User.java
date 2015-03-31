package com.miagebdx.domain;

import com.miagebdx.interfaces.IUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package com.miagebdx.domain
 */
public class User implements IUser {
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

    public Long getId() { return id; }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                ", friends=" + friends +
                ", groups=" + groups +
                '}';
    }

    @Override
    public String getUniqueFileName(){
        return "user-" + this.getId() + ".xml";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (birthdayDate != null ? !birthdayDate.equals(user.birthdayDate) : user.birthdayDate != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
        if (groups != null ? !groups.equals(user.groups) : user.groups != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthdayDate != null ? birthdayDate.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    /**
     * get a specific group, lambda style expression!
     * @param id
     * @return
     */
    public Optional<Group> getGroup(Long id){
        return Optional.ofNullable(
                groups.stream()
                        .filter(group -> group.getId().equals(id))
                        .findAny()
                ).orElse(null);
    }

    @Override
    public void addFriends(User user) {
        this.friends.add(user);
    }

    @Override
    public void addGroup(Group group) {
        this.groups.add(group);
    }

}