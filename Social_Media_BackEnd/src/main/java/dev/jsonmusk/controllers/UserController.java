package dev.jsonmusk.controllers;

import com.google.gson.Gson;
import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Session;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.repositories.UserDAO;
import io.javalin.http.*;

import java.util.List;

public class UserController {

    public Handler loginHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);


        Session session = Driver.userService.login(user.getUsername(), user.getPassword());
        // ctx.json(authorization);
        Cookie cookie = new Cookie("jwt",
                session.getToken(),
                "/", -1
                , true, 1, true, null, null, SameSite.LAX);
        ctx.cookie(cookie);

    };

    public Handler logoutHandler = (ctx) -> {

        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);
        if (user == null) {
            ctx.status(400);
            ctx.result("No user");
        } else {

            // switch user repo islogged boolean value
            // AND delete from session repo
            User returnUser = Driver.userService.logout(user);

            if (returnUser != null) {

                //get rid of jwt cookie
                eraseCookie(ctx, "jwt");

                ctx.status(200);
                ctx.result("logged out");
            }
            else {
                ctx.status(400);
                ctx.result("Could not log out");
            }
        }



    };


    private void eraseCookie(Context ctx, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "", "/", 0,
                true, 1, true, null, null, SameSite.STRICT);
        ctx.cookie(cookie);

    }

    public Handler authorizeHandler = (ctx) -> {

        // gets the current user based on the web token in browser cookies
        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);

        if (token == null){
            throw new ForbiddenResponse("NO TOKEN!");
        }
        boolean result = Driver.userService.authorize(token, user.getId());
        if (!result){
            throw new ForbiddenResponse("INVALID AUTHORIZATION!");
        }


    };

    public Handler getUserHandler = (ctx) -> {

        // gets the current user based on the web token in browser cookies
        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);

        Gson gson = new Gson();
        String json = gson.toJson(user, User.class);
        ctx.result(json);

    };


    public Handler createUserHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        User createdUser = Driver.userService.createUser(user);
        if( createdUser != null) {
            ctx.status(200);
            ctx.result(gson.toJson(createdUser));
        }
        else {
            ctx.status(404);
            ctx.result("Account not created!");
        }
    };

    // Later when MVP is met, think about options to search for user based on
    // their username. Will implement V  handler
    // public Handler getUserByUsernameHandler = (ctx)

    public Handler getUserByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = Driver.userService.getUserById(id);
        Gson gson = new Gson();
        ctx.result(gson.toJson(user));
        ctx.status(200);
    };

    public Handler updateUserHandler = (ctx) -> {
        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);

        String json = ctx.body();
        Gson gson = new Gson();
        User jsonUser = gson.fromJson(json, User.class);

        System.out.println(jsonUser);

        user.setUsername(jsonUser.getUsername());
        user.setFirstname(jsonUser.getFirstname());
        user.setLastname(jsonUser.getLastname());
        user.setEmail(jsonUser.getEmail());
        user.setPhone_number(jsonUser.getPhone_number());
        user.setAge_num(jsonUser.getAge_num());

        System.out.println(user);

        User updatedUser = Driver.userService.updateUser(user);
        System.out.println(updatedUser);
        if (updatedUser != null) {
            ctx.result(gson.toJson(updatedUser));
            ctx.status(200);
        } else {
            ctx.result("Cannot change user");
            ctx.status(400);
        }
    };



    public Handler searchUser = (ctx) -> {
        String username = ctx.pathParam("username").trim();
        System.out.println(username);

        List<User> searchList = Driver.userService.searchUsers(username);
        System.out.println("count: " + searchList.size());
        for (User u : searchList)
            System.out.println(u.toString());
        if (searchList != null && !searchList.isEmpty()) {
            ctx.status(200);
            Gson gson = new Gson();
            ctx.result(gson.toJson(searchList));
        }
        else {
            ctx.result("cannot find users");
            ctx.status(400);
        }
    };

}
