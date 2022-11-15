package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.User;

import java.util.List;


public interface UserDAO {

    //CREATE

    // Used to create user
    User createUser(User user);
    //READ

    // Used to get user by their id ( In the Database)
    User getUserById(int id);
    User getUserByUsername(String username);

    //UPDATE
    User updateUser(User user);

    User updateUserLogin(User user);

    void logout();

    //DELETE



}
