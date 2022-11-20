package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Session;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.util.ConnectionFactory;

import java.sql.*;

public class SessionDAOPostgres implements SessionDAO {
    @Override
    public Session createAuth(User user, Session auth) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
            String sql = "insert into sessions values(default, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getId());
            ps.setString(2, auth.getToken());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            auth.setSessionId(rs.getInt("session_id"));
            return auth;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Session getSessionByToken(String token) {
        if (token == null || token == "")
            return null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from sessions where auth_token=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Session session = new Session();
            session.setSessionId(rs.getInt("session_id"));
            session.setUserId(rs.getInt("user_id"));
            session.setToken(rs.getString("auth_token"));


            return session;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Session getSessionByUserId(int id) {
        if (id == 0)
            return null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from sessions where user_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Session session = new Session();
            session.setSessionId(rs.getInt("session_id"));
            session.setUserId(rs.getInt("user_id"));
            session.setToken(rs.getString("auth_token"));


            return session;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean appendFileUploadToSession(Session session, byte[] file) {
        // take the session id and file in parameter and update the corresponding session in db to add the file
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update sessions set file = ? where session_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBytes(1, file);
            ps.setInt(2, session.getSessionId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getUserIdByToken(String token) {
        if (token == null || token == "")
            return 0;
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select user_id from sessions where auth_token=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // Session session = new Session();
            //   session.setSessionId(rs.getInt("session_id"));
            //   session.setUserId(rs.getInt("user_id"));
            //    session.setToken(rs.getString("auth_token"));

            //     return session.getUserId();
            int userId = rs.getInt("user_id");
            System.out.println("THE USER ID IS CURRENTLY>>>>>>>> " + userId);
            return userId;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean deleteAuthByUserId(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "delete from sessions where user_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();


            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
