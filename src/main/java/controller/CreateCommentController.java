package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单评论
 * Created by joker on 6/2 0002.
 */
@WebServlet("/")
public class CreateCommentController extends HttpServlet {
    DatabaseService databaseService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        int flowerId = Integer.parseInt(req.getParameter("flower_id"));
        int rate = Integer.parseInt(req.getParameter("rate"));
        String comment = req.getParameter("comment");

        databaseService = new DatabaseService();
        resp.getWriter().print(databaseService.insertComment(userId, flowerId, rate, comment,
                String.valueOf(System.currentTimeMillis())));
    }
}
