package com.miagebdx.factory;

import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;
import com.miagebdx.interfaces.IFactory;
import com.miagebdx.utils.DAOUtils;
import com.miagebdx.xml.XMLHandler;

import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.factory
 */
public class GroupFactory extends XMLHandler implements IFactory {

    private static final DAOUtils daoUtils = new DAOUtils();

    @Override
    public Object createClass() {
        return new Group();
    }

    public Group createClass(String name){
        return new Group(daoUtils.getRandomLong(), name);
    }

    public Group createClass(String name, List member){
        return new Group(daoUtils.getRandomLong(), name, member);
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
