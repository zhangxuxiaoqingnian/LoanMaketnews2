package money.kuxuan.platform.factory.model.api.search;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Dialogs;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class FilterRspModel {
    private List<Dialogs> list;
    private List<Dialogs> month;
    private List<Dialogs> userlist;

    public List<Dialogs> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<Dialogs> userlist) {
        this.userlist = userlist;
    }

    public List<Dialogs> getList() {
        return list;
    }

    public void setList(List<Dialogs> list) {
        this.list = list;
    }

    public List<Dialogs> getMonth() {
        return month;
    }

    public void setMonth(List<Dialogs> month) {
        this.month = month;
    }
}
