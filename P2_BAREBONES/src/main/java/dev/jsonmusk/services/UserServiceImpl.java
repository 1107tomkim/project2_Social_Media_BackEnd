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




//    @Override
//    public int login(String username, String password) {
//        int returnval = 0;
//        User checkUser = Driver.userService.getUserByUsername(username);
//        if (checkUser.isLoggedIn() == false) {
//            if (checkUser.getPassword().equals(password)) {
//                returnval = 2;
//                checkUser.setLoggedIn(true);
//                System.out.println("this is checkUser"+ checkUser);
//
//                // update user login?
//
//                //User loggedInUser = this.userDAO.updateUserLogin(checkUser);
//            } else {
//                System.out.println("incorrect password or email");
//                returnval = 1;
//            }
//        } else {
//            System.out.println("you are already logged in");
//            returnval = 0;
//        }
//        //System.out.println(checkUser);
//        //System.out.println(returnval);
//        return returnval;
//    }

    @Override
    public User logout(User user) {
        //int returnval = 0;
        //User checkUser = Driver.userService.getUserByUsername(user.getUsername());

        System.out.println("userImpl object is printing: "+user);
        if (user.isLoggedIn()) {
            //checkUser.setLoggedIn(false);
            user.setLoggedIn(false);
            return user;
        }
        else {
            System.out.println("you are not logged in");
        }
        return user;
    }

}
