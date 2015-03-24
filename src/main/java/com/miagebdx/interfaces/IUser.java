package com.miagebdx.interfaces;

import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.interfaces
 */
public interface IUser extends Savable {

    public void addFriends(User u);

    public void addGroup(Group g);

    public String getUniqueFileName();

}
