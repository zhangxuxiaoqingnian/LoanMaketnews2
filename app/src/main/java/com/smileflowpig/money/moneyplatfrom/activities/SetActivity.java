package com.smileflowpig.money.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.smileflowpig.money.moneyplatfrom.helper.SPUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.SecondEvent;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.presenter.account.ExistContract;
import com.smileflowpig.money.factory.presenter.account.ExistPresenter;


public class SetActivity extends PresenterActivity<ExistContract.Presenter>
        implements ExistContract.View {

    private SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    public static void show(Context context) {
        Intent intent = new Intent(context, SetActivity.class);
        context.startActivity(intent);
    }

    //顶部导航栏返回键点击事件
    @OnClick(R.id.back)
    void back() {
        finish();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_set;
    }


    @OnClick(R.id.exist_button)
    void onExist() {
        mPresenter.exist();
        sp = getSharedPreferences("Deng",MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    @Override
    public void ExistSuccess() {
        EventBus.getDefault().post(new SecondEvent("登录/注册"));
        SPUtil.clear(this);
        com.smileflowpig.money.factory.util.SPUtil.clear(this);
        com.smileflowpig.money.factory.util.SPUtil.putAndApply(this, Constant.UserInfo.ISEXITE, true);
        finish();

    }

    @OnClick(R.id.about)
    void aboutClick() {
        AboutActivity.show(this);
    }

    @Override
    protected ExistContract.Presenter initPresenter() {
        return new ExistPresenter(this);
    }

    @OnClick(R.id.update)
    void clickUpdate() {
        UpdateActivity.show(this);
    }
}
