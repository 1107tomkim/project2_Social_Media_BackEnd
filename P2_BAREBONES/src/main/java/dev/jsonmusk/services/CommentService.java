package dev.jsonmusk.services;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;

import java.util.List;

public interface CommentService {

    //CREATE
    Comment createComment(Comment comment);

    //READ
    Comment getCommentById(int id);
    Comment getCommentParent(Comment comment);
    List<Comment> getCommentChildrenOfCommentId(int id);
    List<Comment> getCommentsOfPostId(int id);

    //UPDATE
    Comment updateComment(Comment comment);

    //DELETE

    boolean deleteCommentById(int id);



}
