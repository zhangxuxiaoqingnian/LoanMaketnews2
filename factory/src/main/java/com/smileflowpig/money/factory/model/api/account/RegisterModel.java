package com.smileflowpig.money.factory.model.api.account;

/**
 * 注册请求时的Model
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class RegisterModel {
    private String phone;
    private String password;
    private String code;
    private String source;

    public RegisterModel(String account, String password, String name) {
        this(account, password, name, null);
    }

    public RegisterModel(String phone, String password, String code, String source) {
        this.phone = phone;
        this.password = password;
        this.code = code;
        this.source = source;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "account='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
