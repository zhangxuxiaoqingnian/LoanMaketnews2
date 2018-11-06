package com.smileflowpig.money.factory.model.api.bill;

public class DetialModel {

    private String page;
    private String platform_id;

    public DetialModel(String page, String platform_id) {
        this.page = page;
        this.platform_id = platform_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }
}
