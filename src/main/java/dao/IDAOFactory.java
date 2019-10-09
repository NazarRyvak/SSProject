package dao;

public interface IDAOFactory {
    IUserDAO getUserDAO();
    IToDoListDAO getToDoListDao();
}
