package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Session;
import dev.jsonmusk.entities.User;


public interface SessionDAO {

    //CREATE
    Session createAuth(User user, Session auth);
    //READ
    int getUserIdByToken(String token);

    Session getSessionByToken(String token);
    Session getSessionByUserId(int id);
    //UPDATE
    boolean appendFileUploadToSession(Session session, byte[] file);

    //DELETE
    boolean deleteAuthByUserId(int id);

}
