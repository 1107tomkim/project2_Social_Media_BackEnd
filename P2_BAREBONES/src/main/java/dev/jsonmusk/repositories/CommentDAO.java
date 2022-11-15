package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;

import java.util.List;

public interface CommentDAO {

    //CREATE
    Comment createComment(Comment comment);
    //READ
    Comment getCommentById(int id);

    //UPDATE
    Comment updateComment(Comment comment);
    Comment getCommentParent(Comment comment);
    List<Comment> getCommentChildrenOfCommentId(int id);
    List<Comment> getCommentsOfPostId(int id);

    //DELETE

    boolean deleteCommentById(int id);

}
