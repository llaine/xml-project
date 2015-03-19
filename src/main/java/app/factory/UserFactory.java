package app.factory;

import app.domain.User;
import app.interfaces.IFactory;
import app.utils.DAOUtils;
import app.xml.XMLHandler;

/**
 * projetXML
 *
 * @author llaine
 * @package app
 */
public class UserFactory extends XMLHandler implements IFactory {

    private static final DAOUtils daoUtils = new DAOUtils();

    @Override
    public User createClass() {
        User u = new User();
        u.setId(daoUtils.getCount());
        return u;
    }

    public User createClass(String firstname, String lastname, String password, String email, String birthdayDate){
        User u = new User(firstname, lastname, password, email, birthdayDate);
        u.setId(daoUtils.getCount());

        return u;
    }

    /**
     * Load the current object
     * @return Object
     */
    public Object load(Long idUser) {
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
