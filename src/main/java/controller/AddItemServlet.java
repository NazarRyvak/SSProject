package controller;

import entity.ToDoList;
import service.ToDoListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/item/add")
public class AddItemServlet extends HttpServlet {

    private static final long serialVersionUID = 8733165020846292067L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("here");
        req.getRequestDispatcher("/WEB-INF/views/add_item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDoListService service = new ToDoListService();
        String dateBegin = req.getParameter("dateBegin");
        String dateEnd = req.getParameter("dateEnd");
        LocalDateTime beginDateTime = getLocalDateTime(dateBegin);
        LocalDateTime endDateTime = getLocalDateTime(dateEnd);
        boolean beginDateIsFree = service.dateIsFree(Integer.valueOf(req.getSession().getAttribute("id").toString()), beginDateTime);
        boolean endDateIsFree = service.dateIsFree(Integer.valueOf(req.getSession().getAttribute("id").toString()), endDateTime);
        boolean betweenDatesAreNotItem = service.beetweenDateNotItems(Integer.valueOf(req.getSession().getAttribute("id").toString()), beginDateTime, endDateTime);
        if (!beginDateIsFree) {
            req.setAttribute("beginDateIsNotFree", "Your schedule is not free with this date!!");
        }
        if (!endDateIsFree) {
            req.setAttribute("endDateIsNotFree", "Your schedule is not free with this date!!");
        }
        if (!betweenDatesAreNotItem) {
            req.setAttribute("betweenDatesAreNotItem", "Between these dates are items!!");
        }

        if (beginDateIsFree && endDateIsFree && betweenDatesAreNotItem) {
            ToDoList toDoList = ToDoList.builder()
                    .description(req.getParameter("description"))
                    .dateBegin(beginDateTime)
                    .dateEnd(endDateTime)
                    .user_id(Integer.valueOf(req.getSession().getAttribute("id").toString()))
                    .build();
            service.addToSchedule(toDoList);
            resp.sendRedirect("/user");
        } else {
            req.getRequestDispatcher("/WEB-INF/views/add_item.jsp").forward(req, resp);
        }
    }

    private LocalDateTime getLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
