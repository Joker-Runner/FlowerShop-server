package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置用户城市
 * Created by joker on 6/1 0001.
 */
@WebServlet("/setting_city")
public class SettingCityController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String cityCode = req.getParameter("city_code");
        String city = req.getParameter("city");
        resp.getWriter().print(databaseService.settingCity(userId, city, cityCode));
    }
}
