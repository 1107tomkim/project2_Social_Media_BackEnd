package dev.jsonmusk.controllers;

import com.google.gson.Gson;
import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.repositories.UserDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class UserController {

    public Handler loginHandler = (ctx) -> {

        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        //System.out.println("this is what they entered: " + user);
        User checkPassword = Driver.userService.getUserByUsername(user.getUsername());
        //System.out.println("this is what we are expecting: " + checkPassword);
        System.out.println(checkPassword.getPassword());
        System.out.println(user.getPassword());

        if (user.getPassword().equals(checkPassword.getPassword())){
            // update user login
            //
            Driver.userService.login(user);
            ctx.result("something good happened");
        }
        else {
            ctx.result("something bad");
        }
    };

    public Handler logoutHandler = (ctx) -> {

        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        System.out.println(user);
        //String username = ctx.pathParam("username");
        User userByParam = Driver.userService.getUserByUsername(String.valueOf(user));
        System.out.println(userByParam);
        if (userByParam.isLoggedIn()){
            User fullUser = Driver.userService.getUserByUsername(String.valueOf(userByParam));
            System.out.println("logoutHandler objects prints:  "+ fullUser);
            Driver.userService.logout(fullUser);
            ctx.status(200);
            ctx.result("logged out");
        }
        else{
            ctx.status(400);
            ctx.result("Could not log out");
        }
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
        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        User updatedUser = Driver.userService.updateUser(user);
        ctx.result(gson.toJson(updatedUser));
        ctx.status(200);
    };


}
