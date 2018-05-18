package money.kuxuan.platform.factory.model.api.examine;

import java.util.List;

import money.kuxuan.platform.factory.model.db.DataE;
import money.kuxuan.platform.factory.model.db.Page;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class RspExamHomeModel {
    private Banner banner;
    private Data listdata;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Data getListdata() {
        return listdata;
    }

    public void setListdata(Data listdata) {
        this.listdata = listdata;
    }
}





