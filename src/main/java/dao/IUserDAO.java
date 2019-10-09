package dao;

import entity.User;

import java.util.List;

public interface IUserDAO {

    void add(User user);

    List<User> getAll();

    User getById(int id);

    User getByLogin(String login);

    String getPasswordByLogin(String login);

    void removeById(int id);
}
