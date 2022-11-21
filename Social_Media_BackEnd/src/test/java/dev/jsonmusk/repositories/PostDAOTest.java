package dev.jsonmusk.repositories;

import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Post;
import io.javalin.http.util.JsonEscapeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDAOTest {

    static PostDAO postDAO = new PostDAOPostgres();

    @Test
    void createPost() {
        Post newPost = new Post();
        newPost.setUserId(1);
        newPost.setPostText("text");
        newPost.setUsername("creator");
        Post savedPost = postDAO.createPost(newPost);
        Assertions.assertNotNull(savedPost);
    }

    @Test
    void getPostById() {
        Post newPost = postDAO.getPostById(2);
        Assertions.assertEquals(2, newPost.getPostId());
    }

    @Test
    void getFeed() {
        List<Post> feed = postDAO.getFeed();
        for(Post p : feed){
            System.out.println(p);
        }
        Assertions.assertNotNull(feed);
    }

    @Test
    void updatePost() {
        Post post = postDAO.getPostById(2);
        System.out.println(post);
        post.setLiked(200);
        Post updatedPost = postDAO.updatePost(post);
        System.out.println(updatedPost);
        Assertions.assertEquals(200, updatedPost.getLiked());

    }

    @Test
    void deletePostById() {

    }

    @Test
    void likePost() {
        Post newPost = postDAO.getPostById(9);
        newPost.setLiker(23);
        System.out.println(newPost);
     //   postDAO.likePost(newPost);
        Post likedPost = postDAO.getPostById(9);
        System.out.println(likedPost);
        int[] intarr = likedPost.getLiked_by();
        int last = intarr[intarr.length-1];
        Assertions.assertEquals(23, last);
    }

}