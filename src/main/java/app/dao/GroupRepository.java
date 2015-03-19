package app.dao;

import app.domain.Group;
import app.factory.GroupFactory;

/**
 * projetXML
 *
 * @author llaine
 * @package app.dao
 */
public class GroupRepository extends GroupFactory {
    /**
     * Save a specific object to the "database".
     * @param g
     */
    public void save(Group g) {
        this.setFile(g.getUniqueFileName(), g);

        try {
            this.saveObject(g.getUniqueFileName(), g);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
