package dev.jsonmusk.driver;

import dev.jsonmusk.controllers.CommentController;
import dev.jsonmusk.controllers.PostController;
import dev.jsonmusk.controllers.UserController;
import dev.jsonmusk.managers.TokenManagerImpl;
import dev.jsonmusk.repositories.CommentDAOPostgres;
import dev.jsonmusk.repositories.PostDAOPostgres;
import dev.jsonmusk.repositories.SessionDAOPostgres;
import dev.jsonmusk.services.*;
import io.javalin.Javalin;
import dev.jsonmusk.repositories.UserDAOPostgres;

public class Driver {
    public static UserService userService = new UserServiceImpl( new UserDAOPostgres(), new SessionDAOPostgres(), new TokenManagerImpl() );

    public static PostService postService = new PostServiceImpl(new PostDAOPostgres());

    public static CommentService commentService = new CommentServiceImpl(new CommentDAOPostgres());

    public static void main(String[] args) {



        Javalin app = Javalin.create(

                javalinConfig -> {
                    javalinConfig.enableCorsForAllOrigins();
                }


        );


        UserController userController = new UserController();
        PostController postController = new PostController();
        CommentController commentController = new CommentController();


        // put app.get, etc. (routes) here
        app.post("/login", userController.loginHandler);


        //Creating new users
        app.post("/create", userController.createUserHandler);
        app.post("/logout", userController.logoutHandler);  //added /api so frontend can reach


        //Restricting access to ('/api') routes via an "authorize handler"
        app.before("/api/*", userController.authorizeHandler);

        //Getting the current user
        app.get("/user", userController.getUserHandler);  //added /api so frontend can reach
        app.get("/user/{id}", userController.getUserByIdHandler); // get user by id


        // post stuff ---
        // Creating a new post
        app.post("/api/post", postController.createPostHandler);  //added /api so frontend can reach


     //   app.put("/user", userController.updateUserHandler);




        //Post Path
        app.post("/post", postController.createPostHandler);
        app.post("/post/like", postController.likeHandler);
        app.post("/post/dislike", postController.dislikeHandler);
        app.get("/post/{post_id}", postController.getPostbyIdHandler);

        app.get("/api/posts", postController.getFeedHandler);  //added /api so frontend can reach



        //Comment Path
        app.get("/api/comments/{post_id}", commentController.getAllCommentsOfPostId); //added /api so frontend can reach

        app.post("/api/comment", commentController.createCommentHandler);
        app.get("/comment/{id}", commentController.getCommentByIdHandler);

        app.put("/comment/{user_id}", commentController.updateCommentHandler);
        app.get("/parentcomment/{comment_parent_id}", commentController.getCommentByParentIdHandler);
        app.delete("/commentdelete/{comment_id}", commentController.deleteCommentHandler);







        // go
        app.start();    // hika 8888




    }
}