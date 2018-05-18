package money.kuxuan.platform.factory.model.api.detail;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Amount;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class AmountRspModel {
    List<Amount> data;

    public List<Amount> getData() {
        return data;
    }

    public void setData(List<Amount> data) {
        this.data = data;
    }
}
