package app.dao;

import app.domain.User;
import app.factory.UserFactory;

import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package app.dao
 */
public class UserRepository extends UserFactory {

    /**
     * Save a specific object to the "database".
     * @param u
     */
    public void save(User u) {
        this.setFile(u.getFilename());

        try {
            this.saveObject(u.getFilename(), u);
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
