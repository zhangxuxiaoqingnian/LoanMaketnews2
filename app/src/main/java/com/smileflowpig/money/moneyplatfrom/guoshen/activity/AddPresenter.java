package com.smileflowpig.money.moneyplatfrom.guoshen.activity;


import com.smileflowpig.money.moneyplatfrom.guoshen.nethelper.AddHelper;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel1;
import com.smileflowpig.money.factory.model.api.guoshen.GetRepaymentModel;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public class AddPresenter extends BasePresenter<AddConreact.View> implements AddConreact.Presenter {


    public AddPresenter(AddConreact.View view) {
        super(view);
    }


    @Override
    public void addRepayment(AddModel addModel) {

        AddHelper.addRepayment(addModel, new DataSource.Callback() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(Object o) {


                getView().setAddEndView();


            }
        });

    }

    @Override
    public void addRepayment1(AddModel1 addModel1) {

        AddHelper.addRepayment1(addModel1, new DataSource.Callback() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(Object o) {

                getView().setAddEndView();

            }
        });




    }

    @Override
    public void getRepayment(int id) {


        AddHelper.getRepayment(id, new DataSource.Callback<RspModel<GetRepaymentModel>>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(RspModel<GetRepaymentModel> getRepaymentModelRspModel) {

                getView().setRepaymentView(getRepaymentModelRspModel.getRst());

            }
        });



    }
}
