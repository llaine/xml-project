package com.miagebdx.utils;

import com.miagebdx.dao.UserRepository;
import com.miagebdx.domain.User;
import junit.framework.TestCase;

public class UserUtilsTest extends TestCase {

    UserRepository userRepository = new UserRepository();
    UserUtils userUtils = UserUtils.getInstance();

    public void testGetInstance() throws Exception {
        UserUtils userUtils = UserUtils.getInstance();
        assertEquals("com.miagebdx.utils.UserUtils", userUtils.getClass().getName());

    }

    public void testDiff() throws Exception {

        User userOne = userRepository.createClass("firstname", "lastname", "password", "email", "birthdayDate");
        User userUpdated = userRepository.createClass("michel", "lastname", "pwd", "email", "birthdayDate");


        User newUser = userUtils.diff(userOne, userUpdated);


        assertEquals(userUpdated.equals(newUser), true);

    }

    public void testIsValidUser() throws Exception {
        User inValidUser = userRepository.createClass();
        User validUser = userRepository.createClass("firstname", null, "password", "email", null);

        Boolean isTrue = true;
        Boolean isFalse = false;

        assertEquals(userUtils.isValidUser(inValidUser), isFalse);
        assertEquals(userUtils.isValidUser(validUser), isTrue);

    }
}