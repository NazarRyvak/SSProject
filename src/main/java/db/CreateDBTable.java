package db;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTable {
    public static void createTableUser(Statement stmt) {
        String query = "create table if not exists user("
                + "user_id int not null auto_increment,"
                + "login varchar(255) not null,"
                + "email varchar(255) not null,"
                + "password varchar(255) not null,"
                + "primary key(user_id)"
                + ")";
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableList(Statement stmt) {
        String query = "create table if not exists list("
                + "list_id int not null AUTO_INCREMENT,"
                + "description varchar(255),"
                + "user_id int not null,"
                + "date_begin timestamp,"
                + "date_end timestamp,"
                + "primary key(list_id),"
                + "constraint fk_user_id foreign key(user_id) references user(user_id)"
                + ")";
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
