package com.smileflowpig.money.factory.model.api.web;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class WebModel {
    private String product_apply_id;
    private String status;
    private String skip_type;
    private String product_id;

    public WebModel(String product_apply_id, String status, String skip_type, String product_id) {
        this.product_apply_id = product_apply_id;
        this.status = status;
        this.skip_type = skip_type;
        this.product_id = product_id;
    }
}
