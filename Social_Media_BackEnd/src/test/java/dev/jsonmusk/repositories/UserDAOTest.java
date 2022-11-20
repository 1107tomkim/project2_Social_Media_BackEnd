package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    // FORMAT REFERENCE
//    static PostDAO postDAO = new PostDAOPostgres();
//
//    @Test
//    @Order(1)
//    void create_request_test() {
////        Post newRequest = new Post(0, 1, 250.00f, "chairs", Post.Type.OTHER);
////        Post savedRequest = postDAO.createReimbursementRequest(newRequest);
////        System.out.println(savedRequest);
////        Assertions.assertNotNull(savedRequest);
////        Assertions.assertEquals("chairs", savedRequest.getDescription());
//    }

    static UserDAO userDAO = new UserDAOPostgres();

    @Test
    void createUser() {
        User testuser = new User(0, "tester", "password", false);
        User savedUser = userDAO.createUser(testuser);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(testuser.getUsername(), savedUser.getUsername());
    }

    @Test
    void getUserById() {
        User user = userDAO.getUserById(3);
        Assertions.assertEquals(3, user.getId());
    }

    @Test
    void getUserByUsername() {
        User user = userDAO.getUserByUsername("Hika");
        Assertions.assertEquals("Hika", user.getUsername());
    }

    @Test
    void updateUser() {
        User user = userDAO.getUserById(4);
        user.setEmail("HikaIsTheBest@Gmail.com");
        User updatedUser = userDAO.updateUser(user);
        System.out.println(updatedUser);
        Assertions.assertEquals("HikaIsTheBest@Gmail.com", updatedUser.getEmail());
    }

    @Test
    void logout() {
    }

}