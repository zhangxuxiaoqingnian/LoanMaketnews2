package com.smileflowpig.money.moneyplatfrom.guoshen.activity;

import java.util.List;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.guoshen.PopModel;
import com.smileflowpig.money.factory.model.api.guoshen.RepaymentListBean;
import com.smileflowpig.money.factory.model.db.User;

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
