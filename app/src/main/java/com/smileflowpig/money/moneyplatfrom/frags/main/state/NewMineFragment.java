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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.BillManagerActivity;
import com.smileflowpig.money.moneyplatfrom.activities.FeedbackActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MessageActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MyCollectActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MySetTwoActivity;
import com.smileflowpig.money.moneyplatfrom.activities.RadiersActivity;
import com.smileflowpig.money.moneyplatfrom.activities.RecordActivity;
import com.smileflowpig.money.moneyplatfrom.activities.SetActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.presenter.state.StateContract;
import com.smileflowpig.money.factory.presenter.state.StatePresenter;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 小狼 on 2018/10/24.
 */

public class NewMineFragment extends PresenterFragment<StateContract.Presenter>
        implements StateContract.View, View.OnClickListener {

    private View inflate;
    private TextView login;
    private CircleImageView loginicon;
    private RelativeLayout collect;
    private RelativeLayout record;
    private RelativeLayout forget;
    private RelativeLayout question;
    private RelativeLayout feenback;
    private RelativeLayout set;
    private int flag = 0;
    private SelfDialog selfDialog;
    private String myiconurl;
    private String myname;
    private String mysex;
    private String mytype;
    private RelativeLayout layout;
    private SharedPreferences sharedPreferences;

    @BindView(R.id.fragment_mine_message_img)
    ImageView message_img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.newmine_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        sharedPreferences = getActivity().getSharedPreferences("Logintype", Context.MODE_PRIVATE);

    }

    public void initview() {
        login = (TextView) inflate.findViewById(R.id.mine_login);
        loginicon = (CircleImageView) inflate.findViewById(R.id.mine_icon);
        collect = (RelativeLayout) inflate.findViewById(R.id.my_collect);
        record = (RelativeLayout) inflate.findViewById(R.id.my_record);
        forget = (RelativeLayout) inflate.findViewById(R.id.my_forget);
        question = (RelativeLayout) inflate.findViewById(R.id.my_question);
        feenback = (RelativeLayout) inflate.findViewById(R.id.my_feenback);
        set = (RelativeLayout) inflate.findViewById(R.id.my_set);
        layout = (RelativeLayout) inflate.findViewById(R.id.mine_layout);
        message_img = (ImageView) inflate.findViewById(R.id.fragment_mine_message_img);
        message_img.setOnClickListener(this);
        login.setOnClickListener(this);
        loginicon.setOnClickListener(this);
        inflate.findViewById(R.id.mine_data).setOnClickListener(this);
        inflate.findViewById(R.id.mine_data).setOnClickListener(this);
        //login.setOnClickListener(this);
        collect.setOnClickListener(this);
        record.setOnClickListener(this);
        forget.setOnClickListener(this);
        question.setOnClickListener(this);
        feenback.setOnClickListener(this);
        set.setOnClickListener(this);
        //loginicon.setOnClickListener(this);
        layout.setOnClickListener(this);

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
    public void stateLogin(User user) {

//        sharedPreferences.edit().putString("sessionid",user.getPHPSESSID()).commit();
        flag = 1;
        login.setText(user.nick);
        if (user.avatar_url.equals("")) {
            loginicon.setImageResource(R.mipmap.loginicon);
        } else {
            Glide.with(getActivity()).load(user.avatar_url).into(loginicon);
        }
        myiconurl = user.avatar_url;
        myname = user.nick;
        if (user.gender.equals("woman")) {
            mysex = "女";
        } else {
            mysex = "男";
        }
        if (user.identity.equals("1")) {
            mytype = "上班族";
        } else if (user.identity.equals("2")) {
            mytype = "个体户";
        } else if (user.identity.equals("3")) {
            mytype = "企业主";
        }


    }


    @Override
    public void setNoLogin() {
        login.setText("注册/登录");
        loginicon.setImageResource(R.mipmap.loginicon);
        flag = 0;
    }

    @Override
    protected StateContract.Presenter initPresenter() {

        return new StatePresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.newmine_layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.mine_data:
                if (flag == 0) {
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    //进入更换信息页面
                    Intent intent = new Intent(getActivity(), MySetTwoActivity.class);
                    intent.putExtra("loginicon", myiconurl);
                    intent.putExtra("loginname", myname);
                    intent.putExtra("loginsex", mysex);
                    intent.putExtra("loginindent", mytype);
                    //把现有的信息传过去展示
                    startActivity(intent);
                }

                MobclickAgent.onEvent(getActivity(), "mineData");
                break;
            case R.id.mine_icon:
            case R.id.mine_login:
                if (flag == 0) {
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    //进入更换信息页面
                    Intent intent = new Intent(getActivity(), MySetTwoActivity.class);
                    intent.putExtra("loginicon", myiconurl);
                    intent.putExtra("loginname", myname);
                    intent.putExtra("loginsex", mysex);
                    intent.putExtra("loginindent", mytype);
                    //把现有的信息传过去展示
                    startActivity(intent);
                }

                break;
            //我的收藏
            case R.id.my_collect:
                if (flag == 0) {
                    //提示登录
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODEF);
                }
                MobclickAgent.onEvent(getActivity(), "mineCollectInto");
                break;
            //我的足迹
            case R.id.my_record:
                Intent intent1 = new Intent(getActivity(), RecordActivity.class);
                startActivityForResult(intent1, Constant.Code.REQUEST_CODE);
                MobclickAgent.onEvent(getActivity(), "mineBrowseInto");
                break;
            //还款备忘录
            case R.id.my_forget:
                if (flag == 0) {
                    //提示登录
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    Intent intent2 = new Intent(getActivity(), BillManagerActivity.class);
                    startActivity(intent2);
                }
                MobclickAgent.onEvent(getActivity(), "mineMemoInto");

                break;
            //常见问题
            case R.id.my_question:
                RadiersActivity.show(getActivity());
                MobclickAgent.onEvent(getActivity(), "mineQuestionInto");
                break;
            //意见反馈
            case R.id.my_feenback:
                if (flag == 0) {
                    //提示登录
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                    startActivity(intent);
                }
                MobclickAgent.onEvent(getActivity(), "mineFeedbackInto");
                break;
            //设置
            case (R.id.my_set):
                if (flag == 0) {
                    //提示登录
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    SetActivity.show(getContext());
                }
                MobclickAgent.onEvent(getActivity(), "mineSetting");
                break;
            case R.id.fragment_mine_message_img:
                MessageActivity.show(getActivity(),2);
                message_img.setImageResource(R.mipmap.icon_message_mine);
                MobclickAgent.onEvent(getActivity(), "mineNews");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();

    }

}
