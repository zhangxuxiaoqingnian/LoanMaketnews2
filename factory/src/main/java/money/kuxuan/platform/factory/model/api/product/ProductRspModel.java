package money.kuxuan.platform.factory.model.api.product;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Page;
import money.kuxuan.platform.factory.model.db.Product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ProductRspModel {
    List<Product> data;
    Page pageinfo;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public Page getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageinfo = pageinfo;
    }
}
