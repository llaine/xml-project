package com.miagebdx.rest;


import com.miagebdx.authentification.AuthUtils;
import com.miagebdx.dao.UserRepository;
import com.miagebdx.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


/**
 * gs-actuator-service
 *
 * @author llaine
 * @package com.miagebdx.rest
 */

@RestController
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
    public @ResponseBody ResponseEntity<List<User>> getUsers(HttpSession session){
        authUtils.firewall(session);

        log.debug("Getting Resource User");

        return Optional.ofNullable(userRepo.findAll())
                .map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    /**
     * POST -> /users : Create a specific User
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
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable Long id, @ModelAttribute User user, HttpSession session) {
        authUtils.firewall(session);

        log.debug("REST Update User : {}", id);

        userRepo.updateUser(id, user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
