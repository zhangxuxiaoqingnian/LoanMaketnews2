package com.smileflowpig.money.factory.presenter.home;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.message.MessageRspModel;
import com.smileflowpig.money.factory.model.db.BannerData;
import com.smileflowpig.money.factory.model.db.Category;
import com.smileflowpig.money.factory.model.db.Product;
import com.smileflowpig.money.factory.presenter.notice.Notice;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface HomeContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void requestData();
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        //界面端只能刷新整个数据
        //// TODO: 2017/8/25 0025 想要刷新单条数据
        void onDone(List<Product> product);

        void onBanner(List<BannerData> bannerDataList);

        void onCategory(List<Category> categoryList);
        //上拉加载
        void refresh(List<Product> products);

        //没有更多数据的回调
        void NoData();

        void getMessageData(MessageRspModel messageRspModel, boolean flag);


        void getNotice(List<Notice> noticeList);

        void version(String url);
    }
}
