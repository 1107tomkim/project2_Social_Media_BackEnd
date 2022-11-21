package dev.jsonmusk.services;

import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.repositories.PostDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {


    private PostDAO postDAO;
    public PostServiceImpl(PostDAO postDAO){  this.postDAO = postDAO;  }


    @Override
    public Post createPost(Post post) {
        return this.postDAO.createPost(post);
    }

    @Override
    public Post getPostById(int id) {
        return this.postDAO.getPostById(id);
    }

    @Override
    public List<Post> getFeed() {
        return this.postDAO.getFeed();
    }

    @Override
    public Post updatePost(Post post) {
        return this.postDAO.updatePost(post);
    }

    @Override
    public Post likePost(Post post, User user) {
        if (user == null)
            return null;

        int[] likersUserIds = post.getLiked_by();
        int[] dislikersUserIds = post.getDisliked_by();
        boolean hasAlreadyLiked = Arrays.stream(likersUserIds).anyMatch(i -> i == user.getId());
        boolean hasAlreadyDisliked = Arrays.stream(dislikersUserIds).anyMatch(i -> i == user.getId());

        if (!hasAlreadyLiked && !hasAlreadyDisliked) {
            return this.postDAO.likePost(post);
        } else {
            return null;
        }


    }

    @Override
    public Post dislikePost(Post post, User user) {
        if (user == null)
            return null;

        int[] likersUserIds = post.getLiked_by();
        int[] dislikersUserIds = post.getDisliked_by();
        boolean hasAlreadyLiked = Arrays.stream(likersUserIds).anyMatch(i -> i == user.getId());
        boolean hasAlreadyDisliked = Arrays.stream(dislikersUserIds).anyMatch(i -> i == user.getId());

        if (!hasAlreadyLiked && !hasAlreadyDisliked) {
            return this.postDAO.dislikePost(post);
        } else {
            return null;
        }

    }

    @Override
    public Post likeAmount(Post post) {
        return this.postDAO.likeAmount(post);
    }

    @Override
    public boolean checkLiked(Post post) {
        return this.postDAO.checkLiked(post);
    }

    @Override
    public boolean deletePostById(int id) {
        return this.postDAO.deletePostById(id);
    }
}
