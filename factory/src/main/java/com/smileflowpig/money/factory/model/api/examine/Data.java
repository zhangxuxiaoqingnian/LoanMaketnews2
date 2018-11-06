package com.smileflowpig.money.factory.model.api.examine;

import com.smileflowpig.money.factory.model.db.DataE;
import com.smileflowpig.money.factory.model.db.Page;

import java.util.List;

public class Data{
    private List<DataE> data;
    private Page pageinfo;

    public List<DataE> getData() {
        
        return data;
    }

    public void setData(List<DataE> data) {
        this.data = data;
    }

    public Page getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageinfo = pageinfo;
    }
}