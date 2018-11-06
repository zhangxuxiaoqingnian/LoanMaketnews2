package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.smileflowpig.money.moneyplatfrom.activities.MyBillActivity;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

/**
 * Created by 小狼 on 2018/10/10.
 */

public class MyBillFragment extends PresenterFragment {
    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.billfrag_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent=new Intent(getActivity(), MyBillActivity.class);
        startActivity(intent);

    }
}
