package app.domain;

import java.util.List;

/**
 * gs-actuator-service
 *
 * @author llaine
 * @package app.domain
 */
abstract class Group {
    private Long id;
    private String name;
    private List<User> members;

    private void addMember(User u) {
        this.members.add(u);
    }


}
