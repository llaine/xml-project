package app.domain;

import app.interfaces.IGroup;
import app.xml.XMLHandler;

import java.util.List;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package app.domain
 */
public class Group extends XMLHandler implements IGroup {
    private Long id;
    private String name;
    private List<User> members;

    @Override
    public void addMember() {
        //TODO
    }

}
