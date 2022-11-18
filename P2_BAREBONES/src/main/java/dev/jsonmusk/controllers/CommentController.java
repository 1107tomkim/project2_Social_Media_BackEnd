package dev.jsonmusk.controllers;

import com.google.gson.Gson;
import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Comment;
import io.javalin.http.Handler;

import java.util.List;

public class CommentController {

    public Handler createCommentHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Comment newComment = gson.fromJson(json, Comment.class);
        Comment createdComment = Driver.commentService.createComment(newComment);
        ctx.result("comment successfully created!");
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
}
