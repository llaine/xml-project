package app.dao;

import app.domain.User;
import app.factory.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package app.dao
 */
public class UserRepository extends UserFactory {

    private final Logger log = LoggerFactory.getLogger(UserRepository.class);

    /**
     * Save a specific object to the "database".
     * @param u
     */
    public void save(User u) {
        log.debug("Saving object {}", u);

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

        this.listAllByType("app.domain.User")
                .stream()
                .forEach(user -> lesUsers.add((User) user));

        log.debug("Finding all app.domain.User, {}", lesUsers);

        return lesUsers;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User findOneByUsername(String username, String password){
        log.debug("Finding one user by username: {} & password :{}", username, password);

        List<User> lesUsers = this.findAll();
        if(!lesUsers.isEmpty()){
            for(User userPersisted: lesUsers){
                if(userPersisted.getFirstname().equals(username) && userPersisted.getPassword().equals(password)){
                    log.debug("Found {} ", userPersisted);
                    return userPersisted;
                }
            }
        }

        return null;
    }
}
