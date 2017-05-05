package service;

import model.FlowerBean;
import model.UserBean;
import org.springframework.stereotype.Service;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 数据库操作 JDBC
 * Created by joker on 4/11 0011.
 */
@Service
public class DatabaseService {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * 创建数据库连接Statement
     *
     * @return statement
     */
    private Statement createStatement() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "zhang520");
            properties.setProperty("password", "zhang520");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");

            conn = DriverManager.getConnection("jdbc:mysql://"
                    + Util.IP + ":" + Util.DB_PORT + "/flower_shop", properties);
            statement = conn.createStatement();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return statement;
    }

    public void closeStatement() {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录的数据库操作
     *
     * @param userBean 用户Bean
     * @return 用户名密码是否正确
     */
    public boolean login(UserBean userBean) {
        String sql = "SELECT * FROM users WHERE username='" +
                userBean.getUserName() + "'";
        String s;
        try {
            resultSet = createStatement().executeQuery(sql);
            while (resultSet.next()) {
                s = resultSet.getString("password");
                return s.equals(userBean.getPassWord());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 用户注册的数据库操作
     *
     * @param userBean 待注册用户的UserBean
     * @return 是否注册成功
     */
    public boolean signUp(UserBean userBean) {
        String sql = "INSERT INTO users(username, password) VALUES('" +
                userBean.getUserName() + "','" + userBean.getPassWord() + "')";
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

    /**
     * 插入商品
     *
     * @param flowerBean 要插入的商品
     * @return 插入是否成功
     */
    public boolean insertFlower(FlowerBean flowerBean) {
        String sql = "INSERT INTO flowers(title, introduction,price,image) VALUES('" +
                flowerBean.getTitle() + "','" + flowerBean.getIntroduction() + "','" +
                flowerBean.getPrice() + "','" + flowerBean.getImageURL() + "')";
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

    /**
     * 查询商品列表
     *
     * @return 返回商品列表
     */
    public List<FlowerBean> queryFlower() {
        String sql = "SELECT * FROM flowers";
        FlowerBean flowerBean;
        List<FlowerBean> flowerBeanList = new ArrayList<FlowerBean>();
        try {
            resultSet = createStatement().executeQuery(sql);
            while (resultSet.next()) {
                flowerBean = new FlowerBean();
                flowerBean.setId(resultSet.getInt("id"));
                flowerBean.setTitle(resultSet.getString("title"));
                flowerBean.setIntroduction(resultSet.getString("introduction"));
                flowerBean.setPrice(resultSet.getDouble("price"));
                flowerBean.setImageURL(Util.getURL() + "/flower/" + resultSet.getString("image"));
                flowerBeanList.add(flowerBean);
            }
            return flowerBeanList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
