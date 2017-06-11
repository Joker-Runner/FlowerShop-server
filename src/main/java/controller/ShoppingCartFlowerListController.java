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
 *
 * Created by joker on 5/7 0007.
 */
@WebServlet("/shopping_cart_list")
public class ShoppingCartFlowerListController extends HttpServlet {

    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.getWriter().print(databaseService.queryShoppingCartFlowers(1));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("user_id"));
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder gsonBuilder = new GsonBuilder();
        String flowerList = gsonBuilder.create().toJson(databaseService.queryShoppingCartFlowers(userId));
        resp.getWriter().print(flowerList);
    }
}
