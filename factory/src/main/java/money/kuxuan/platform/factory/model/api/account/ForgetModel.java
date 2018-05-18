package money.kuxuan.platform.factory.model.api.account;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ForgetModel {
    private String phone;
    private String password;
    private String code;

    public ForgetModel(String phone, String password, String code) {
        this.phone = phone;
        this.password = password;
        this.code = code;
    }
}
