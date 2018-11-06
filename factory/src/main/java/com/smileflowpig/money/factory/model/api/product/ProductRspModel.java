package com.smileflowpig.money.factory.model.api.product;

import com.smileflowpig.money.factory.model.db.Page;
import com.smileflowpig.money.factory.model.db.Product;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ProductRspModel {
    List<Product> data;
    Page pageinfo;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public Page getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageinfo = pageinfo;
    }
}
