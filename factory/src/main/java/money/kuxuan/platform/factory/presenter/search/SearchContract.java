package money.kuxuan.platform.factory.presenter.search;

import java.util.List;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.api.search.FilterRspModel;
import money.kuxuan.platform.factory.model.db.Product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface SearchContract {

    interface Presenter extends BaseContract.Presenter{
        void refreshData(String term,String amount,String user_title,String ranking_list,int type);

       void  setPage(int page);

        void setHasNext(boolean hasNext);

        void choseCategory(String term,String amount,String user_title,String ranking_list);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void onSearchDone(List<Product> product);

        //上拉加载
        void refresh(List<Product> products);

        //没有更多数据的回调
        void NoData();

        void getList(FilterRspModel filterRspModel);


        void getChoose(List<Product> products);

    }
}
