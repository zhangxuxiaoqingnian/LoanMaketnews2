package money.kuxuan.platform.factory.model.api.launcher;

import java.util.List;

import money.kuxuan.platform.factory.presenter.notice.Notice;

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
