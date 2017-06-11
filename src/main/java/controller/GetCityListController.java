package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@WebServlet("/get_city")
    public class GetCityListController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String cityListJson = new GsonBuilder().create().toJson(databaseService.queryCityList());
        resp.getWriter().print(cityListJson);
    }
}
