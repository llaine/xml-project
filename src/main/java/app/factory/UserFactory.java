package app.factory;

import app.domain.User;
import app.interfaces.IFactory;
import app.xml.XMLHandler;

/**
 * projetXML
 *
 * @author llaine
 * @package app
 */
public class UserFactory extends XMLHandler implements IFactory {

    @Override
    public User createClass(String... properties) {
        return new User();
    }

    /**
     * Load the current object
     * @return Object
     */
    public Object load(int idUser) {
        try {
            // Load object take the id of the object wanted to be load and the type. (UserFactory = USer, GroupFactory = Group)
            Object result = this.loadObject(idUser, this.getClass().getName());
            return result == null ? null : result;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
