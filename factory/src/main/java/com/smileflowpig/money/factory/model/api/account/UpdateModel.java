package com.smileflowpig.money.factory.model.api.account;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class UpdateModel {
    private String new_password;
    private String password;

    public UpdateModel(String new_password, String password) {
        this.new_password = new_password;
        this.password = password;
    }
}
