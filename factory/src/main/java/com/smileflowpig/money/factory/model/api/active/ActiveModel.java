package com.smileflowpig.money.factory.model.api.active;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class ActiveModel {
    private int page;

    public ActiveModel(int page, int num) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ActiveModel(int page) {
        this.page = page;
    }
}
