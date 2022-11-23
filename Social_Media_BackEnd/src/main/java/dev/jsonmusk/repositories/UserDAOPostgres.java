package dev.jsonmusk.repositories;

import dev.jsonmusk.entities.Post;
import dev.jsonmusk.entities.User;
import dev.jsonmusk.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOPostgres implements UserDAO {

    @Override
    public User createUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection()){
            //INSERT INTO users VALUES (DEFAULT, 'User1', 'password', 'George', 'Neilson', 'georgeyboy@office.net', FALSE);
            String sql = "insert into users values(default, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone_number());
            ps.setString(7, user.getAge_num());
            ps.setBoolean(8, user.isLoggedIn());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("user_id");
            user.setId(generatedKey);
            return user;

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        // select the post from db with corresponding id
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from users where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();


            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPhone_number(rs.getString("phonenumber"));
            user.setAge_num(rs.getString("age_num"));
            user.setLoggedIn(rs.getBoolean("islogged"));
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from users where username=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            User gottenUser = new User();
            gottenUser.setId(rs.getInt("user_id"));
            gottenUser.setUsername(rs.getString("username"));
            gottenUser.setPassword(rs.getString("password"));
            gottenUser.setFirstname(rs.getString("firstname"));
            gottenUser.setLastname(rs.getString("lastname"));
            gottenUser.setEmail(rs.getString("email"));
            gottenUser.setPhone_number(rs.getString("phonenumber"));
            gottenUser.setAge_num(rs.getString("age_num"));
            gottenUser.setLoggedIn(rs.getBoolean("isLogged"));

            return gottenUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> searchUser(String username) {
        if (username.length() < 1)
            return null;

        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from users where upper(username) like upper('%" + username + "%')";
            PreparedStatement ps = connection.prepareStatement(sql);
         //   ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User gottenUser = new User();
                gottenUser.setId(rs.getInt("user_id"));
                gottenUser.setUsername(rs.getString("username"));
                gottenUser.setPassword(rs.getString("password"));
                gottenUser.setFirstname(rs.getString("firstname"));
                gottenUser.setLastname(rs.getString("lastname"));
                gottenUser.setEmail(rs.getString("email"));
                gottenUser.setPhone_number(rs.getString("phonenumber"));
                gottenUser.setAge_num(rs.getString("age_num"));
                gottenUser.setLoggedIn(rs.getBoolean("isLogged"));
                users.add(gottenUser);
            }
            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        // take the user in parameter and update the corresponding user(id) in db
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update users set username = ?, password = ?, firstname = ?, lastname = ?, email = ?, phonenumber = ?, age_num = ?, islogged = ? where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone_number());
            ps.setString(7, user.getAge_num());
            ps.setBoolean(8, user.isLoggedIn());
            ps.setInt(9, user.getId());

            ps.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUserLogin(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update users set islogged = ? where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, user.isLoggedIn());
            ps.setInt(2, user.getId());

            ps.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //log in
    }
    @Override
    public User login(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update users set islogged = ? where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, user.getId());

            ps.executeUpdate();
            System.out.println(user.getId());
            System.out.println(user.isLoggedIn());
            System.out.println("dao Logs in");
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //log in
    }


    @Override
    public User logout(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update users set islogged = ? where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, user.isLoggedIn());
            ps.setInt(2, user.getId());

            ps.executeUpdate();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}