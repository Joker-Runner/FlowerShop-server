package controller;

import model.UserBean;
import org.springframework.stereotype.Controller;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册的Controller
 * Created by joker on 5/2 0002.
 */
@WebServlet("/sign_up")
@Controller
public class SignUpController extends HttpServlet {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><body><h2>Sign Up! Today is " + new java.util.Date() + "</h2></body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean.setUserName(req.getParameter("username"));
        userBean.setPassWord(req.getParameter("password"));
        resp.getWriter().print(databaseService.signUp(userBean));
    }
}
