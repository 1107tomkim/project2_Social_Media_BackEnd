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
        app.post("/logout", userController.logoutHandler);


        //Restricting access to ('/api') routes via an "authorize handler"
        app.before("/api/*", userController.authorizeHandler);

        //Getting the current user
        app.get("/api/user", userController.getUserHandler);



        // post stuff ---
        // Creating a new post
        app.post("/api/post", postController.createPostHandler);




     //   app.get("/user/{id}", userController.getUserByIdHandler);
     //   app.put("/user", userController.updateUserHandler);




        //Post Path
        app.post("/api/post/{username}", postController.createPostHandler);
        app.get("/api/post/{post_id}", postController.getPostbyIdHandler);

        app.get("/api/posts", postController.getFeedHandler);









        // go
        app.start();




    }
}