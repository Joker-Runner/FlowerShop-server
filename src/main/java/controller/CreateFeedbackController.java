package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 6/1 0001.
 */
@WebServlet("/create_feedback")
public class CreateFeedbackController extends HttpServlet {
    DatabaseService databaseService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        databaseService = new DatabaseService();
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String feedback = req.getParameter("feedback");
        resp.getWriter().print(databaseService.insertFeedback(userId,feedback));
    }
}
