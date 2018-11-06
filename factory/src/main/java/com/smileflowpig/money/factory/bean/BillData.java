package com.smileflowpig.money.factory.bean;

import java.util.ArrayList;

public class BillData {

    private ArrayList<BillManagerBean> data;

    private AllMyBean.RstBean.PageInfoBean pageinfo;

    public ArrayList<BillManagerBean> getData() {
        return data;
    }

    public void setData(ArrayList<BillManagerBean> data) {
        this.data = data;
    }

    public AllMyBean.RstBean.PageInfoBean getPageInfoBean() {
        return pageinfo;
    }

    public void setPageInfoBean(AllMyBean.RstBean.PageInfoBean pageInfoBean) {
        this.pageinfo = pageInfoBean;
    }
}
