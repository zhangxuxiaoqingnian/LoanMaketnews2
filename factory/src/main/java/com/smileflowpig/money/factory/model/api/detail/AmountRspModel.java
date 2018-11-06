package com.smileflowpig.money.factory.model.api.detail;

import com.smileflowpig.money.factory.model.db.Amount;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class AmountRspModel {
    List<Amount> data;

    public List<Amount> getData() {
        return data;
    }

    public void setData(List<Amount> data) {
        this.data = data;
    }
}
