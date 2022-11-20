package dev.jsonmusk.services;

import dev.jsonmusk.driver.Driver;
import dev.jsonmusk.entities.Comment;
import dev.jsonmusk.entities.Post;
import dev.jsonmusk.repositories.PostDAO;

import java.util.List;

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
    public boolean deletePostById(int id) {
        return this.postDAO.deletePostById(id);
    }
}
