package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;

import java.util.List;

public interface PostDAO {

    //CREATE

    // Used to simply create posts
    Post createPost(Post post);
    //READ

    // This is what we are using to grab specific post by id
    // We will use this implementation when we want to grab
    // a specific feed I.E -> When we want to only see a certain feed
    Post getPostById(int id);


    // This will be what we use to grab all the feeds regardless of the users
    // Feeds as in all the post that have been made
    List<Post> getFeed();

    //UPDATE
    Post updatePost(Post post);

    //DELETE

    boolean deletePostById(int id);



}
