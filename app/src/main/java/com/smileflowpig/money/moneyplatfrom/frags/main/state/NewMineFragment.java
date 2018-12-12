package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.factory.util.LoginStatusUtil;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.BillManagerActivity;
import com.smileflowpig.money.moneyplatfrom.activities.FeedbackActivity;
import com.smileflowpig.money.moneyplatfrom.activities.FlowMeActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MessContextActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MessageActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MyCollectActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MyDatumActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MySetTwoActivity;
import com.smileflowpig.money.moneyplatfrom.activities.NewTaskActivity;
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
import com.smileflowpig.money.moneyplatfrom.util.CropUtils;
import com.smileflowpig.money.moneyplatfrom.util.DialogPermission;
import com.smileflowpig.money.moneyplatfrom.util.FileUtil;
import com.smileflowpig.money.moneyplatfrom.util.PermissionUtil;
import com.smileflowpig.money.moneyplatfrom.util.SharedPreferenceMark;
import com.umeng.analytics.MobclickAgent;

import java.io.File;

/**
 * Created by 小狼 on 2018/10/24.
 */

public class NewMineFragment extends PresenterFragment<StateContract.Presenter>
        implements StateContract.View, View.OnClickListener {

    private View inflate;
    private TextView login;
    private CircleImageView loginicon;
    private LinearLayout collect;
    private LinearLayout record;
    private LinearLayout forget;
    private LinearLayout question;
    private LinearLayout feenback;
    private ImageView set;
    //    private int flag = 0;
    private SelfDialog selfDialog;
    private String myiconurl;
    private String myname;
    private String mysex;
    private String mytype;
    private String myphone;
    private RelativeLayout layout;
    private SharedPreferences sharedPreferences;
    private GetCont getCont;

    @BindView(R.id.fragment_mine_message_img)
    ImageView message_img;
    private LinearLayout mywallet;
    private LinearLayout relationme;
    private LinearLayout detection;

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
        collect = (LinearLayout) inflate.findViewById(R.id.my_collect);
        record = (LinearLayout) inflate.findViewById(R.id.my_record);
        forget = (LinearLayout) inflate.findViewById(R.id.my_forget);
        question = (LinearLayout) inflate.findViewById(R.id.my_question);
        feenback = (LinearLayout) inflate.findViewById(R.id.my_feenback);
        set = (ImageView) inflate.findViewById(R.id.my_set);
        layout = (RelativeLayout) inflate.findViewById(R.id.mine_layout);
        message_img = (ImageView) inflate.findViewById(R.id.fragment_mine_message_img);
        mywallet = (LinearLayout) inflate.findViewById(R.id.mywallet);
        relationme = (LinearLayout) inflate.findViewById(R.id.relationme);
        detection = (LinearLayout) inflate.findViewById(R.id.detection);
        message_img.setOnClickListener(this);
        login.setOnClickListener(this);
        loginicon.setOnClickListener(this);
        mywallet.setOnClickListener(this);
        relationme.setOnClickListener(this);
        inflate.findViewById(R.id.mine_data).setOnClickListener(this);
        inflate.findViewById(R.id.mine_data).setOnClickListener(this);
        //login.setOnClickListener(this);
        collect.setOnClickListener(this);
        record.setOnClickListener(this);
        forget.setOnClickListener(this);
        question.setOnClickListener(this);
        feenback.setOnClickListener(this);
        set.setOnClickListener(this);
        detection.setOnClickListener(this);
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

//
        login.setText(user.nick);
        System.out.println(user.avatar_url + "图片地址");
        if (user.avatar_url.equals("")) {
            loginicon.setImageResource(R.mipmap.loginicon);
        } else {
            Glide.with(getActivity()).load(user.avatar_url).into(loginicon);
        }

        sharedPreferences.edit().putString("myphonecode", user.phone).commit();
        myiconurl = user.avatar_url;
        myphone = user.phone;
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
        if (!LoginStatusUtil.isLogin()) {
            login.setText("注册/登录");
            loginicon.setImageResource(R.mipmap.loginicon);
        }
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
                if (!LoginStatusUtil.isLogin()) {
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), MyDatumActivity.class);
                    intent.putExtra("myphone", myphone);
                    startActivity(intent);
                }

                MobclickAgent.onEvent(getActivity(), "mineData");
                break;
            case R.id.mine_icon:
            case R.id.mine_login:
                if (!LoginStatusUtil.isLogin()) {
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
                if (!LoginStatusUtil.isLogin()) {
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
                if (!LoginStatusUtil.isLogin()) {
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
                if (!LoginStatusUtil.isLogin()) {
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
                if (!LoginStatusUtil.isLogin()) {
                    //提示登录
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    SetActivity.show(getContext());
                }
                MobclickAgent.onEvent(getActivity(), "mineSetting");
                break;
            case R.id.fragment_mine_message_img:
                Intent intent3=new Intent(getActivity(), MessContextActivity.class);
                startActivity(intent3);
                message_img.setImageResource(R.mipmap.icon_message_mine);
                MobclickAgent.onEvent(getActivity(), "mineNews");
                break;
            case R.id.mywallet:
                if (!LoginStatusUtil.isLogin()) {
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), NewTaskActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.relationme:
                Intent intent2 = new Intent(getActivity(), FlowMeActivity.class);
                startActivity(intent2);
                break;
            //检测报告
            case R.id.detection:
                if (getCont != null) {
                    getCont.credit();
                }

                break;

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.getCont = (GetCont) activity;
    }

    public interface GetCont {
        void credit();
    }

    public void SetCont(GetCont getCont) {
        this.getCont = getCont;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();

    }

}
