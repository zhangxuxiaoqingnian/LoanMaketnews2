package com.smileflowpig.money.factory.model.api.product;

import com.smileflowpig.money.factory.model.db.CreditCardProduct;
import com.smileflowpig.money.factory.model.db.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class CreditCardModel {

    List<CreditCardProduct> list;
    Page pageInfo;

    public List<CreditCardProduct> getList() {
        return list;
    }

    public void setList(List<CreditCardProduct> list) {
        this.list = list;
    }

    public Page getPageinfo() {
        return pageInfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageInfo = pageinfo;
    }

}
