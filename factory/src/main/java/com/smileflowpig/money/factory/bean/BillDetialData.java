package com.smileflowpig.money.factory.bean;

import java.util.ArrayList;

public class BillDetialData {


    private ArrayList<BillDetialBean> bill_list;
    private AllMyBean.RstBean.PageInfoBean pageInfo;
    private Amount amount;

    public ArrayList<BillDetialBean> getBill_list() {
        return bill_list;
    }

    public void setBill_list(ArrayList<BillDetialBean> bill_list) {
        this.bill_list = bill_list;
    }

    public AllMyBean.RstBean.PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(AllMyBean.RstBean.PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }


}
