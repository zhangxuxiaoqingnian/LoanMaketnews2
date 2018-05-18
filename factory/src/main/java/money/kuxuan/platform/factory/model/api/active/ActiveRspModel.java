package money.kuxuan.platform.factory.model.api.active;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Active;
import money.kuxuan.platform.factory.model.db.Page;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ActiveRspModel {
    private List<Active> data;
    private Page pageinfo;

    public List<Active> getData() {
        return data;
    }

    public void setData(List<Active> data) {
        this.data = data;
    }

    public Page getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageinfo = pageinfo;
    }
}
