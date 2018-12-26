package com.smileflowpig.money.moneyplatfrom.guoshen.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.activities.AboutActivity;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.SecondEvent;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.util.LoginUtil;
import com.smileflowpig.money.factory.util.SPUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public class MineActivity extends PresenterActivity<MineConreact.Presenter> implements MineConreact.View {

    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";
    int flag = 0;

    @BindView(R.id.phone)
    public TextView phoneTv;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @BindView(R.id.back)
    public ImageView back;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        EventBus.getDefault().register(this);
        mPresenter.start();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void show(Context context) {
        Intent intent = new Intent(context, MineActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(SecondEvent event) {

        String msg = event.getMsg();
        if (msg.equals("登录/注册")) {
            flag = 0;
        } else {
            flag = 1;
            if (frameLayout.getVisibility() == View.GONE) {
                frameLayout.setVisibility(View.VISIBLE);
            }
            finish();
        }
        phoneTv.setText(msg);

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.guoshen_aboutme;

    }

    @OnClick(R.id.top_rel)
    void topClick() {
        if (flag == 0) {
            //            AccountActivity.show(getContext());
            //            getActivity().finish();
            Intent intent = new Intent(this, AccountActivity.class);
            this.startActivityForResult(intent, com.smileflowpig.money.moneyplatfrom.Constant.Code.REQUEST_CODE);
        } else {
            return;
        }
    }

    @OnClick(R.id.set_layout)
    void onSetting() {
        AboutActivity.show(this);
    }

    @BindView(R.id.framelayout)
    FrameLayout frameLayout;


    @Override
    public void stateLogin(User user) {
        hideLoading();
        phoneTv.setText(user.phone);
        flag = 1;

        if (frameLayout.getVisibility() == View.GONE) {
            frameLayout.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.exist_button)
    public void loginOut() {
        mPresenter.exist();
    }

    @Override
    public void setNoLogin() {
        hideLoading();
        phoneTv.setText("登录/注册");
        flag = 0;
        Boolean isExit = (Boolean) SPUtil.get(getApplicationContext(), Constant.UserInfo.ISEXITE, true);
        if (!isExit) {
            LoginUtil.getInstance().reLoadLogin(new LoginUtil.OnLoadListener() {
                @Override
                public void onLoadSuccess(User user) {
                    stateLogin(user);
                }

                @Override
                public void onLoadFail(int strRes) {
                    phoneTv.setText("登录/注册");
                    flag = 0;
                }

            });
        }
    }

    @Override
    public void ExistSuccess() {
        EventBus.getDefault().post(new SecondEvent("登录/注册"));
//        SPUtil.clear(this);
        SPUtil.putAndApply(this, Constant.UserInfo.ISEXITE, true);
        SPUtil.putAndApply(this, Constant.UserInfo.SESSIONID, "");
        finish();
    }


    @Override
    protected MineConreact.Presenter initPresenter() {
        return new MinePresenter(this);
    }


}
