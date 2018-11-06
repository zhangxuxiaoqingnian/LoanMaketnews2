package com.smileflowpig.money.factory.model.api.bill;

import com.smileflowpig.money.factory.bean.BillManagerBean;

import java.util.ArrayList;

public class BillRspModel {

    private ArrayList<BillManagerBean> data;


    public ArrayList<BillManagerBean> getData() {
        return data;
    }

    public void setData(ArrayList<BillManagerBean> data) {
        this.data = data;
    }
}
