package app.rest;

import java.util.List;
import app.dao.GroupRepository;
import app.domain.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package app.rest
 */

@Controller
@RequestMapping("/api")
public class GroupResource {

    private final Logger log = LoggerFactory.getLogger(GroupResource.class);

    private GroupRepository groupRepo = new GroupRepository();

    /**
     * GET -> /users : get all the users
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/groups")
    public @ResponseBody
    List<Group> getUsers(){
        log.debug("Getting resource GET groups");
        return groupRepo.findAll();
    }



}
