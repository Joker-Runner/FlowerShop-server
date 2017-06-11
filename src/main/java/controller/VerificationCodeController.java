package controller;

import org.apache.commons.lang3.RandomStringUtils;
import service.DatabaseService;
import utils.SendVerificationCodeEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joker on 5/17 0017.
 */
@WebServlet("/verification_code")
public class VerificationCodeController extends HttpServlet {
    DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userEmail = req.getParameter("email");
        String verificationCode = RandomStringUtils.randomNumeric(6);
        String generateTime = System.currentTimeMillis() + "";
        int re = databaseService.verificationCode(userEmail, verificationCode, generateTime);
        String responseData=re+"";
        /**
         * 1：未被注册并成功插入验证码；
         * 0：已被注册；
         * -1：出错
         */
        if (re == 1) {
            if (SendVerificationCodeEmail.sendMail(userEmail, verificationCode)){
                responseData = verificationCode;
            }
        } else if (re == 0) {
        } else if (re == -1) {

        }
        resp.getWriter().print(responseData);
    }
}
