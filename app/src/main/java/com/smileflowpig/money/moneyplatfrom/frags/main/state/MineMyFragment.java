package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.activities.AboutActivity;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.ActionActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MySetActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.presenter.state.StateContract;
import com.smileflowpig.money.factory.presenter.state.StatePresenter;

/**
 * Created by 小狼 on 2018/10/12.
 */

    public class MineMyFragment extends PresenterFragment<StateContract.Presenter>
            implements StateContract.View,View.OnClickListener{

    private View inflate;
    private CircleImageView icon;
    private TextView mytext;
    private TextView mylogin;
    private int flag=0;
    private RelativeLayout isfan;
    private RelativeLayout guan;
    private SelfDialog selfDialog;
    private String myphone;
    private String myiconurl;
    private String myname;

    private boolean liulang2;
    private TextView headset;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.mine_my_layout, null);
        return inflate;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mine_my_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        mylogin.setOnClickListener(this);
        guan.setOnClickListener(this);
        isfan.setOnClickListener(this);

        headset.setOnClickListener(this);

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPresenter.start();

    }
    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
        SharedPreferences sp = getActivity().getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang2 = sp.getBoolean("liulang", false);
    }

    public void initview(){

        mylogin = (TextView) inflate.findViewById(R.id.mylogin);
        icon = (CircleImageView) inflate.findViewById(R.id.myicon);
        guan = (RelativeLayout) inflate.findViewById(R.id.isguan);
        isfan = (RelativeLayout) inflate.findViewById(R.id.isfan);

        headset = (TextView) getActivity().findViewById(R.id.headset);

    }

    @Override
    public void stateLogin(User user) {
        flag=1;
        mylogin.setText(user.nick);
        if(user.avatar_url.equals("")){
            icon.setImageResource(R.mipmap.loginicon);
        }else {
            Glide.with(getActivity()).load(user.avatar_url).into(icon);
        }


        myname = user.nick;
        myiconurl = user.avatar_url;
        myphone = user.phone;

    }

    @Override
    public void setNoLogin() {
        mylogin.setText("登录");
        icon.setImageResource(R.mipmap.loginicon);
        flag=0;
    }

    @Override
    protected StateContract.Presenter initPresenter() {
        return new StatePresenter(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.mylogin:
                if(flag==0){
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                }
                break;
            case R.id.isguan:
                AboutActivity.show(getActivity());
                break;
            case R.id.isfan:
                if(flag==0){
                    //提示登录
                    createDialog(R.string.no_login2);
                }else {
                    //进入反馈页面
                    ActionActivity.show(getActivity());
                }
                break;
            case R.id.headset:
                if(liulang2){
                    //SetActivity.show(MainActivity.this);
                    Intent intent=new Intent(getActivity(),MySetActivity.class);
                    intent.putExtra("myicon",myiconurl);
                    intent.putExtra("myname",myname);
                    intent.putExtra("myphone",myphone);
                    startActivity(intent);
                }else {
                    createDialog(R.string.no_login2);
                }
                break;
        }
    }

    public void createDialog(@StringRes int str) {
        selfDialog = new SelfDialog(getActivity());
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
}
