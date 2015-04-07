import com.miagebdx.dao.GroupRepository;
import com.miagebdx.dao.UserRepository;
import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * projetXML
 *
 * @author llaine
 * @package PACKAGE_NAME
 */
public class MainTests {

    public static void main(String[] args) {

        UserRepository userRepo = new UserRepository();

        GroupRepository groupRepo = new GroupRepository();

        User u = userRepo.findOneByUsername("toto", "password");


        /*
        // Create some users
        User toto = userRepo.createClass("Louis", "Lainé", "password", "email", "19/08/1993");
        User tati = userRepo.createClass("Toto", "Lainé", "password", "email", "20/08/1993");
        User tata = userRepo.createClass("Toto", "Lainé", "password", "email", "20/08/1993");
        User titi = userRepo.createClass("Toto", "Lainé", "password", "email", "20/08/1993");
        User tete = userRepo.createClass("Toto", "Lainé", "password", "email", "20/08/1993");

        toto.addFriend(tata);
        toto.addFriend(tati);

        List members = new ArrayList<>();

        members.add(tete);
        members.add(titi);

        Group newGroup = groupRepo.createClass("Group de ouf", members);

        toto.addGroup(newGroup);

        toto.getGroups().stream().forEach(group -> System.out.println(group.getId() + " - " + group.getName() + " - " + group.getMembers().size()));

        Optional<Group> g = toto.getGroup(newGroup.getId());

        userRepo.save(toto);

        groupRepo.save(newGroup);
        */



    }
}
