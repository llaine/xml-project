package app.rest;


import app.dao.UserRepository;
import app.domain.User;
import app.factory.UserFactory;
import app.interfaces.IFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


/**
 * gs-actuator-service
 *
 * @author llaine
 * @package app.rest
 */

@Controller
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private UserRepository userRepo = new UserRepository();

    /**
     * GET -> /users : get all the users
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users")
    public @ResponseBody User getUsers(){

        // Testing the creation && the saving in XML.
        User u = userRepo.createClass();
        u.setFirstname("Foo");
        u.setLastname("bar");
        u.setEmail("foo@bar.baz");

        userRepo.save(u);

        log.debug("Getting Resource GET --> " + u.toString());

        return u;
    }

    /**
     * GET -> /users/:id : Get a specific user
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/users/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable Long id){
        log.debug("REST request to User : {}", id);

        return Optional.ofNullable((User) userRepo.load(id))
                .map(user -> new ResponseEntity<>(
                        user,
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }




}
