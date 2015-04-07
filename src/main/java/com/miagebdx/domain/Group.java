package com.miagebdx.domain;

import com.miagebdx.interfaces.IGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package com.miagebdx.domain
 */
public class Group implements IGroup {
    private Long id;
    private String name;
    private List<User> members;


    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
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


    public void addMember(User u) {
        if(this.members == null){
            this.members = new ArrayList<>();
        }

        this.members.add(u);
    }

    @Override
    public String getUniqueFileName(){
        return "group-" + this.getId() + ".xml";
    }


}
