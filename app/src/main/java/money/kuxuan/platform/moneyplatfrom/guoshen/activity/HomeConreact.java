package money.kuxuan.platform.moneyplatfrom.guoshen.activity;

import java.util.List;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.api.guoshen.PopModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.model.db.User;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public interface HomeConreact {

    interface View extends BaseContract.View<HomeConreact.Presenter> {

        void stateLogin(User user);

        void setNoLogin();

        void setRepaymentListView(List<RepaymentListBean> repaymentListBeans);

        void setPopDtailView(List<PopModel> popModels);

        void putStatusView();


    }

    interface Presenter extends BaseContract.Presenter {


        void getLoginStats();

        void getRepaymentList();

        void getPopDetai(int id);

        void putStatus(String putStats);


    }

}
