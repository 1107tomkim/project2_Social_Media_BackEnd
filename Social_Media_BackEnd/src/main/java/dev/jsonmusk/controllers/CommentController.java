package dev.jsonmusk.controllers;

import com.google.gson.Gson;
import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.User;
import io.javalin.http.Handler;

import java.util.List;

public class CommentController {



    public Handler createCommentHandler = (ctx) -> {
        System.out.println("creating comment...");
        String token = ctx.cookie("jwt");
        User user = Driver.userService.getUserByAuthToken(token);

        String json = ctx.body();
        Gson gson = new Gson();
        Comment newComment = gson.fromJson(json, Comment.class);

        newComment.setUserId(user.getId());
        Comment createdComment = Driver.commentService.createComment(newComment);
        ctx.result("comment successfully created!");
        System.out.println("comment successfully created! :\n" + createdComment);
        ctx.status(200);
    };


    public Handler getCommentByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Comment comment = Driver.commentService.getCommentById(id);
        Gson gson = new Gson();
        ctx.result(gson.toJson(comment));
        ctx.status(200);
    };

    public Handler getAllCommentsOfPostId = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("post_id"));
        List<Comment> comments = Driver.commentService.getCommentsOfPostId(id);
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        ctx.result(json);
    };

    public Handler updateCommentHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Comment comment = gson.fromJson(json, Comment.class);
        Comment updatedComment = Driver.commentService.updateComment(comment);
        ctx.result(gson.toJson(updatedComment));
        ctx.status(200);
    };

    public Handler getCommentByParentIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("comment_parent_id"));
        Comment comment = Driver.commentService.getCommentByParentId(id);
        Gson gson = new Gson();
        ctx.result(gson.toJson(comment));
        ctx.status(200);
    };

    public Handler deleteCommentHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("comment_id"));
        boolean result = Driver.commentService.deleteCommentById(id);
        if(result){
            ctx.status(204);
        }
        else{
            ctx.status(400);
            ctx.result("Could not process your delete request");
        }
    };
}
