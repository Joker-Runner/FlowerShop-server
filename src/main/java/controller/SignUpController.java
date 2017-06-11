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
        req.getRequestDispatcher("/WEB-INF/view/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserBean userBean = new UserBean();
        userBean.setUsername(req.getParameter("username"));
        userBean.setEmail(req.getParameter("email"));
        userBean.setPassWord(req.getParameter("password"));
        int success = databaseService.signUp(userBean);
        resp.getWriter().print(success);
//        if (success==1){
//            resp.sendRedirect(req.getContextPath()+"/login.html");
//        }
    }
}
