package money.kuxuan.platform.factory.model.api.product;

import java.util.List;

import money.kuxuan.platform.factory.model.db.BannerData;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class BannerRspModel {
    private List<BannerData> homeact;

    public List<BannerData> getHomeact() {
        return homeact;
    }

    public void setHomeact(List<BannerData> homeact) {
        this.homeact = homeact;
    }
}
