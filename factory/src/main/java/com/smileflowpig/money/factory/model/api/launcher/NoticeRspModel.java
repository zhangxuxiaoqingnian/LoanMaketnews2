package com.smileflowpig.money.factory.model.api.launcher;

import com.smileflowpig.money.factory.presenter.notice.Notice;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class NoticeRspModel {
    private List<Notice> data;

    public List<Notice> getData() {
        return data;
    }

    public void setData(List<Notice> data) {
        this.data = data;
    }
}
