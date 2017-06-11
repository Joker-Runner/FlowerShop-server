package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by joker on 5/7 0007.
 */
@WebServlet("/add_shopping_cart")
public class AddShoppingCartController extends HttpServlet{
    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flowerId = Integer.parseInt(req.getParameter("flower_id"));
        int userId = Integer.parseInt(req.getParameter("user_id"));
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(databaseService.insertShoppingCart(flowerId,userId));
    }
}
