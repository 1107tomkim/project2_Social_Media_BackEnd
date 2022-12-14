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
    List<User> searchUser(String username);
    //UPDATE
    User updateUserLogin(User user);
    User updateUser(User user);
    User login(User user);

    User logout(User user);

    //DELETE



}
