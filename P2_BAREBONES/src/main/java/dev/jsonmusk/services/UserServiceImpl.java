package dev.jsonmusk.services;

import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.repositories.UserDAO;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }


    @Override
    public User createUser(User user) {
        return this.userDAO.createUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        return this.userDAO.updateUser(user);
    }

    @Override
    public int login(String username, String password) {
        int returnval = 0;
        User checkUser = Driver.userService.getUserByUsername(username);
        if (checkUser != null) {
            if (checkUser.getPassword().equals(password)) {
                returnval = 2;
                checkUser.setLoggedIn(true);
                User loggedInUser = this.userDAO.updateUserLogin(checkUser);
            } else {
                returnval = 1;
            }
        } else {
            returnval = 0;
        }
        System.out.println(returnval);
        return returnval;
    }

    @Override
    public int logout(String username, String password) {
        int returnval = 0;
        User checkUser = Driver.userService.getUserByUsername(username);
        if (checkUser != null) {
            checkUser.setLoggedIn(false);
            User loggedInUser = this.userDAO.updateUserLogin(checkUser);
            returnval = 2;
        }
        else {
            returnval = 1;
        }
        return returnval;
    }

}
