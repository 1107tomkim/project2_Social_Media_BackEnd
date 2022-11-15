package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;

import java.util.List;

public class CommentDAOPostgres implements CommentDAO{


    @Override
    public Comment createComment(Comment comment) {
        // insert new comment into db
        return null;
    }

    @Override
    public Comment getCommentById(int id) {
        // select the comment from db with corresponding id
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        // update the comment in db with corresponding id
        return null;
    }

    @Override
    public Comment getCommentParent(Comment comment) {
        // get the parent comment from db with corresponding child comment
        return null;
    }

    @Override
    public List<Comment> getCommentChildrenOfCommentId(int id) {
        // get the comments from db with corresponding parent comment
        return null;
    }

    @Override
    public List<Comment> getCommentsOfPostId(int id) {
        // given a post id, return all of its comments
        return null;
    }

    @Override
    public boolean deleteCommentById(int id) {
        // delete comment from db
        return false;
    }

}
