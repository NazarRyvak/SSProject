package controller;

import service.ToDoListService;
import utils.checking.MyChecking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/item/delete/*")
public class DeleteItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] arr = req.getRequestURI().split("/");

        if (arr.length == 4 && MyChecking.isInt(arr[arr.length - 1])) {
            ToDoListService service = new ToDoListService();
            HttpSession session = req.getSession();
            Object userId = session.getAttribute("id");
            if (userId != null) {
                service.deleteById(Integer.parseInt(arr[arr.length - 1]), userId.toString());
            }
            resp.sendRedirect("/user");
        } else {
            resp.sendRedirect("/404");
        }
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
