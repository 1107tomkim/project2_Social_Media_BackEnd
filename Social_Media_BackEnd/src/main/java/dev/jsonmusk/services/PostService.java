package dev.jsonmusk.services;

import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.entities.User;

import java.util.List;

public interface PostService {

    //CREATE
    Post createPost(Post post);

    //READ
    Post getPostById(int id);

    List<Post> getFeed();

    //UPDATE
    Post updatePost(Post post);
    Post likePost(Post post, User user);
    Post dislikePost(Post post, User user);

    Post likeAmount(Post post);
    boolean checkLiked(Post post);


    //DELETE

    boolean deletePostById(int id);



}
