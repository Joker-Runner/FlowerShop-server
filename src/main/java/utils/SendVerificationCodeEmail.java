package utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * Created by joker on 5/17 0017.
 */
public class SendVerificationCodeEmail {

    private static boolean isSSL = true;
    private static String host = "smtp.163.com";
    private static int port = 465;
    private static String from = "joker_flower_shop@163.com";
//    private static String to = "";
    private static String username = "joker_flower_shop@163.com";
    private static String password = "zhang520";

//    public static void main(String[] args) {
//        System.out.println(
//                sendMail("joker_runner@qq.com", "233455"));
//    }

    /**
     * 发送邮件
     * @param userEmail 用户邮箱
     * @param verificationCode 验证码
     * @return 是否发送成功
     */
    public static boolean sendMail(String userEmail, String verificationCode) {
        try {
            Email email = new SimpleEmail();
            email.setSSLOnConnect(isSSL);
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthentication(username, password);
            email.setFrom(from);
            email.addTo(userEmail);
            email.setSubject("网上花店验证码");
            email.setMsg("【网上花店】亲爱的用户，你好！\n你的验证码是: " + verificationCode + "\n" +
                    "此邮件由系统自动发出，请勿直接回复。\n如果您没有要求绑定邮箱，请忽略此邮件。\n祝你使用愉快！"
            );
            email.send();
            System.out.println("发送成功");
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return false;
    }
}
