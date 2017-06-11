package service;

import model.*;
import org.springframework.stereotype.Service;
import utils.Utils;

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

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "zhang520");
            properties.setProperty("password", "zhang520");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");

            conn = DriverManager.getConnection("jdbc:mysql://"
                    + Utils.IP + ":" + Utils.DB_PORT + "/flower_shop", properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 创建数据库连接Statement
     *
     * @return statement
     */
    private Statement createStatement() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Properties properties = new Properties();
//            properties.setProperty("user", "zhang520");
//            properties.setProperty("password", "zhang520");
//            properties.setProperty("useSSL", "false");
//            properties.setProperty("autoReconnect", "true");
//
//            conn = DriverManager.getConnection("jdbc:mysql://"
//                    + Utils.IP + ":" + Utils.DB_PORT + "/flower_shop", properties);
            conn = getConnection();
            statement = conn.createStatement();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return statement;
    }

    private void closeStatement() {
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
     * @param email
     * @return userBean 用户Bean
     */
    public UserBean login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email='" + email + "'";
        UserBean userBean = new UserBean();
        try {
            resultSet = createStatement().executeQuery(sql);
            if (resultSet.next()) {
                userBean.setId(resultSet.getInt("id"));
                userBean.setUsername(resultSet.getString("username"));
                userBean.setEmail(resultSet.getString("email"));
                userBean.setPassWord(resultSet.getString("password"));
                userBean.setIcon(Utils.getURL() + "/flower/" +
                        resultSet.getString("icon"));
                userBean.setCityCode(resultSet.getString("city_code"));
                userBean.setCity(resultSet.getString("city"));
                if (password.equals(userBean.getPassWord())) {
                    return userBean;
                }
            } else {
                return null;
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
        return null;
    }

    /**
     * 用户注册的数据库操作
     *
     * @param userBean 待注册用户的UserBean
     * @return 是否注册成功
     */
    public int signUp(UserBean userBean) {
        String sql = "INSERT INTO users(username, email, password) VALUES('"
                + userBean.getUsername() + "','" + userBean.getEmail()
                + "','" + userBean.getPassWord() + "')";

        String sqlVerificationCode = "UPDATE verification_code SET signed_up = 1 " +
                "WHERE email = '" + userBean.getEmail() + "'";
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sqlVerificationCode);
            conn.commit();
            return 1;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 设置用户名
     *
     * @param userId
     * @param username
     * @return
     */
    public boolean settingUsername(int userId, String username) {
        String sql = "UPDATE users SET username = '" + username + "' WHERE id ='" + userId + "'";
        return settingUserBean(sql);
    }

    /**
     * 设置头像
     *
     * @param userId
     * @param iconPath
     * @return
     */
    public boolean settingIcon(int userId, String iconPath) {
        String sql = "UPDATE users SET icon = '" + iconPath + "' WHERE id ='" + userId + "'";
        return settingUserBean(sql);
    }

    /**
     * 设置用户名
     *
     * @param userId
     * @param password
     * @return
     */
    public boolean settingPassword(int userId, String password) {
        String sql = "UPDATE users SET password = '" + password + "' WHERE id ='" + userId + "'";
        return settingUserBean(sql);
    }

    /**
     * 设置城市
     *
     * @param userId
     * @param city
     * @param cityCode
     * @return
     */
    public boolean settingCity(int userId, String city, String cityCode) {
        String sql = "UPDATE users SET city = '" + city + "', city_code = '"
                + cityCode + "' WHERE id ='" + userId + "'";
        return settingUserBean(sql);
    }

    /**
     * 设置 UserBean
     *
     * @param sql
     * @return 是否设置成功
     */
    private boolean settingUserBean(String sql) {
        try {
            if (createStatement().executeUpdate(sql) == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }


    /**
     * 验证是否已注册，和插入验证码
     *
     * @param userEmail
     * @param verificationCode
     * @param generate_time
     * @return 1：未被注册并成功插入验证码；0：已被注册；-1：出错
     */
    public int verificationCode(String userEmail, String verificationCode, String generate_time) {
        String sqlQuery = "SELECT signed_up FROM verification_code " +
                "WHERE email = '" + userEmail + "'";
        String sqlInsert = "INSERT INTO verification_code(email, code, generate_time) VALUES ('"
                + userEmail + "','" + verificationCode + "','" + generate_time + "')";
        String sqlUpdate = "UPDATE verification_code SET code = '" + verificationCode +
                "',generate_time = '" + generate_time + "' WHERE email = '" + userEmail + "'";
        try {
            resultSet = createStatement().executeQuery(sqlQuery);
            if (resultSet.next()) { // 没有获取过验证码，resultSet.next() 为false
                // 1 已被注册； 0 未被注册
                int signedUp = resultSet.getInt("signed_up");
                if (signedUp == 0) { //未被注册，并返回成功插入1条验证码数据
                    return createStatement().executeUpdate(sqlUpdate);
                } else if (signedUp == 1) { // 已被注册，返回0
                    return 0;
                }
            } else {
                return createStatement().executeUpdate(sqlInsert);
            }
//            if (resultSet == null) {
//                return createStatement().executeUpdate(sqlInsert);
//            } else {
//                while (resultSet.next()) {
//                    // 1 已被注册； 0 未被注册
//                    int signedUp = resultSet.getInt("signed_up");
//                    if (signedUp == 0) { //未被注册，并返回成功插入1条验证码数据
//                        return createStatement().executeUpdate(sqlUpdate);
//                    } else if (signedUp == 1) { // 已被注册，返回0
//                        return 0;
//                    }
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeStatement();
        }
        return -1;
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
     * 插入购物车数据
     *
     * @param flowerId
     * @param userId
     * @return
     */
    public boolean insertShoppingCart(int flowerId, int userId) {
        String sql = "INSERT INTO shopping_cart(flower_id, user_id) " +
                "VALUES ('" + flowerId + "','" + userId + "')";
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

    public String queryNotice() {
        String sql = "SELECT * FROM notice ORDER BY id DESC ";
        try {
            resultSet = createStatement().executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeStatement();
        }
        return null;
    }

    /**
     * 查询商品列表
     *
     * @return 返回商品列表
     */
    public List<FlowerBean> queryAllFlower() {
        String sql = "SELECT * FROM flowers";
        return queryFlowerList(sql);
    }

    /**
     * 查询特定的商品
     *
     * @param startNumber 开始的序号
     * @return 查询所得商品列表（6个）
     */
    public List<FlowerBean> queryFlowers(int startNumber) {
        String sql = "SELECT * FROM flowers LIMIT " + startNumber + " , " + startNumber + 6;
        return queryFlowerList(sql);
    }

    /**
     * 查询指定类别的花
     *
     * @param categoryId
     * @return
     */
    public List<FlowerBean> queryCategoryFlower(int categoryId) {
        String sql = "SELECT * FROM flowers WHERE category_id = " + categoryId;
        return queryFlowerList(sql);
    }

    /**
     * 查询购物车数据
     *
     * @param userId 要查询的用户
     * @return 返回购物车列表
     */
    public List<FlowerBean> queryShoppingCartFlowers(int userId) {
        String sql = "SELECT * FROM flowers WHERE id IN " +
                "(SELECT flower_id FROM shopping_cart WHERE user_id = " + userId + ")";
        return queryFlowerList(sql);
    }

    /**
     * 按关键词搜索商品
     *
     * @param keyword
     * @return
     */
    public List<FlowerBean> searchFlowers(String keyword) {
        String sql = "SELECT * FROM flowers WHERE title LIKE \"%" + keyword + "%\"";
        return queryFlowerList(sql);
    }

    /**
     * 查询商品列表的工具类
     *
     * @param sql 查询的SQL 语句
     * @return 返回商品列表
     */
    private List<FlowerBean> queryFlowerList(String sql) {
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
                flowerBean.setImageURL(Utils.getURL() + "/flower/" + resultSet.getString("image"));
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

    /**
     * 获取分类列表
     *
     * @return
     */
    public List<CateGoryBean> queryCateGoryList() {
        CateGoryBean cateGoryBean;
        List<CateGoryBean> cateGoryBeans = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            resultSet = createStatement().executeQuery(sql);
            while (resultSet.next()) {
                cateGoryBean = new CateGoryBean();
                cateGoryBean.setId(resultSet.getInt("id"));
                cateGoryBean.setName(resultSet.getString("name"));
                cateGoryBeans.add(cateGoryBean);
            }
            return cateGoryBeans;
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

    /**
     * 插入收藏夹商品
     *
     * @param flowerId
     * @param userId
     * @return
     */
    public boolean insertStar(int flowerId, int userId) {
        String sql = "INSERT INTO star(flower_id, user_id) " +
                "VALUES ('" + flowerId + "','" + userId + "')";
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
     * 查询购物车数据
     *
     * @param userId 要查询的用户
     * @return 返回购物车列表
     */
    public List<StarBean> queryStarFlowers(int userId) {
        String sql = "SELECT star.id, star.user_id, star.flower_id AS flower_id, " +
                "title, introduction, price, image FROM flowers, star " +
                "WHERE star.user_id =  " + userId + " AND flowers.id = star.flower_id";
        StarBean starBean;
        ArrayList<StarBean> starBeanArrayList = new ArrayList<>();
        try {
            resultSet = createStatement().executeQuery(sql);
            while (resultSet.next()) {
                starBean = new StarBean();
                starBean.setId(resultSet.getInt("id"));
                starBean.setUserId(resultSet.getInt("user_id"));
                starBean.setFlowerId(resultSet.getInt("flower_id"));
                starBean.setTitle(resultSet.getString("title"));
                starBean.setIntroduction(resultSet.getString("introduction"));
                starBean.setImageURL(Utils.getURL() + "/flower/" + resultSet.getString("image"));
                starBean.setPrice(resultSet.getDouble("price"));
                starBeanArrayList.add(starBean);
            }
            return starBeanArrayList;
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

    public boolean removeStar(int starId) {
        String sql = "DELETE FROM star WHERE id = " + starId;
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建订单
     *
     * @param flowerId
     * @param userId
     * @param address
     * @param order_status
     * @return
     */
    public boolean insertOrder(int flowerId, int userId, String addressee, String telephone,
                               String address, int order_status, String remark) {
        String sql = "INSERT INTO orders(flower_id,user_id,addressee,telephone,address,order_status,remark) "
                + "VALUES ('" + flowerId + "','" + userId + "','" + addressee + "','" + telephone + "','"
                + address + "','" + order_status + "','" + remark + "')";
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
     * 查询订单
     *
     * @param userId
     * @return
     */
    public List<OrderBean> queryOrder(int userId) {
        String sql = "SELECT orders.id,user_id,flower_id,title,introduction,price,image," +
                "addressee,telephone,address,remark,order_status FROM flowers,orders " +
                "WHERE orders.user_id=" + userId + " AND flowers.id=orders.flower_id";

        OrderBean orderBean;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            resultSet = createStatement().executeQuery(sql);
            while (resultSet.next()) {
                orderBean = new OrderBean();
                orderBean.setId(resultSet.getInt("id"));
                orderBean.setUserId(resultSet.getInt("user_id"));
                orderBean.setFlowerId(resultSet.getInt("flower_id"));
                orderBean.setTitle(resultSet.getString("title"));
                orderBean.setIntroduction(resultSet.getString("introduction"));
                orderBean.setImageURL(Utils.getURL() + "/flower/" + resultSet.getString("image"));
                orderBean.setPrice(resultSet.getDouble("price"));
                orderBean.setAddress(resultSet.getString("address"));
                orderBean.setTelephone(resultSet.getString("telephone"));
                orderBean.setAddressee(resultSet.getString("addressee"));
                orderBean.setOrder_status(resultSet.getInt("order_status"));
                orderBean.setRemark(resultSet.getString("remark"));
                orderBeanList.add(orderBean);
            }
            return orderBeanList;
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


    @Deprecated
    public boolean insertCity(ArrayList<CityBean> cityBeanArrayList) {
//        CityBean cityBean = new CityBean();

        try {
            Statement statement = createStatement();
            for (CityBean cityBean : cityBeanArrayList) {
                String sql = "INSERT INTO city(code,city,province) "
                        + "VALUES ('" + cityBean.getCode() + "','" +
                        cityBean.getCity() + "','" + cityBean.getPr() + "')";
                statement.executeUpdate(sql);
            }
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

    public ArrayList<CityBean> queryCityList() {
        String sql = "SELECT * FROM city";
        ArrayList<CityBean> cityBeanArrayList = new ArrayList<>();
        try {
            resultSet = createStatement().executeQuery(sql);
            CityBean cityBean;
            while (resultSet.next()) {
                cityBean = new CityBean();
                cityBean.setCity(resultSet.getString("city"));
                cityBean.setPr(resultSet.getString("province"));
                cityBean.setCode(resultSet.getString("code"));
                cityBeanArrayList.add(cityBean);
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
        return cityBeanArrayList;
    }

    public boolean insertFeedback(int userId, String feedback) {
        String sql = "INSERT INTO feedback(user_id, feedback, create_time) "
                + "VALUES ('" + userId + "','" + feedback + "','"
                + System.currentTimeMillis() + "')";
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

    public boolean insertComment(int userId, int flowerId, int rate, String comment, String createTime) {
        String sql = "INSERT INTO comment(user_id,flower_id,rate,comment,create_time) " +
                "VALUES ('" + userId + "','" + flowerId + "','" + rate
                + "','" + comment + "','" + createTime + "')";
        try {
            return createStatement().executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return false;
    }

}
