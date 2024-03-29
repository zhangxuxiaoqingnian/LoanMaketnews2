package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.content.Intent;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.ZhenxinUrlJson;
import com.smileflowpig.money.factory.data.helper.HongbaoHelper;
import com.smileflowpig.money.factory.util.LoginStatusUtil;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.TaskHongbaoActivity;
import com.smileflowpig.money.moneyplatfrom.activities.WebviewActivity;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.OnClick;

/**
 * Created by 小狼 on 2018/11/14.
 */

public class CreditFragment extends PresenterFragment {


    public static final int ZHENGXIN_REQUEST_CODE = 12;
    public static final int ZHENGXIN_RESULT_CODE = 13;

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
//        String url = "https://m.tianxiaxinyong.com/cooperation/crp-op/signin.html?channel=10221247&from=singlemessage&isappinstalled=0&a=&i=&t=1540434586889";
//        WebActivity.show(getActivity(), "个人征信", url);
        MobclickAgent.onEvent(getActivity(),"goTocredit");
        if (LoginStatusUtil.isLogin()) {
            getUrl();
        } else {
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            intent.putExtra("zhengxin", true);
            startActivityForResult(intent, ZHENGXIN_REQUEST_CODE);
        }

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ZHENGXIN_REQUEST_CODE && resultCode == ZHENGXIN_RESULT_CODE) {
            //登录成功获取url
            getUrl();

        }
    }

    Activity activity;

    private void getUrl() {
        activity = (Activity) getActivity();
        activity.showOnlyDialogLoadding();
//        ToastUtil.show(getActivity(), "获取url");

        HongbaoHelper.getZhenxinUrl(new DataSource.Callback<ZhenxinUrlJson>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                activity.hideOnDialogLoading();
                ToastUtil.show(getActivity(), "获取链接失败，请稍后重试");
            }

            @Override
            public void onDataLoaded(ZhenxinUrlJson zhenxinUrlJson) {
                activity.hideOnDialogLoading();
                if (zhenxinUrlJson != null) {
                    String url = zhenxinUrlJson.getUrl();
                    WebActivity.show(getActivity(), "个人征信", url);
                }else{
                    ToastUtil.show(getActivity(), "获取链接失败，请稍后重试");
                }
            }
        });
    }
}
