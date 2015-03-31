package com.miagebdx.dao;

import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;
import com.miagebdx.exceptions.MissingParametersException;
import com.miagebdx.factory.GroupFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.dao
 */
public class GroupRepository extends GroupFactory {

    private final Logger log = LoggerFactory.getLogger(GroupRepository.class);


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


    /**
     * Saving a user
     * @param g
     * @return
     */
    public Group createGroup(Group g) throws MissingParametersException {
        if(null != g.getName()) {

            Group newGroup = this.createClass(g.getName());

            log.info("Creating user {} ", newGroup);

            this.save(newGroup);

            return newGroup;

        }else{
            throw new MissingParametersException();
        }
    }
}
