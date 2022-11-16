package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentDAOTest {

    private CommentDAO commentDAO = new CommentDAOPostgres();

    @Test
    void createComment() {
        // make a new comment object
        Comment comment = new Comment(0, 1, "test2", null, 0, 9, 0 , 0);
        System.out.println(comment);
        // pass new comment to the DAO to store it
        Comment createdComment = commentDAO.createComment(comment);
        System.out.println(createdComment);
        // check if successful
        Assertions.assertNotNull(createdComment);
        Assertions.assertEquals("test2", createdComment.getText());
    }

    @Test
    void getCommentById() {
        // get a comment from the DAO with id of 2
        Comment gottenComment = commentDAO.getCommentById(2);
        System.out.println(gottenComment);
        // if DAO finds it, it will exist, otherwise it will be null
        Assertions.assertNotNull(gottenComment);
    }

    @Test
    void getPostCommentsByPostId() {
        // get a list of comments that all have the same post id
        List<Comment> postComments = commentDAO.getCommentsOfPostId(9);
        for (Comment c : postComments)
            System.out.println(c);
        // to pass the test, there must be a list of comments
        Assertions.assertNotNull(postComments);
    }
    @Test
    void updateComment() {
    }

    @Test
    void getCommentParent() {
    }

    @Test
    void getCommentChildrenOfCommentId() {
    }

    @Test
    void getCommentsOfPostId() {
    }

    @Test
    void deleteCommentById() {
    }
}