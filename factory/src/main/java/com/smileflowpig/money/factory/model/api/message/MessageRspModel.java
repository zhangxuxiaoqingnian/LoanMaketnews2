package com.smileflowpig.money.factory.model.api.message;

import com.smileflowpig.money.factory.model.db.Message;
import com.smileflowpig.money.factory.model.db.Page;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class MessageRspModel {
    private List<Message> list;
    private int max_id;
    private Page pageInfo;

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public int getMax_id() {
        return max_id;
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }

    public Page getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Page pageInfo) {
        this.pageInfo = pageInfo;
    }
}
