package app.authentification;

import app.dao.UserRepository;

import app.domain.User;
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
 * @package app.rest
 */

@Controller
@RequestMapping("/api")
public class Authentification {

    private final Logger log = LoggerFactory.getLogger(Authentification.class);
    private UserRepository userRepo = new UserRepository();

    /**
     * GET -> /users : get all the users
     * @return
     */
    @RequestMapping(value="/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> authenticate(@RequestParam String username, @RequestParam String password) {
        log.info("Connection tried with {} {}", username, password);

        return Optional.ofNullable(userRepo.findOneByUsername(username, password))
                .map(user -> new ResponseEntity<>(
                        user,
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }
}
