package dev.jsonmusk.controllers;

import com.google.gson.Gson;
import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.entities.User;
import io.javalin.http.Handler;

import java.util.List;

public class PostController {


    public Handler createPostHandler = (ctx) -> {
    // authorization done by routes ("/api/*"); no user login checking logic is necessary here
        String json = ctx.body();
        Gson gson = new Gson();
        Post newPost = gson.fromJson(json, Post.class);
        User userByParam = Driver.userService.getUserByUsername(newPost.getUsername());
//        if (userByParam.isLoggedIn()){
            newPost.setUserId(userByParam.getId());
            Driver.postService.createPost(newPost);
//            ctx.result("You just posted again. maybe take a break.");
//            ctx.status(400);
//        }
//        else {
//            System.out.println("please log in");
//            ctx.result("Please log in to post");
//            ctx.status(400);
//        }

    };

    public Handler getPostbyIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("post_id"));
        Post gottenPost = Driver.postService.getPostById(id);
        if (gottenPost != null) {
            Gson gson = new Gson();
            ctx.status(200);
            ctx.result(gson.toJson(gottenPost, Post.class));
        }
        else {
            ctx.result("ERROR!! CAN NOT FIND POST!!");
            ctx.status(400);
        }

    };

    public Handler getFeedHandler = (ctx) -> {
      // Will return all the posts in Chronological order
        List<Post> feed = Driver.postService.getFeed();

        if (feed != null) {
            Gson gson = new Gson();
            ctx.status(200);
            ctx.result(gson.toJson(feed));
        }
        else {
            ctx.result("ERROR!! CAN NOT FIND FEED!!");
            ctx.status(400);
        }
    };

}
