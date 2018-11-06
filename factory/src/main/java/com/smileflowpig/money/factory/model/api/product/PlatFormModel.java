package com.smileflowpig.money.factory.model.api.product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class PlatFormModel {
    private String is_hot;
    private int page;
    private String is_new;

    public PlatFormModel(String is_hot, int page,String is_new) {
        this.is_hot = is_hot;
        this.page = page;
        this.is_new = is_new;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PlatFormModel{" +
                "is_hot='" + is_hot + '\'' +
                ", page=" + page +
                '}';
    }
}
