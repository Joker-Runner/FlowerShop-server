package controller.view;

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
@WebServlet("/index")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    int userId = Integer.parseInt(cookie.getValue());
                    req.setAttribute("userId", userId);
                    break;
                }
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
