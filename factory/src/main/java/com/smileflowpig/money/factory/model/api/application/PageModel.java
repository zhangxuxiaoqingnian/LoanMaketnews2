package com.smileflowpig.money.factory.model.api.application;

import com.smileflowpig.money.factory.model.api.creditcard.PageModelAll;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class PageModel extends PageModelAll{
    private int pageId;
    private String status;


    public PageModel(int pageid, String status) {
        this.pageId = pageid;
        this.status = status;
    }


}
