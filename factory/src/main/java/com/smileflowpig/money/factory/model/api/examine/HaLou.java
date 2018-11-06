package com.smileflowpig.money.factory.model.api.examine;

/**
 * Created by 小狼 on 2018/8/29.
 */

public class HaLou {

    private String package_id;
    private int page;
    private String classification_id;

    public HaLou(String package_id, int page, String classification_id) {
        this.package_id = package_id;
        this.page = page;
        this.classification_id = classification_id;
    }
}
