package controller;

import model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by joker on 4/11 0011.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet{
    LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><body><h2>Hello World! Today is " + new java.util.Date() + "</h2></body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean.setUserName(req.getParameter("username"));
        userBean.setPassWord(req.getParameter("password"));
        if (loginService.queryLogin(userBean)){
//            resp.sendRedirect(req.getContextPath() + "/login_success.jsp");
            resp.getWriter().print(true);
        }
    }
}
