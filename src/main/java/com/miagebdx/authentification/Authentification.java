package com.miagebdx.authentification;

import com.miagebdx.dao.UserRepository;

import com.miagebdx.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.rest
 */

@Controller
@RequestMapping("/api")
public class Authentification {

    private final Logger log = LoggerFactory.getLogger(Authentification.class);
    private UserRepository userRepo = new UserRepository();
    private AuthUtils authUtils = AuthUtils.getInstance();

    /**
     * GET -> /authenticate :Authenticate with the right username and password.
     * @return
     */
    @RequestMapping(value="/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> authenticate(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // User is already connected.
        if(session.getAttribute("user") != null) return new ResponseEntity<>((User)session.getAttribute("user"), HttpStatus.OK);

        log.info("Connection tried with {} {}", username, password);

        User u = userRepo.findOneByUsername(username, password);

        // Create a session with credentials.
        if(null != u) {
            authUtils.bootstrapCredentials(u, session);
        }

        return Optional.ofNullable(u)
                .map(user -> new ResponseEntity<>(
                        user,
                        HttpStatus.OK
                )).orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }

    /**
     * GET -> /authenticate :Authenticate with the right username and password.
     * @return
     */
    @RequestMapping(value="/check/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> checkIfUserNameExists(@PathVariable String name, HttpSession session) {
        authUtils.firewall(session);

        return new ResponseEntity<>(
                    userRepo.checkIfUsernameExists(name),
                    HttpStatus.OK
                );

    }

        /**
         * GET -> /register : Register a user.
         * @return
         */
    @RequestMapping(value="/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> register(@RequestBody User u){

        log.info("Trying to create a new user {} ", u);

        User user = userRepo.createUser(u);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    /**
     * GET -> /register : Register a user.
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> logout(HttpSession session){

        log.info("Disconnecting current user");

        authUtils.freeSession(session);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
