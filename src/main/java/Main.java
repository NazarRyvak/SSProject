import dao.ToDoListJDBCDao;
import dao.UserJDBCDao;
import db.DBConnection;
import entity.ToDoList;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        //User user = DAOFactory.getFactory().getUserDAO().getById(1);
       /* String str = "nazar123";
        String str1 = StringXORer.encode(str, Key.JAVA);
        System.out.println(str + " "+ str1);
        System.out.println(StringXORer.decode(str1, Key.JAVA));*/
        Statement stmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse("2019-10-07 23:19:59", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2019-10-08 22:59:59", formatter);
        LocalDateTime date3 = LocalDateTime.parse("2019-10-06 11:59:59", formatter);
        LocalDateTime date4 = LocalDateTime.parse("2019-10-06 13:59:59", formatter);
        try {
            stmt = conn.createStatement();
            /*UserJDBCDao dao = new UserJDBCDao();
            User user = dao.getById(1);
            System.out.println(user);*/

           /* ToDoListJDBCDao dao = new ToDoListJDBCDao();
            //CreateDBTable.createTableList(stmt);
            ToDoList list = ToDoList.builder().description("1").user_id(1).dateBegin(date1).dateEnd(date2).build();
            ToDoList list1 = ToDoList.builder().description("2").user_id(1).dateBegin(date3).dateEnd(date4).build();
            //ToDoList list2 = ToDoList.builder().description("1").user_id(1).date_begin(Date.valueOf("2019-10-06 11:59:59")).date_end(Date.valueOf("2019-10-06 13:59:59")).build();

            dao.add(list);
            dao.add(list1);*/
            //dao.add(list2);
           /* LocalDateTime dateTime = LocalDateTime.of(2019,10,06,22,00,00);
            System.out.println(ConvertDate.convertLocalDateTimeToDateTimeMySql(dateTime));*/
         /*   ToDoListJDBCDao dao = new ToDoListJDBCDao();
            System.out.println(dao.checkIfDateIsNotFree(2,date1));*/
            ToDoListJDBCDao dao = new ToDoListJDBCDao();
            System.out.println(dao.checkIfDateInScheduleIsBetweenInputDates(2,date1, date2));



           /*User user = User.builder().login("kolya").email("kolya@gmail.com").password("123").build();
            UserJDBCDao dao = new UserJDBCDao();
            dao.add(user);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //CreateDBTable.createTableList(stmt);
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
