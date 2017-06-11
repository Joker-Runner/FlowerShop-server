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
 * Created by joker on 5/23 0023.
 */
@WebServlet("/category_flower_list")
public class CategoryFlowerListController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        GsonBuilder gsonBuilder = new GsonBuilder();
        String flowerList = gsonBuilder.create().toJson(databaseService.queryCategoryFlower(categoryId));
        resp.getWriter().print(flowerList);
    }
}
