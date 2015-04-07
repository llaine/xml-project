package com.miagebdx.rest;


import com.miagebdx.authentification.AuthUtils;
import com.miagebdx.dao.UserRepository;
import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;


/**
 * gs-actuator-service
 *
 * @author llaine
 * @package com.miagebdx.rest
 */

@Controller
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private UserRepository userRepo = new UserRepository();
    private final AuthUtils authUtils = AuthUtils.getInstance();

    /**
     * GET -> /users : get all the users
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users")
    public @ResponseBody ResponseEntity getUsers(HttpSession session){
        authUtils.firewall(session);

        log.debug("Getting Resource User");

        List list = userRepo.findAll();

        log.info("debug {}", list);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * POST -> /users : Create a specific User.
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users")
    public ResponseEntity<User> createUser(@ModelAttribute User user, HttpSession session) {
        authUtils.firewall(session);

        log.info("Creating user {}", user);

        userRepo.createUser(user);

        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }


    /**
     *
     * @param idUser
     * @param idGroup
     * @param idContact
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{idUser}/groups/{idGroup}/friends/{idContact}")
    public @ResponseBody ResponseEntity<?> addContactToGroup(@PathVariable Long idUser, @PathVariable Long idGroup, @PathVariable Long idContact) {
        log.info("Adding friend {} to group {} ", idContact, idGroup);

        userRepo.addUserToGroup(idUser, idGroup, idContact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{id}/friends")
    public @ResponseBody ResponseEntity<?> addFriend(@RequestBody User user, @PathVariable Long id) {
        log.info("Adding friend to the current user");

        userRepo.addFriend(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param idUser
     * @param idContact
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{idUser}/friends/{idContact}")
    public @ResponseBody ResponseEntity<?> removeFriend(@PathVariable Long idUser, @PathVariable Long idContact) {
        log.info("Removing friend from user's list.");

        userRepo.removeFriend(idUser, idContact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param idUser
     * @param idGroup
     * @param idContact
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{idUser}/groups/{idGroup}/friends/{idContact}")
    public @ResponseBody ResponseEntity<?> removeFriendFromGroup(@PathVariable Long idUser, @PathVariable Long idGroup, @PathVariable Long idContact) {
        log.info("Removing friend from user's list.");

        userRepo.removeUserFromGroup(idUser, idGroup, idContact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param idUser
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{idUser}/dependencies/{idContact}")
    public @ResponseBody ResponseEntity<?> getDependeciesForUser(@PathVariable Long idUser, @PathVariable Long idContact) {
        log.info("Getting dependencies for {} ", idContact);

        return new ResponseEntity<>(
                userRepo.getDependenciesForUser(idUser, idContact),
                HttpStatus.OK);
    }



    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{idUser}/groups/{idGroup}")
    public @ResponseBody ResponseEntity removeGroup(@PathVariable Long idUser, @PathVariable Long idGroup) {
        log.info("Remove group");

        userRepo.removeGroup(idUser, idGroup);

        return new ResponseEntity<>(HttpStatus.OK);
    }




    /**
     * GET -> /users/:id : Get a specific user
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable Long id, HttpSession session) {
        authUtils.firewall(session);

        log.debug("REST request to User : {}", id);

        return Optional.ofNullable((User) userRepo.load(id))
                .map(user -> new ResponseEntity<>(
                        user,
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * PUT -> /users/:id : Update a specific user
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{id}")
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user, HttpSession session) {
        authUtils.firewall(session);

        log.debug("REST Update User : {}", id);

        userRepo.updateUser(id, user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST -> /users/:id/groups : Create a new group
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{id}/groups")
    public @ResponseBody ResponseEntity<?> createGroupForUser(@PathVariable Long id, @RequestBody Group g, HttpSession session) {
        authUtils.firewall(session);

        log.debug("REST request to create a group : {}", g);

        userRepo.addGroup(id, g);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
