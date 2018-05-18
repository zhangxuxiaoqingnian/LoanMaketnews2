package money.kuxuan.platform.factory.model.api.product;

import java.util.List;

import money.kuxuan.platform.factory.model.db.CreditCardAppliProduct;
import money.kuxuan.platform.factory.model.db.Page;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class CreditCardAppliModel {


    List<CreditCardAppliProduct> list;
    Page pageInfo;

    public List<CreditCardAppliProduct> getList() {
        return list;
    }

    public void setList(List<CreditCardAppliProduct> list) {
        this.list = list;
    }

    public Page getPageinfo() {
        return pageInfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageInfo = pageinfo;
    }


}
