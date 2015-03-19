package app.dao;

import app.domain.User;
import app.factory.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        this.listAllByType("user");

        return null;
    }
}
