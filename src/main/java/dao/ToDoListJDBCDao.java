package dao;

import db.DBConnection;
import entity.ToDoList;
import utils.converter.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDoListJDBCDao implements IToDoListDAO {

    private Connection conn;

    public ToDoListJDBCDao() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void add(ToDoList toDoList) {
        String query = "insert into list(description, user_id, date_begin, date_end) values(?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, toDoList.getDescription());
            pstmt.setInt(2, toDoList.getUser_id());
            pstmt.setTimestamp(3, ConvertDate.convertLocalDateTimeToTimestamp(toDoList.getDateBegin()));
            pstmt.setTimestamp(4, ConvertDate.convertLocalDateTimeToTimestamp(toDoList.getDateEnd()));
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ToDoList> getAllByUserId(int id) {
        List<ToDoList> list1 = new ArrayList<>();
        String query = "select * from list where user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ToDoList toDoList = ToDoList.builder()
                        .id(resultSet.getInt(1))
                        .description(resultSet.getString(2))
                        .user_id(resultSet.getInt(3))
                        .dateBegin(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(4)))
                        .dateEnd(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(5)))
                        .build();
                list1.add(toDoList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    @Override
    public List<ToDoList> getAllByUserIdAndDate(int id, LocalDate date) {
        String dateBegin = String.valueOf(date+ " 00:00:00");
        String dateEnd = String.valueOf(date + " 23:59:59");
        List<ToDoList> list = new ArrayList<>();
        String query = "select * from list where list.user_id = ? and ((list.date_begin between ? and ?) or (list.date_end between ? and ?) or(? between list.date_begin and list.date_end))order by list.date_begin";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, dateBegin);
            pstmt.setString(3, dateEnd);
            pstmt.setString(4, dateBegin);
            pstmt.setString(5, dateEnd);
            pstmt.setString(6, dateBegin);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ToDoList toDoList = ToDoList.builder()
                        .id(resultSet.getInt(1))
                        .description(resultSet.getString(2))
                        .user_id(resultSet.getInt(3))
                        .dateBegin(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(4)))
                        .dateEnd(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(5)))
                        .build();
                list.add(toDoList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ToDoList> getAll() {
        List<ToDoList> list = new ArrayList<>();
        String query = "select * from list";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ToDoList toDoList = ToDoList.builder()
                        .id(resultSet.getInt(1))
                        .description(resultSet.getString(2))
                        .user_id(resultSet.getInt(3))
                        .dateBegin(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(4)))
                        .dateEnd(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(5)))
                        .build();
                list.add(toDoList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ToDoList getById(int id) {
        String query = "select * from list where list.list_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ToDoList toDoList = ToDoList.builder()
                        .id(resultSet.getInt(1))
                        .description(resultSet.getString(2))
                        .user_id(resultSet.getInt(3))
                        .dateBegin(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(4)))
                        .dateEnd(ConvertDate.convertTimestampToLocalDateTime(resultSet.getTimestamp(5)))
                        .build();
                return toDoList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeById(int id) {
        String query = "delete from list where list.list_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkIfDateIsNotFree(int userId, LocalDateTime dateTime) {
        String date = String.valueOf(dateTime);
        String query = "select * from list where list.user_id = ? and ? between list.date_begin and list.date_end limit 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, date);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkIfDateInScheduleIsBetweenInputDates(int userId, LocalDateTime localDateBegin, LocalDateTime localDateEnd) {
        String dateBegin = String.valueOf(localDateBegin);
        String dateEnd = String.valueOf(localDateEnd);
        String query = "select * from list where list.user_id = ? and (list.date_begin between ? and ? or list.date_end between ? and ?) limit 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, dateBegin);
            pstmt.setString(3, dateEnd);
            pstmt.setString(4, dateBegin);
            pstmt.setString(5, dateEnd);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
