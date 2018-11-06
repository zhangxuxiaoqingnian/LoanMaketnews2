package com.smileflowpig.money.moneyplatfrom.guoshen.activity;


import android.support.annotation.StringRes;

import com.smileflowpig.money.moneyplatfrom.guoshen.nethelper.HomeHelper;

import java.util.List;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.guoshen.PopModel;
import com.smileflowpig.money.factory.model.api.guoshen.RepaymentListBean;
import com.smileflowpig.money.factory.model.db.User;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public class HomePresenter extends BasePresenter<HomeConreact.View> implements HomeConreact.Presenter {


    public HomePresenter(HomeConreact.View view) {
        super(view);
    }


    @Override
    public void getLoginStats() {

        AccountHelper.loginState(new DataSource.Callback<User>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){
                    getView().setNoLogin();
                }
            }

            @Override
            public void onDataLoaded(User user) {
                if(getView()!=null){
                    getView().stateLogin(user);
                }

            }
        });


    }

    @Override
    public void getRepaymentList() {


        HomeHelper.getRepaymentList(new DataSource.Callback<RspModel<List<RepaymentListBean>>>() {
            @Override
            public void onDataNotAvailable(int strRes) {


            }

            @Override
            public void onDataLoaded(RspModel<List<RepaymentListBean>> repaymentListBeanRspModel) {

                 getView().setRepaymentListView(repaymentListBeanRspModel.getRst());

            }
        });


    }

    @Override
    public void getPopDetai(int id) {


        HomeHelper.getPopDetailData(id, new DataSource.Callback<RspModel<List<PopModel>>>() {
            @Override
            public void onDataNotAvailable(int strRes) {



            }

            @Override
            public void onDataLoaded(RspModel<List<PopModel>> listRspModel) {

                 getView().setPopDtailView(listRspModel.getRst());

            }
        });




    }

    @Override
    public void putStatus(String putStats) {


        HomeHelper.putStatus(putStats, new DataSource.Callback<RspModel>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                getView().putStatusView();
            }
        });



    }


}
