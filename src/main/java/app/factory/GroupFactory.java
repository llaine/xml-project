package app.factory;

import app.domain.Group;
import app.domain.User;
import app.interfaces.IFactory;
import app.utils.DAOUtils;
import app.xml.XMLHandler;

import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package app.factory
 */
public class GroupFactory extends XMLHandler implements IFactory {

    private static final DAOUtils daoUtils = new DAOUtils();

    @Override
    public Object createClass() {
        return new Group();
    }

    public Group createClass(String name, List<User> members){
        return new Group(daoUtils.getRandomLong(), name, members);
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
