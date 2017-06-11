package controller;

import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/7 0007.
 */
@WebServlet("/create_order")
public class CreateOrderController extends HttpServlet {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int flowerId = Integer.parseInt(req.getParameter("flower_id"));
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String addressee = req.getParameter("addressee");
        String telephone = req.getParameter("telephone");
        String address = req.getParameter("address");
        int order_status = Integer.parseInt(req.getParameter("order_status"));
        String remark = req.getParameter("remark");
        resp.getWriter().print(databaseService.insertOrder(flowerId,
                userId, addressee, telephone, address, order_status, remark));
    }
}
