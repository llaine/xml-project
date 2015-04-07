package com.miagebdx.utils;

import com.miagebdx.dao.GroupRepository;
import com.miagebdx.dao.UserRepository;
import com.miagebdx.domain.Group;
import com.miagebdx.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.utils
 */
public class MainTest {

    public static void main(String[] args) {

        System.out.println("Test");

        UserRepository userRepo = new UserRepository();

        // Create me
        User louis = userRepo.createClass("Louis", "Lainé", "password", "email", "19/08/1993");

        for (int i = 0; i < 2; i++) {

            User user = userRepo.createClass("nom"+i, "prenom"+i, "password"+i, "email"+i, "19/08/19"+i);

            user.addFriend(louis);

            userRepo.save(user);
        }


        System.out.println(userRepo.findAll().size());

    }
}
