package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.moneyplatfrom.activities.WebviewActivity;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import butterknife.OnClick;

/**
 * Created by 小狼 on 2018/11/14.
 */

public class CreditFragment extends PresenterFragment {
    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.credit_fragment_layout;
    }


    @OnClick(R.id.chakanxinyong)
    void onCredit() {
        String url = "https://m.tianxiaxinyong.com/cooperation/crp-op/signin.html?channel=10221247&from=singlemessage&isappinstalled=0&a=&i=&t=1540434586889";
        WebActivity.show(getActivity(), "个人征信", url);
    }
}
