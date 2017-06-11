package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/15 0015.
 */
@WebServlet("/add_star")
public class AddStarController extends HttpServlet{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        int flowerId = Integer.parseInt(req.getParameter("flower_id"));
        resp.getWriter().print(databaseService.insertStar(flowerId,userId));
    }
}
