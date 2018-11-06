package com.smileflowpig.money.moneyplatfrom.frags.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.ApActivity1;
import com.smileflowpig.money.moneyplatfrom.activities.MemoActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MyCollectActivity;
import com.smileflowpig.money.moneyplatfrom.activities.RadiersActivity;
import com.smileflowpig.money.moneyplatfrom.activities.RecordActivity;
import com.smileflowpig.money.moneyplatfrom.activities.SetActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.SecondEvent;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.presenter.state.StateContract;
import com.smileflowpig.money.factory.presenter.state.StatePresenter;
import com.smileflowpig.money.factory.util.LoginUtil;
import com.smileflowpig.money.factory.util.SPUtil;


/**
 *
 */
public class MineFragment extends PresenterFragment<StateContract.Presenter>
        implements StateContract.View ,View.OnClickListener{

    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";
    int flag = 0;

    @BindView(R.id.phone)
    public TextView phoneTv;

//    @BindView(R.id.ap_lin)
//    LinearLayout ap_lin;
    @BindView(R.id.apply_lin)
    LinearLayout apply_lin;
    @BindView(R.id.lin_problem)
    LinearLayout lin_problem;
    @BindView(R.id.mycollect)
    LinearLayout mycollect;
    @BindView(R.id.newrecord)
    LinearLayout record;
    @BindView(R.id.memo)
    LinearLayout memo;


    private SelfDialog selfDialog;


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPresenter.start();
        if (checkChannel()==false) {
//            ap_lin.setVisibility(View.GONE);
            apply_lin.setVisibility(View.GONE);
            lin_problem.setVisibility(View.GONE);
        }
        mycollect.setOnClickListener(this);
        record.setOnClickListener(this);
        memo.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(SecondEvent event) {

        String msg = event.getMsg();
        if (msg.equals("登录/注册")) {
            flag = 0;
        } else {
            flag = 1;
        }
        Log.d("harvic", msg);
        phoneTv.setText(msg);

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.top_rel)
    void topClick() {
        if (flag == 0) {
//            AccountActivity.show(getContext());
//            getActivity().finish();
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            getActivity().startActivityForResult(intent, com.smileflowpig.money.moneyplatfrom.Constant.Code.REQUEST_CODE);
        } else {
            return;
        }
    }

    @OnClick(R.id.set_layout)
    void onSetting() {
        if (flag == 0) {
            createDialog(R.string.no_login);
            return;
        }
        SetActivity.show(getContext());
    }

    @OnClick(R.id.lin_problem)
    public void commonProblem(){

        RadiersActivity.show(getActivity());

    }


    @Override
    public void stateLogin(User user) {
        phoneTv.setText(user.phone);
        flag = 1;
    }

    @Override
    public void setNoLogin() {
        phoneTv.setText("登录/注册");
        flag = 0;
        Boolean isExit = (Boolean) SPUtil.get(getActivity(), Constant.UserInfo.ISEXITE, true);
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
    protected StateContract.Presenter initPresenter() {
        return new StatePresenter(this);
    }


    /**
     * Dialog
     */
    public void createDialog(@StringRes int str) {
        selfDialog = new SelfDialog(getContext());
        selfDialog.setTitle("温馨提示");
        selfDialog.setMessage(str);
        selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
    }

//    @OnClick(R.id.ap_lin)
//    void onApClick() {
//        if (flag == 0) {
//            createDialog(R.string.no_login);
//            return;
//        }
//        ApActivity.show(getContext());
//    }


    @OnClick(R.id.apply_lin)
    void onApplyClick(){
        if(flag==0){
            createDialog(R.string.no_login);
            return;
        }

        ApActivity1.show(getContext());
    }





    private boolean checkChannel() {
        SharedPreferences sp = getActivity().getSharedPreferences(CHANNEL,
                Context.MODE_PRIVATE);
        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
        return channelOk.equals("0");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //我的收藏
            case R.id.mycollect:
                Intent intent=new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
                break;
                //浏览记录
            case R.id.newrecord:
                Intent intent1=new Intent(getActivity(),RecordActivity.class);
                startActivity(intent1);
                break;
                //还款备忘录
            case R.id.memo:
                Intent intent2=new Intent(getActivity(), MemoActivity.class);
                startActivity(intent2);
                break;

        }
    }
}





