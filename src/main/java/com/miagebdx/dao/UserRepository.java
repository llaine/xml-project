package com.miagebdx.dao;

import com.miagebdx.domain.Group;
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
import java.util.Random;
import java.util.stream.Collectors;

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

    /**
     * Add a new user onto HashMap of the User how correspond to the user id.
     * @param id
     * @param user
     * @throws RuntimeException
     */
    public void addFriend(Long id, User user) throws RuntimeException {
        log.info("Adding friend {} to {}", user, id);

        User u = (User) this.load(id);

        if(u == null){
            throw new NotFoundException();
        }

        if(id == null){
            throw new MissingParametersException();
        }

        user.setId(getRandomLong());

        u.addFriend(user);

        this.save(u);
    }

    public void addGroup(Long id, Group group) {
        log.info("Adding a new group {} for {} ", id, group);

        User u = (User) this.load(id);

        if(u == null){
            throw new NotFoundException();
        }

        if(id == null || group == null){
            throw new MissingParametersException();
        }

        group.setId(getRandomLong());

        u.addGroup(group);

        this.save(u);
    }

    public void addUserToGroup(Long idUser, Long idGroup, User u) {
        log.info("Adding a new user {} to group {} from {}", u, idGroup, idUser);

        // Fetching the user in database.
        User user = (User) this.load(idUser);

        if(u == null) throw new NotFoundException();

        if(idUser == null || idGroup == null) throw new MissingParametersException();

        // Getting all the group of the user.
        List<Group> groupsFromUser = user.getGroups();

        if(groupsFromUser != null) {
            for(Group g : groupsFromUser) {
                if(g.getId().equals(idGroup)) {
                    g.addMember(user);
                    break;
                }
            }
        }

    }

    public void removeFriend(Long idUser, Long idFriend){
        log.info("Removing friend {} from user's list {} ", idFriend, idUser);
        User user = (User) this.load(idUser);

        if(user == null) throw new NotFoundException();

        if(idUser == null || idFriend == null) throw new MissingParametersException();

        ArrayList<User> newFriends = user.getFriends()
                .stream()
                .filter(f -> !f.getId().equals(idFriend))
                .collect(Collectors.toCollection(ArrayList::new));

        user.setFriends(newFriends);

        this.save(user);
    }



    public Long getRandomLong(){
        long LOWER_RANGE = 0;
        long UPPER_RANGE = 1000000;
        Random random = new Random();
        return LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
    }

}
