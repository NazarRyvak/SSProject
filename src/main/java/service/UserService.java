package service;

import dao.UserJDBCDao;
import entity.User;
import utils.secure.Key;
import utils.secure.StringXORer;

public class UserService {

    private UserJDBCDao userDao;

    public UserService() {
        userDao = new UserJDBCDao();
    }

    private boolean checkIfLoginExist(String login) {
        return userDao.getByLogin(login) != null;
    }

    public boolean authenticationFailed(String login, String password) {
        String password1 = userDao.getPasswordByLogin(login);
        String decode = StringXORer.decode(password1, Key.JAVA);
        return !password.equals(decode);
    }

    public User getUser(String login) {
        if (!checkIfLoginExist(login)) {
            return null;
        } else {
            return userDao.getByLogin(login);
        }
    }
}
