package money.kuxuan.platform.factory.model.api.product;

import java.util.List;

import money.kuxuan.platform.factory.model.db.ApplyProduct;
import money.kuxuan.platform.factory.model.db.Page;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ApplyProductModel {
    List<ApplyProduct> list;
    Page pageInfo;

    public List<ApplyProduct> getList() {
        return list;
    }

    public void setList(List<ApplyProduct> list) {
        this.list = list;
    }

    public Page getPageinfo() {
        return pageInfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageInfo = pageinfo;
    }
}
