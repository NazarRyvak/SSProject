package service;

import dao.ToDoListJDBCDao;
import entity.ToDoList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ToDoListService {
    private ToDoListJDBCDao dao;

    public ToDoListService() {
        dao = new ToDoListJDBCDao();
    }

    public List<ToDoList> getAllByUserAndDate(int id, LocalDate date) {
        return dao.getAllByUserIdAndDate(id, date);
    }

    public void deleteById(int id, String userId) {
        ToDoList toDolist = dao.getById(id);
        if (String.valueOf(toDolist.getUser_id()).equals(userId)) {
            dao.removeById(id);
        }
    }

    public boolean dateIsFree(int id, LocalDateTime dateTime) {
        return !dao.checkIfDateIsNotFree(id, dateTime);
    }

    public boolean beetweenDateNotItems(int id, LocalDateTime dateBegin, LocalDateTime dateEnd) {
        return !dao.checkIfDateInScheduleIsBetweenInputDates(id, dateBegin, dateEnd);
    }

    public void addToSchedule(ToDoList toDoList) {
        dao.add(toDoList);
    }


}
