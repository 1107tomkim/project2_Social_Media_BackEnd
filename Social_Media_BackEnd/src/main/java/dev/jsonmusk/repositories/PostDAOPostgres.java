package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostDAOPostgres implements PostDAO {

    @Override
    public Post createPost(Post post) {
        // insert this new post into db

        try (Connection connection = ConnectionFactory.getConnection()) {
            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
            // INSERT INTO posts values(DEFAULT,'text7', 'username', 1, DEFAULT, NULL, '{}', default );
            String sql = "insert into posts values(default, ?, ?, ?, ?, ?, default, default)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getPostText());
            ps.setString(2, post.getUsername());
            ps.setInt(3, post.getUserId());
            ps.setTimestamp(4, newTimestamp);
            ps.setBytes(5, post.getPostPhoto());

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
            post.setUserId(rs.getInt("createdBy"));
            post.setUsername(rs.getString("createdByName"));
            post.setDate(rs.getTimestamp("date_created"));
            post.setPostPhoto(rs.getBytes("post_photo"));

            Array arr = rs.getArray("liked_by");
            post.setLiked_by(ArrayToIntArray(arr));

            arr = rs.getArray("disliked_by");
            post.setDisliked_by(ArrayToIntArray(arr));

            return post;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    int[] ArrayToIntArray (Array arr) {
        String str = arr.toString();
        int newlen = str.length();
        newlen -= 2;
        newlen -= (newlen-1)/2;
        int[] intArr = new int[newlen];
        int counter = 0;
        for (int i = 1; i < str.length() - 1; i+=2) {
            System.out.println(i + ": " + Integer.parseInt(String.valueOf(str.charAt(i))));
            intArr[counter] = Integer.parseInt(String.valueOf(str.charAt(i)));

            counter++;
        }
        return intArr;
    }
    @Override
    public List<Post> getFeed() {
        // this returns all posts
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from posts order by date_created";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Post> feed = new ArrayList<>();
            while(rs.next()){
                Post post = new Post();
                post.setPostText(rs.getString("post_text"));
                post.setPostId(rs.getInt("post_id"));
                post.setUserId(rs.getInt("createdBy"));
                post.setUsername(rs.getString("createdByName"));
                post.setDate(rs.getTimestamp("date_created"));
                post.setPostPhoto(rs.getBytes("post_photo"));


                Array arr = rs.getArray("liked_by");
                post.setLiked_by(ArrayToIntArray(arr));

                arr = rs.getArray("disliked_by");
                post.setDisliked_by(ArrayToIntArray(arr));


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
           // update posts set liked_by = array_append(liked_by, 3) where post_id = 1;
            String sql = "update posts set post_text = ?, createdby = ?, date_created = ?, post_photo = ? where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, post.getPostText());
            ps.setInt(2, post.getUserId());
            ps.setTimestamp(3, post.getDate());
            ps.setBytes(4, post.getPostPhoto());
            ps.setInt(5, post.getPostId());
            ps.executeUpdate();

            return post;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post likePost(Post post) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update posts set liked_by = array_append(liked_by, ?) where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getLiker());
            ps.setInt(2, post.getPostId());

            ps.executeUpdate();
            return post;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post dislikePost(Post post) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update posts set disliked_by = array_append(disliked_by, ?) where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getLiker());
            ps.setInt(2, post.getPostId());

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

    @Override
    public Post likeAmount(Post post) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select array_length(liked_by, 1) as liked, array_length(disliked_by, 1) as disliked from posts where post_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getPostId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Post post2 = new Post();
            post2.setLiked(rs.getInt("liked"));
            post2.setDisliked(rs.getInt("disliked"));
            return post2;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkLiked(Post post) {
        System.out.println(post.getPostId());
        System.out.println(post.getLiker());
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select exists(select from posts where post_id= ? and (?=any(liked_by) or ?=any(disliked_by)))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getPostId());
            ps.setInt(2, post.getLiker());
            ps.setInt(3, post.getLiker());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBoolean("exists");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
