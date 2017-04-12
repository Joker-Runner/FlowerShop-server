package service;

import model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.*;
import java.util.Properties;


/**
 * Created by joker on 4/11 0011.
 */
public class LoginService {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();


    public boolean queryLogin(UserBean userBean) {

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String s;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "zhang520");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");

            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/flower_shop", properties);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE username='" +
                    userBean.getUserName() + "'");

            while (resultSet.next()) {
                s = resultSet.getString("password");
                return s.equals(userBean.getPassWord()) ? true : false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {

            try {
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
