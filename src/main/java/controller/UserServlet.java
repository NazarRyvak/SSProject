package controller;

import entity.ToDoList;
import service.ToDoListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private boolean isCurrentDate = false;

    private static final long serialVersionUID = -4027159295798624286L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ToDoListService toDoListService = new ToDoListService();
        if (!isCurrentDate) {
            session.setAttribute("currentDate", LocalDate.now());
            isCurrentDate = true;
        }
        Object obj = session.getAttribute("id");
        Integer userId;
        if (obj==null){
            userId = 1;
        }else{
             userId = Integer.valueOf(obj.toString());
        }
        LocalDateTime dateTime = getLocalDateTime(session.getAttribute("currentDate").toString());
        List<ToDoList> toDoList =  toDoListService.getAllByUserAndDate(userId, dateTime.toLocalDate());
        for (ToDoList list:toDoList
             ) {
            System.out.println(list);
        }
        req.setAttribute("toDoList", toDoList);
        req.getRequestDispatcher("WEB-INF/views/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private LocalDateTime getLocalDateTime(String str){
        return LocalDateTime.of(LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.now());
    }


}
