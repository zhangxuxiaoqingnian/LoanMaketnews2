package money.kuxuan.platform.factory.model.api.account;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LoginByCodeModel {
    String phone;
    String code;
    String source;
    String token;

    public LoginByCodeModel(String phone, String code,String source) {
        this.phone = phone;
        this.code = code;
        this.source = source;
    }

    public LoginByCodeModel(String phone, String token) {
        this.phone = phone;
        this.token = token;
    }
}
