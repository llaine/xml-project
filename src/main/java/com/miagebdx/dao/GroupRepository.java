package com.miagebdx.dao;

import com.miagebdx.domain.Group;
import com.miagebdx.factory.GroupFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.dao
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


    /**
     * Get all the User persisted.
     * @return List<User>
     */
    public List<Group> findAll(){

        List<Group> lesGroups = new ArrayList<>();

        this.listAllByType("com.miagebdx.domain.Group")
                .stream()
                .forEach(group -> lesGroups.add((Group) group));

        return lesGroups;
    }
}
