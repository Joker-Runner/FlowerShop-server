package controller;

import com.google.gson.GsonBuilder;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/12 0012.
 */
@WebServlet("/order_list")
public class OrderListController extends HttpServlet{
    DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder gsonBuilder = new GsonBuilder();
        String orderList = gsonBuilder.create().toJson(databaseService.queryOrder(1));
        resp.getWriter().print(orderList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        GsonBuilder gsonBuilder = new GsonBuilder();
        String orderList = gsonBuilder.create().toJson(databaseService.queryOrder(userId));
        resp.getWriter().print(orderList);
    }
}
