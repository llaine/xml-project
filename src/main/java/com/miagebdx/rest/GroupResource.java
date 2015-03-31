package com.miagebdx.rest;

import java.util.List;
import java.util.Optional;

import com.miagebdx.authentification.AuthUtils;
import com.miagebdx.dao.GroupRepository;
import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package com.miagebdx.rest
 */

@RestController
@RequestMapping("/api")
public class GroupResource {

    private final Logger log = LoggerFactory.getLogger(GroupResource.class);
    private GroupRepository groupRepo = new GroupRepository();
    private final AuthUtils authUtils = AuthUtils.getInstance();


    /**
     * GET -> /groups : get all the groups
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/groups")
    public @ResponseBody
    List<Group> getGroups(HttpSession session){
        authUtils.firewall(session);

        log.debug("Getting resource GET groups");

        return groupRepo.findAll();
    }



    /**
     * GET -> /groups/:id : Get a specific group
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/groups/{id}")
    public @ResponseBody
    ResponseEntity<Group> getGroup(@PathVariable Long id, HttpSession session) {
        authUtils.firewall(session);

        log.debug("REST request to Group : {}", id);

        return Optional.ofNullable((Group) groupRepo.load(id))
                .map(group -> new ResponseEntity<>(
                        group,
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * POST -> /groups : create a group
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/groups")
    public ResponseEntity<Group> createGroup(@ModelAttribute Group group, HttpSession session) {
        authUtils.firewall(session);

        log.info("Creating group {}", group);

        groupRepo.createGroup(group);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}
