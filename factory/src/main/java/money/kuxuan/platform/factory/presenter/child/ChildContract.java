package money.kuxuan.platform.factory.presenter.child;

import java.util.List;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.db.Product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ChildContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void requestData();
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        //界面端只能刷新整个数据
        //// TODO: 2017/8/25 0025 想要刷新单条数据
        void onDone(List<Product> product);

        //上拉加载
        void refresh(List<Product> products);

        //没有更多数据的回调
        void NoData();
    }
}
