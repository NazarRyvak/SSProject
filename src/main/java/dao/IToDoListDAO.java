package dao;

import entity.ToDoList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IToDoListDAO {

    void add(ToDoList toDoList);

    List<ToDoList> getAllByUserId(int id);

    List<ToDoList> getAllByUserIdAndDate(int id, LocalDate date);

    List<ToDoList> getAll();

    ToDoList getById(int id);

    void removeById(int id);

    boolean checkIfDateIsNotFree(int userId, LocalDateTime dateTime);

    boolean checkIfDateInScheduleIsBetweenInputDates(int userId, LocalDateTime dateBegin, LocalDateTime dateEnd);

}
