package utils;

/**
 * 静态工具类
 * Created by joker on 5/4 0004.
 */
public class Utils {
    public static String IP = "123.206.201.169";
    public static final String PORT = "8080";
    public static final String DB_PORT = "3306";

    /**
     * 构造URL头
     *
     * @return 返回项目根(.)URL
     */
    public static String getURL(){
        return "http://"+ IP+":"+PORT+"/FlowerShop";
    }

}
