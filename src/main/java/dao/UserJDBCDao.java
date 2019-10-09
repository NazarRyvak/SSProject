package dao;

import db.DBConnection;
import entity.User;
import utils.secure.Key;
import utils.secure.StringXORer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDao implements IUserDAO {

    private Connection conn;

    public UserJDBCDao() {
        this.conn = DBConnection.getConnection();;
    }

    @Override
    public void add(User user) {
        String query = "insert into user(login, email, password) values(?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, StringXORer.encode(user.getPassword(), Key.JAVA));
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String query = "select * from user";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery(query);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .login(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User getById(int id) {
        String query = "select * from user where user.user_id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .login(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .build();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        String query = "select * from user u where u.login = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .login(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .build();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPasswordByLogin(String login) {
        String query = "select user.password from user where user.login = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeById(int id) {
        String query = "delete from user where user.user_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
