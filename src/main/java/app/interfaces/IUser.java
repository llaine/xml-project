package app.interfaces;

import app.domain.Group;
import app.domain.User;

/**
 * projetXML
 *
 * @author llaine
 * @package app.interfaces
 */
public interface IUser extends Savable {

    public void addFriends(User u);

    public void addGroup(Group g);

    public String getUniqueFileName();

}
