package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.CityBean;
import model.FlowerBean;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by joker on 5/29 0029.
 */
@Deprecated
@WebServlet("/insert_city")
public class City extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String citys = req.getParameter("city");
        Type type = new TypeToken<ArrayList<CityBean>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        ArrayList<CityBean> cityBeans = gson.fromJson(citys, type);
        resp.getWriter().print(new DatabaseService().insertCity(cityBeans));
    }
}
