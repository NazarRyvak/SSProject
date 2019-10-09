package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost/todolist?serverTimezone=Europe/Kiev&useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    private DBConnection() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(DRIVER).getDeclaredConstructor().newInstance();
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection successful!!!");
            } catch (Exception e) {
                System.out.println("Connection failed...");
                e.printStackTrace();
            }
        }
        return conn;
    }
}
