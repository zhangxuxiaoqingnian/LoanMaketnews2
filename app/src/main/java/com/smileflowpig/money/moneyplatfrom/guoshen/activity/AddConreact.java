package com.smileflowpig.money.moneyplatfrom.guoshen.activity;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel1;
import com.smileflowpig.money.factory.model.api.guoshen.GetRepaymentModel;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public interface AddConreact {

    interface View extends BaseContract.View<AddConreact.Presenter> {


        void setAddEndView();

        void setRepaymentView(GetRepaymentModel getRepaymentModel);

    }

    interface Presenter extends BaseContract.Presenter {


        void addRepayment(AddModel addModel);
        void addRepayment1(AddModel1 addModel1);
        void getRepayment(int id);

    }







}
