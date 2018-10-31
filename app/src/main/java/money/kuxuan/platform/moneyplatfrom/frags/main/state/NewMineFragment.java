package money.kuxuan.platform.moneyplatfrom.frags.main.state;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.presenter.state.StateContract;
import money.kuxuan.platform.factory.presenter.state.StatePresenter;
import money.kuxuan.platform.moneyplatfrom.Constant;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.AccountActivity;
import money.kuxuan.platform.moneyplatfrom.activities.FeedbackActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MemoActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MyCollectActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MySetTwoActivity;
import money.kuxuan.platform.moneyplatfrom.activities.RadiersActivity;
import money.kuxuan.platform.moneyplatfrom.activities.RecordActivity;
import money.kuxuan.platform.moneyplatfrom.activities.SetActivity;

/**
 * Created by 小狼 on 2018/10/24.
 */

public class NewMineFragment extends PresenterFragment<StateContract.Presenter>
        implements StateContract.View,View.OnClickListener{

    private View inflate;
    private TextView login;
    private CircleImageView loginicon;
    private RelativeLayout collect;
    private RelativeLayout record;
    private RelativeLayout forget;
    private RelativeLayout question;
    private RelativeLayout feenback;
    private RelativeLayout set;
    private int flag=0;
    private SelfDialog selfDialog;
    private String myiconurl;
    private String myname;
    private String mysex;
    private String mytype;
    private RelativeLayout layout;
    private SharedPreferences sharedPreferences;

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
    public void initview(){
        login = (TextView) inflate.findViewById(R.id.mine_login);
        loginicon = (CircleImageView) inflate.findViewById(R.id.mine_icon);
        collect = (RelativeLayout) inflate.findViewById(R.id.my_collect);
        record = (RelativeLayout) inflate.findViewById(R.id.my_record);
        forget = (RelativeLayout) inflate.findViewById(R.id.my_forget);
        question = (RelativeLayout) inflate.findViewById(R.id.my_question);
        feenback = (RelativeLayout) inflate.findViewById(R.id.my_feenback);
        set = (RelativeLayout) inflate.findViewById(R.id.my_set);
        layout = (RelativeLayout) inflate.findViewById(R.id.mine_layout);
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
        flag=1;
        login.setText(user.nick);
        if(user.avatar_url.equals("")){
            loginicon.setImageResource(R.mipmap.loginicon);
        }else {
            Glide.with(getActivity()).load(user.avatar_url).into(loginicon);
        }
        myiconurl=user.avatar_url;
        myname=user.nick;
        if(user.gender.equals("woman")){
            mysex="女";
        }else {
            mysex="男";
        }
        if(user.identity.equals("1")){
            mytype="上班族";
        }else if(user.identity.equals("2")){
            mytype="个体户";
        }else if(user.identity.equals("3")){
            mytype="企业主";
        }



    }

    @Override
    public void setNoLogin() {
        login.setText("登录");
        loginicon.setImageResource(R.mipmap.loginicon);
        flag=0;
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
        switch (v.getId()){
            //登录
            case R.id.mine_layout:
                if(flag==0){
                    //未登录  跳转到登录页面
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
                }else {
                    //进入更换信息页面
                    Intent intent=new Intent(getActivity(), MySetTwoActivity.class);
                    intent.putExtra("loginicon",myiconurl);
                    intent.putExtra("loginname",myname);
                    intent.putExtra("loginsex",mysex);
                    intent.putExtra("loginindent",mytype);
                    //把现有的信息传过去展示
                    startActivity(intent);
                }
                break;
            //我的收藏
            case R.id.my_collect:
                if(flag==0){
                    //提示登录
                    createDialog(R.string.no_login2);
                }else {
                    Intent intent=new Intent(getActivity(), MyCollectActivity.class);
                    startActivityForResult(intent, Constant.Code.REQUEST_CODEF);
                }
                break;
            //我的足迹
            case R.id.my_record:
                Intent intent1=new Intent(getActivity(),RecordActivity.class);
                startActivityForResult(intent1, Constant.Code.REQUEST_CODE);
                break;
            //还款备忘录
            case R.id.my_forget:
                if(flag==0){
                    //提示登录
                    createDialog(R.string.no_login2);
                }else {
                    Intent intent2=new Intent(getActivity(), MemoActivity.class);
                    startActivity(intent2);
                }

                break;
            //常见问题
            case R.id.my_question:
                RadiersActivity.show(getActivity());
                break;
            //意见反馈
            case R.id.my_feenback:
                if(flag==0){
                    //提示登录
                    createDialog(R.string.no_login2);
                }else {
                    Intent intent=new Intent(getActivity(), FeedbackActivity.class);
                    startActivity(intent);
                }

                break;
            //设置
            case (R.id.my_set):
                if(flag==0){
                    //提示登录
                    createDialog(R.string.no_login2);
                }else {
                    SetActivity.show(getContext());
                }

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();

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
