package money.kuxuan.platform.factory.presenter.statehome;

import java.util.List;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.api.examine.Banner;
import money.kuxuan.platform.factory.model.api.examine.RspExamHomeModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public interface ExamineContract {

    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void refreshData();

        void getInfoList(int index);

        void setPage(int page);

        void hasNet(boolean flag);

    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void getHomeData(RspExamHomeModel rspExamHomeModel);

        void tabList(Banner banner, List<String> strings);

        void NoData();

        void getRefreshData(RspExamHomeModel rspExamHomeModel);
    }
}
