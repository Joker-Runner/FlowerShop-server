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
@WebServlet("/get_category")
public class GetCategoryController extends HttpServlet{
    DatabaseService databaseService = new DatabaseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder gsonBuilder = new GsonBuilder();
        String categoryList = gsonBuilder.create().toJson(databaseService.queryCateGoryList());
        resp.getWriter().print(categoryList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
