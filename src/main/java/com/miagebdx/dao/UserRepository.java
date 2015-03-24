package com.miagebdx.dao;

import com.miagebdx.domain.User;
import com.miagebdx.exceptions.MissingParametersException;
import com.miagebdx.factory.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.dao
 */
public class UserRepository extends UserFactory {

    private final Logger log = LoggerFactory.getLogger(UserRepository.class);

    /**
     * Save a specific object to the "database".
     * @param u
     */
    public void save(User u) {
        log.info("Saving object {}", u);

        this.setFile(u.getUniqueFileName(), u);
        try {
            this.saveObject(u.getUniqueFileName(), u);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Get all the User persisted.
     * @return List<User>
     */
    public List<User> findAll(){
        List<User> lesUsers = new ArrayList<>();

        this.listAllByType("com.miagebdx.domain.User")
                .stream()
                .forEach(user -> lesUsers.add((User) user));

        log.info("Finding all com.miagebdx.domain.User, {}", lesUsers);

        return lesUsers;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User findOneByUsername(String username, String password){
        log.info("Finding one user by username: {} & password: {}", username, password);

        List<User> lesUsers = this.findAll();
        if(!lesUsers.isEmpty()){
            for(User userPersisted: lesUsers){
                if(userPersisted.getFirstname().equals(username) && userPersisted.getPassword().equals(password)){
                    log.info("Found {} ", userPersisted);
                    return userPersisted;
                }
            }
        }

        return null;
    }

    /**
     * Saving a user
     * @param u
     * @return
     */
    public User createUser(User u) throws MissingParametersException {
        if(null != u.getFirstname() &&
            null != u.getLastname() &&
            null != u.getPassword() &&
            null != u.getEmail() &&
            null != u.getBirthdayDate()) {

            User newCreatedUser = this.createClass(u.getFirstname(), u.getLastname(), u.getPassword(), u.getEmail(), u.getBirthdayDate());

            log.info("Creating user {} ", newCreatedUser);

            this.save(newCreatedUser);

            return newCreatedUser;

        }else{
            throw new MissingParametersException();
        }
    }
}
