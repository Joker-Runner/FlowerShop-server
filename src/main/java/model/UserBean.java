package model;

import org.springframework.stereotype.Component;

/**
 * 用户 Bean
 * Created by joker on 4/11 0011.
 */
@Component
public class UserBean {
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
