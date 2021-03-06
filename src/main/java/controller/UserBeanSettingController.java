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
 * Created by joker on 5/20 0020.
 */
@WebServlet("/user_bean_setting")
public class UserBeanSettingController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserBean userBean = new UserBean();
        userBean.setId(Integer.parseInt(req.getParameter("id")));
        userBean.setUsername(req.getParameter("username"));
        userBean.setPassWord(req.getParameter("password"));
//        resp.getWriter().print(databaseService.settingUserBean(userBean));
    }
}
