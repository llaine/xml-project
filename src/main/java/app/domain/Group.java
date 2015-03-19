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
public class Group implements IGroup {
    private Long id;
    private String name;
    private List<User> members;


    public Group(Long id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Group(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @Override
    public void addMember() {
        //TODO
    }

    @Override
    public String getUniqueFileName(){
        return "group-" + this.getId() + ".xml";
    }


}
