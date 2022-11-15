package dev.jsonmusk.services;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.repositories.CommentDAO;
import dev.jsonmusk.repositories.PostDAO;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;
    public CommentServiceImpl(CommentDAO commentDAO){  this.commentDAO = commentDAO;  }

    @Override
    public Comment createComment(Comment comment) {
        return this.commentDAO.createComment(comment);
    }

    @Override
    public Comment getCommentById(int id) {
        return this.commentDAO.getCommentById(id);
    }

    @Override
    public Comment getCommentParent(Comment comment) {
        return this.commentDAO.getCommentParent(comment);
    }

    @Override
    public List<Comment> getCommentChildrenOfCommentId(int id) {
        return this.commentDAO.getCommentChildrenOfCommentId(id);
    }

    @Override
    public List<Comment> getCommentsOfPostId(int id) {
        return this.commentDAO.getCommentsOfPostId(id);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return this.commentDAO.updateComment(comment);
    }

    @Override
    public boolean deleteCommentById(int id) {
        return this.commentDAO.deleteCommentById(id);
    }
}
