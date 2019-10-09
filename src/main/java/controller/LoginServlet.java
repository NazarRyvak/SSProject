package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private User user;
    private static final long serialVersionUID = 4249780684684026453L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("incorrectLoginOrPassword", false);
        UserService userService = new UserService();
        String login = req.getParameter("user_name");
        String password = req.getParameter("password");
        this.user = userService.getUser(login);
        if (user==null||userService.authenticationFailed(login, password)){
            req.setAttribute("failed", "Incorrect login or password!!Try again...");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }else{
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("login", user.getLogin());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("id", user.getId());
            System.out.println(req.getSession().getAttribute("id"));
            resp.sendRedirect("/user");
        }
    }
}
