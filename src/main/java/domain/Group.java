package domain;

import java.util.List;
import java.util.UUID;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package domain
 */
abstract class Group {
    private Long id;
    private String name;
    private List<User> members;

    private void addMember(User u) {
        this.members.add(u);
    }


}
