package com.miagebdx.utils;

import com.miagebdx.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.utils
 */
public class UserUtils {
    private static UserUtils ourInstance = new UserUtils();

    public static UserUtils getInstance() {
        return ourInstance;
    }

    private UserUtils() {
    }


    private final Logger log = LoggerFactory.getLogger(DAOUtils.class);

    /**
     * A little diff algorithm to check which fields have been updated
     * when called "update" methods.
     *
     * @param userPersisted
     * @param userUpdated
     * @return User
     */
    public User diff(User userPersisted, User userUpdated){
        userPersisted.setId(userUpdated.getId());

        /* Checkin the firstname */
        if(!userPersisted.getFirstname().equals(userUpdated.getFirstname()) && null != userUpdated.getFirstname()){
            userPersisted.setFirstname(userUpdated.getFirstname());
        }

        /* Checkin the lastname */
        if(!userPersisted.getLastname().equals(userUpdated.getLastname()) && null != userUpdated.getLastname()){
            userPersisted.setLastname(userUpdated.getLastname());
        }

        /* Checkin the email */
        if(!userPersisted.getEmail().equals(userUpdated.getEmail()) && null != userUpdated.getEmail()){
            userPersisted.setEmail(userUpdated.getEmail());
        }

        /* Checkin the birthday date */
        if(!userPersisted.getBirthdayDate().equals(userUpdated.getBirthdayDate()) && null != userUpdated.getBirthdayDate()){
            userPersisted.setBirthdayDate(userUpdated.getBirthdayDate());
        }


        /* Checkin the password */
        if(!userPersisted.getPassword().equals(userUpdated.getPassword()) && null != userUpdated.getPassword()){
            userPersisted.setPassword(userUpdated.getPassword());
        }

        /* Checkin the friends */
        if(!userPersisted.getFriends().equals(userUpdated.getFriends()) && null != userUpdated.getFriends()){
            userPersisted.setFriends(userUpdated.getFriends());
        }

        /* Checkin the group */
        if(!userPersisted.getGroups().equals(userUpdated.getGroups()) && null != userUpdated.getGroups()){
            userPersisted.setGroups(userUpdated.getGroups());
        }

        return userPersisted;
    }

    /**
     * Check if a User passed in params has minimum properties
     * such as firstname, passord and email!
     * @param u
     * @return
     */
    public Boolean isValidUser(User u){
        Boolean isValid = false;
        if(null != u.getFirstname() && null != u.getPassword() && null != u.getEmail()) {
            isValid = true;
        }
        return isValid;
    }
}
