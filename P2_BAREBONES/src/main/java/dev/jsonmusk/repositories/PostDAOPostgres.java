package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOPostgres implements PostDAO {

    @Override
    public Post createPost(Post post) {
        // insert this new post into db

        try (Connection connection = ConnectionFactory.getConnection()) {
            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
            String sql = "insert into posts values(default, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getPostText());
            ps.setString(2, post.getUsername());
            ps.setInt(3, post.getUserId());
            ps.setTimestamp(4, newTimestamp);
            ps.setBytes(5, post.getPostPhoto());
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            post.setPostId(rs.getInt("post_id"));
            return post;


        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public Post getPostById(int id) {
        // select the post from db with corresponding id
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from posts where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Post post = new Post();
            post.setPostText(rs.getString("post_text"));
            post.setPostId(id);
            post.setUserId(rs.getInt("createdby"));
            post.setDate(rs.getTimestamp("date_created"));
            post.setPostPhoto(rs.getBytes("post_photo"));
            post.setLiked(rs.getInt("liked"));
            post.setDisliked(rs.getInt("disliked"));
            return post;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getFeed() {
        // this returns all posts
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from posts order by created_at";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Post> feed = new ArrayList<>();
            while(rs.next()){
                Post post = new Post();
                post.setPostText(rs.getString("post_text"));
                post.setPostId(rs.getInt("post_id"));
                post.setUserId(rs.getInt("created_by"));
                post.setDate(rs.getTimestamp("created_at"));
                post.setPostPhoto(rs.getBytes("post_photo"));
                post.setLiked(rs.getInt("liked"));
                post.setDisliked(rs.getInt("disliked"));
                feed.add(post);
            }
            return feed;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Post updatePost(Post post) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update posts set post_text = ?, createdby = ?, date_created = ?, post_photo = ?, liked = ?, disliked = ? where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, post.getPostText());
            ps.setInt(2, post.getUserId());
            ps.setTimestamp(3, post.getDate());
            ps.setBytes(4, post.getPostPhoto());
            ps.setInt(5, post.getLiked());
            ps.setInt(6, post.getDisliked());
            ps.setInt(7, post.getPostId());
            ps.executeUpdate();

            return post;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletePostById(int id) {
        // delete post from db
        return false;
    }
}
