package controller.view;

import com.google.gson.GsonBuilder;
import model.UserBean;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/24 0024.
 */
@WebServlet("/login_web")
public class Login extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName()=="name"){
//                int userId = Integer.parseInt(cookie.getValue());
                resp.sendRedirect(req.getContextPath()+"/index");
                break;
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/login.html").forward(req,resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        UserBean userBean = new UserBean();
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        userBean = databaseService.login(email, password);
//        Cookie cookie = new Cookie("name",userBean.getId()+"");
//        cookie.setMaxAge(7*24*3600000);
//        if(userBean!=null){
//            resp.addCookie(cookie);
//        }
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        resp.getWriter().print(gsonBuilder.create().toJson(userBean));
//    }
}
