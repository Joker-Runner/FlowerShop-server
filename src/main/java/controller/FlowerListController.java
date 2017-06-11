package controller;

import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取商品列表的 Controller
 * Created by joker on 5/3 0003.
 */
@WebServlet("/flower_list")
@Controller
public class FlowerListController extends HttpServlet {

    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        GsonBuilder gsonBuilder = new GsonBuilder();
        String flowerList = gsonBuilder.create().toJson(databaseService.queryAllFlower());
        resp.getWriter().print(flowerList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int startNumber = Integer.parseInt(req.getParameter("start_number"));
        GsonBuilder gsonBuilder = new GsonBuilder();
        String flowerList = gsonBuilder.create().toJson(databaseService.queryFlowers(startNumber));
        resp.getWriter().print(flowerList);

//        doGet(req, resp);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("utf-8");
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        String flowerList = gsonBuilder.create().toJson(databaseService.queryFlower());
//        resp.getWriter().print(flowerList);

    }
}
