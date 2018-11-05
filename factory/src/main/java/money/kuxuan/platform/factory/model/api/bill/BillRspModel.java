package money.kuxuan.platform.factory.model.api.bill;

import java.util.ArrayList;

import money.kuxuan.platform.factory.bean.BillManagerBean;

public class BillRspModel {

    private ArrayList<BillManagerBean> data;


    public ArrayList<BillManagerBean> getData() {
        return data;
    }

    public void setData(ArrayList<BillManagerBean> data) {
        this.data = data;
    }
}
