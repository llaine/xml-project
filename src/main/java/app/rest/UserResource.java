package app.rest;


import app.domain.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * gs-actuator-service
 *
 * @author llaine
 * @package app.rest
 */

@Controller
@RequestMapping("/users")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody User sayHello(){
        User u = new User("Toto", "tata", "pwd", "email", "19/08/1993");
        log.debug("Getting Resource GET --> " + u.toString());
        return u;
    }
}
