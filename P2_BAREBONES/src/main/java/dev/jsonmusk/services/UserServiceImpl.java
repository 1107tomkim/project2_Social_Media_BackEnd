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
    public User login(User user) {
        return this.userDAO.login(user);
    }

    @Override
    public User logout(User user) {
        if (user.isLoggedIn()) {
            user.setLoggedIn(false);
            userDAO.logout(user);
            return user;
        }
        else {
            System.out.println("you are not logged in");
        }
        return user;
    }

}
