package money.kuxuan.platform.factory.model.api.account;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LoginModel {
    private String phone;
    private String password;


    public LoginModel(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }



    public String getAccount() {
        return phone;
    }

    public void setAccount(String account) {
        this.phone = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
