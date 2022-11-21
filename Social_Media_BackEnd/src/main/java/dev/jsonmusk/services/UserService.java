package dev.jsonmusk.services;

import dev.jsonmusk.entities.Session;
import dev.jsonmusk.entities.User;

import java.util.List;


public interface UserService {

    //CREATE
    User createUser(User user);

    //READ
    User getUserById(int id);
    User getUserByUsername(String username);


    //UPDATE
    User updateUser(User user);

    Session login(String username, String password);
    User getUserByAuthToken(String token);

    Session getSessionByUserId(int id);

    boolean authorize (String token, int userId);

    User logout(User user);

    List<User> searchUsers(User user);

    //DELETE


}
