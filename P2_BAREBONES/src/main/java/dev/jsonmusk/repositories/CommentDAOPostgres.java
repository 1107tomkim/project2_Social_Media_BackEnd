package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOPostgres implements CommentDAO{


    @Override
    public Comment createComment(Comment comment) {
        // insert new comment into db
        try (Connection connection = ConnectionFactory.getConnection()) {
            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
            //INSERT INTO comment_table VALUES (DEFAULT, 'this is a comment', 1, 9, DEFAULT, 0, 0);
            String sql = "insert into comment_table values(default, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comment.getText());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getPostId());
            ps.setInt(4, comment.getParentId());
            //Date Created datatype should change from int to timestamp
            ps.setTimestamp(5,  new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            comment.setComment_id(rs.getInt("comment_id"));
            return comment;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment getCommentById(int id) {
        // select the comment from db with corresponding id
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from comment_table where comment_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // create comment object from the attributes in the table
            Comment gottenComment = new Comment();
            gottenComment.setComment_id(id);
            gottenComment.setText(rs.getString("comment_text"));
            gottenComment.setUserId(rs.getInt("createdBy"));
            gottenComment.setPostId(rs.getInt("comment_post_id"));
            //Added comment_parent_id
            gottenComment.setPostId(rs.getInt("comment_parent_id"));
            gottenComment.setDate(rs.getTimestamp("date_created"));
            gottenComment.setLiked(rs.getInt("liked"));
            gottenComment.setDisliked(rs.getInt("disliked"));
            // return the object if found
            return gottenComment;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return nothing if not found
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        // update the comment in db with corresponding id
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update comment_table set comment_text = ?, createdBy = ?, comment_post_id = ?, comment_parent_id = ?, date_created = default, liked = ?, disliked = ? where comment_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, comment.getText());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getPostId());
            ps.setInt(4, comment.getParentId());
            // ps.setTimestamp(5, comment.getDate());
            ps.setInt(5, comment.getLiked());
            ps.setInt(6, comment.getDisliked());
            ps.setInt(7, comment.getComment_id());

            ps.executeUpdate();

            return comment;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
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
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from comment_table where comment_post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            // create a list of comments (for that post)
            List<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                Comment comment = new Comment(  rs.getInt("comment_id"),
                        rs.getInt("createdBy"),
                        rs.getString("comment_text"),
                        rs.getTimestamp("date_created"),
                        rs.getInt("comment_parent_id"),
                        rs.getInt("comment_post_id"),
                        rs.getInt("liked"),
                        rs.getInt("disliked"));
                comments.add(comment);
            }
            return comments;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteCommentById(int id) {
        // delete comment from db
        return false;
    }

}



//package dev.jsonmusk.repositories;
//
//import dev.jsonmusk.entities.Comment;
//import dev.jsonmusk.util.ConnectionFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CommentDAOPostgres implements CommentDAO{
//
//
//    @Override
//    public Comment createComment(Comment comment) {
//        // insert new comment into db
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
//            //INSERT INTO comment_table VALUES (DEFAULT, 'this is a comment', 1, 9, DEFAULT, 0, 0);
//            String sql = "insert into comment_table values(default, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, comment.getText());
//            ps.setInt(2, comment.getUserId());
//            ps.setInt(3, comment.getPostId());
//            ps.setTimestamp(4, newTimestamp);
//            ps.setInt(5, 0);
//            ps.setInt(6, 0);
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//
//            comment.setComment_id(rs.getInt("comment_id"));
//            return comment;
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Comment getCommentById(int id) {
//        // select the comment from db with corresponding id
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "select * from comment_table where comment_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//
//            // create comment object from the attributes in the table
//            Comment gottenComment = new Comment();
//            gottenComment.setComment_id(id);
//            gottenComment.setText(rs.getString("comment_text"));
//            gottenComment.setUserId(rs.getInt("createdBy"));
//            gottenComment.setPostId(rs.getInt("comment_post_id"));
//            gottenComment.setDate(rs.getTimestamp("date_created"));
//            gottenComment.setLiked(rs.getInt("liked"));
//            gottenComment.setDisliked(rs.getInt("disliked"));
//            // return the object if found
//            return gottenComment;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        // return nothing if not found
//        return null;
//    }
//
//    @Override
//    public Comment updateComment(Comment comment) {
//        // update the comment in db with corresponding id
//        return null;
//    }
//
//    @Override
//    public Comment getCommentParent(Comment comment) {
//        // get the parent comment from db with corresponding child comment
//        return null;
//    }
//
//    @Override
//    public List<Comment> getCommentChildrenOfCommentId(int id) {
//        // get the comments from db with corresponding parent comment
//        return null;
//    }
//
//    @Override
//    public List<Comment> getCommentsOfPostId(int id) {
//        // given a post id, return all of its comments
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "select * from comment_table where comment_post_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            // create a list of comments (for that post)
//            List<Comment> comments = new ArrayList<>();
//            while(rs.next()) {
//                Comment comment = new Comment(  rs.getInt("comment_id"),
//                                                rs.getInt("createdBy"),
//                                                rs.getString("comment_text"),
//                                                rs.getTimestamp("date_created"),
//                                                rs.getInt("comment_parent_id"),
//                                                rs.getInt("comment_post_id"),
//                                                rs.getInt("liked"),
//                                                rs.getInt("disliked"));
//                comments.add(comment);
//            }
//            return comments;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteCommentById(int id) {
//        // delete comment from db
//        return false;
//    }
//
//}
