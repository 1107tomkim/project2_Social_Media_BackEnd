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

        int returnval = Driver.userService.login(user.getUsername(), user.getPassword());
        if (returnval == 2) {
            ctx.status(200);
            ctx.result("something is good " + returnval);
        }
        else if (returnval == 1) {
            ctx.status(401);
            ctx.result("something is bad " + returnval);
        }
        else {
            ctx.status(400);
            ctx.result("something is bad " + returnval);
        }
    };

    public Handler logoutHandler = (ctx) -> {

        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        int returnval = Driver.userService.logout(user.getUsername(), user.getPassword());
        if (returnval == 2) {
            ctx.status(200);
            ctx.result("logged out");
        }
        else {
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
