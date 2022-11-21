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
        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);
        String json = ctx.body();
        Gson gson = new Gson();
        Post newPost = gson.fromJson(json, Post.class);

        newPost.setUsername(user.getUsername());
        newPost.setUserId(user.getId());

        try {
            Driver.postService.createPost(newPost);
            ctx.result("good");
            ctx.status(201);
        } catch (RuntimeException e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }


    };

    public Handler getPostbyIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("post_id"));
        Post gottenPost = Driver.postService.getPostById(id);
        Post postLikes = Driver.postService.likeAmount(gottenPost);
        gottenPost.setDisliked(postLikes.getDisliked());
        gottenPost.setLiked(postLikes.getLiked());
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

    public Handler likeHandler = (ctx) -> {
        // authorization done by routes ("/api/*"); no user login checking logic is necessary here

        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);
        String json = ctx.body();
        Gson gson = new Gson();
        Post likePost = gson.fromJson(json, Post.class);
        Post fullPost = Driver.postService.getPostById(likePost.getPostId());
        fullPost.setLiker(user.getId());
        Driver.postService.checkLiked(fullPost);
        System.out.println(fullPost);
        if(!Driver.postService.checkLiked(fullPost)){
            try{
                Driver.postService.likePost(fullPost);
                ctx.result("you liked this poost");
                ctx.status(200);
            }catch (RuntimeException e){
                System.out.println(e);
            }
        }
        else {
            ctx.result("you cant do that");
            ctx.status(400);
        }



    };
    public Handler dislikeHandler = (ctx) -> {
        // authorization done by routes ("/api/*"); no user login checking logic is necessary here

        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);

        String json = ctx.body();
        Gson gson = new Gson();
        Post likePost = gson.fromJson(json, Post.class);

        Post fullPost = Driver.postService.getPostById(likePost.getPostId());
        fullPost.setLiker(user.getId());
        Driver.postService.checkLiked(fullPost);

        System.out.println(fullPost);
        if(!Driver.postService.checkLiked(fullPost)) {
            try {
                Driver.postService.dislikePost(fullPost);
                ctx.result("you disliked this poost");
                ctx.status(200);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        } else {
            ctx.result("you cant do that");
            ctx.status(400);
        }

    };

}
