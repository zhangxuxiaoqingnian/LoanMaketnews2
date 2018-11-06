package com.smileflowpig.money.factory.model.api.detail;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class DetailModel {
    String id;
    String enter_source;

    public String getEnter_source() {
        return enter_source;
    }

    public void setEnter_source(String enter_source) {
        this.enter_source = enter_source;
    }

    public DetailModel(String id, String enter_source) {
        this.id = id;
        this.enter_source = enter_source;
    }

    public DetailModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
