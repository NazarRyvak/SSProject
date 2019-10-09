package dao;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory = null;
    private DAOFactory(){
    }
    public static IDAOFactory getFactory(){
        if (factory==null){
            factory = new DAOFactory();
        }
        return factory;
    }
    @Override
    public IUserDAO getUserDAO() {
        return new UserJDBCDao();
    }

    @Override
    public IToDoListDAO getToDoListDao() {
        return new ToDoListJDBCDao();
    }
}
