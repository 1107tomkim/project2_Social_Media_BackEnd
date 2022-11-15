package dev.jsonmusk.services;

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

    int login(String username, String password);

    int logout(String username, String password);

    //DELETE


}
