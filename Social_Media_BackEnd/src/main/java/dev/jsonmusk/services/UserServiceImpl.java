package dev.jsonmusk.services;

import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Session;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.managers.TokenManager;
import dev.jsonmusk.repositories.SessionDAO;
import dev.jsonmusk.repositories.UserDAO;

import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private TokenManager tokenManager;

    private SessionDAO sessionDAO;
    public UserServiceImpl(UserDAO userDAO, SessionDAO sessionDAO, TokenManager tokenManager){
        this.userDAO = userDAO;

        this.sessionDAO = sessionDAO;

        this.tokenManager = tokenManager;


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
    public Session login(String username, String password) {
        // int returnval = 0;
        User checkUser = Driver.userService.getUserByUsername(username);
        System.out.println(checkUser);
        if (checkUser != null) {
            if (checkUser.getPassword().equals(password)) {
                //  returnval = 2;


                checkUser.setLoggedIn(true);
                User loggedInUser = this.userDAO.updateUserLogin(checkUser);

                // authorization stuff
                //   String userId = user.getUserId();
                String token = tokenManager.issueTokenToUserId(checkUser.getId());

                System.out.println(token);
                Session session = new Session(checkUser.getId(), token);


                this.sessionDAO.createAuth(checkUser, session);


                return session;
            }
        }  else {
            //  returnval = 0;
            return null;
        }
        //  System.out.println(returnval);
        //   return returnval;
        return null;
    }
    @Override
    public User logout(User user) {

        int returnval = 0;
        if (user != null) {
            user.setLoggedIn(false);
            User loggedOutUser = this.userDAO.updateUserLogin(user);

            this.sessionDAO.deleteAuthByUserId(user.getId());

            return loggedOutUser;
        }
        else {
            return null;
        }

    }

    @Override
    public List<User> searchUsers(User user) {
        return this.userDAO.searchUser(user);
    }

    @Override
    public User getUserByAuthToken(String token) {


        int id = this.sessionDAO.getUserIdByToken(token);

        if (id <= 0 )
            return null;

        User user = this.userDAO.getUserById(id);
        return user;
    }

    @Override
    public Session getSessionByUserId(int id) {

        Session session = this.sessionDAO.getSessionByUserId(id);

        return session;
    }


    @Override
    public boolean authorize(String token, int userId) {
        boolean result = tokenManager.authorize(token, userId);
        return result;
    }

}
