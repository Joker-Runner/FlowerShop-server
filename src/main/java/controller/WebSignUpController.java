package controller;

import model.UserBean;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/18 0018.
 */
@WebServlet("/web_sign_up")
public class WebSignUpController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserBean userBean = new UserBean();
        userBean.setUsername(req.getParameter("username"));
        userBean.setEmail(req.getParameter("email"));
        userBean.setPassWord(req.getParameter("password"));
        int success = databaseService.signUp(userBean);
        if (success==1){
            resp.sendRedirect(req.getContextPath()+"/login.html");
        }
    }
}
