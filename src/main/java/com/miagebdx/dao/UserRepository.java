package com.miagebdx.dao;

import com.miagebdx.domain.User;
import com.miagebdx.exceptions.MissingParametersException;
import com.miagebdx.exceptions.NotFoundException;
import com.miagebdx.factory.UserFactory;
import com.miagebdx.utils.DAOUtils;
import com.miagebdx.utils.UserUtils;
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
    private final UserUtils userUtils = UserUtils.getInstance();

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
                if(null != userPersisted){
                    if(userPersisted.getFirstname().equals(username) && userPersisted.getPassword().equals(password)){
                        log.info("Found {} ", userPersisted);
                        return userPersisted;
                    }
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
        if(userUtils.isValidUser(u)){
            User newCreatedUser = this.createClass(u.getFirstname(), u.getLastname(), u.getPassword(), u.getEmail(), u.getBirthdayDate());

            log.info("Creating user {} ", newCreatedUser);

            this.save(newCreatedUser);

            return newCreatedUser;

        }else{
            throw new MissingParametersException();
        }
    }

    /**
     * Update a user.
     * Verify if user's fields are valid.
     * @param id
     * @return
     */
    public void updateUser(Long id, User user) throws RuntimeException {
         /* Only three fields are needed to an User object to be valid.  */
        if(userUtils.isValidUser(user)) {

            log.info("Updating {}, {} ", id, user);

            User u = (User) this.load(id);

            if (null == u) throw new NotFoundException();

            this.save(user);
        }else{
            throw new MissingParametersException();
        }
    }
}
