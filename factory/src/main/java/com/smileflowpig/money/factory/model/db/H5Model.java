package com.smileflowpig.money.factory.model.db;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class H5Model{
    private String product_Id;
    private String link;
    private String type;

    public String getProduct_id() {
        return product_Id;
    }

    public void setProduct_id(String product_id) {
        this.product_Id = product_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}