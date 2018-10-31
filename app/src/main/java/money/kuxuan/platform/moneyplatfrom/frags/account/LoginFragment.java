package money.kuxuan.platform.moneyplatfrom.frags.account;


import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.qiujuer.genius.ui.widget.Loading;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.SecondEvent;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.presenter.account.LoginContract;
import money.kuxuan.platform.factory.presenter.account.LoginPresenter;
import money.kuxuan.platform.moneyplatfrom.Constant;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.ForgetActivity;
import money.kuxuan.platform.moneyplatfrom.helper.SPUtil;

/**
 * 登陆的界面
 */
public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View, TabLayout.OnTabSelectedListener {
    @BindView(R.id.top_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.edit_password)
    EditText mPassword;

    @BindView(R.id.edit_phone)
    EditText mPhone;

    @BindView(R.id.code)
    TextView code;


    @BindView(R.id.code_tv)
    TextView code_tv;

    @BindView(R.id.phone_tv)
    TextView phone_tv;

    @BindView(R.id.loading)
    Loading mLoading;


    private SelfDialog selfDialog;

    int flag = 0;

    String qrcode = "1";

    String smstype = "1";

    TimeCount time;

    @BindView(R.id.forget)
    TextView txtForget;


    @BindView(R.id.register)
    TextView txtRegist;


    @Override
    public void showLoading() {
        super.showLoading();

        // 正在进行时，正在进行注册，界面不可操作
        // 开始Loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);

        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        btn_submit.setEnabled(false);

    }

    private AccountTrigger mAccountTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //拿到我们Activity的引用
        mAccountTrigger = (AccountTrigger) context;
        listener = (onLoginListener) context;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }



    @Override
    public void loginSuccess(User user) {

        // 关闭当前界面
        time.cancel();
        EventBus.getDefault().post(new SecondEvent(user.phone));
//        MainActivity.show(getActivity());
        if (flag == 0) {
            String phone = mPhone.getText().toString();
            String password = mPassword.getText().toString();
            saveUserLoginData(phone, password);
        } else if (flag == 1) {
            String phone = user.getPhone();
            saveUserLoginData(phone, "");
        }
        listener.onLoginSuccess(true);
    }

    onLoginListener listener;

    public interface onLoginListener {
        void onLoginSuccess(boolean isSuccess);
    }


    private void saveUserLoginData(String userName, String password) {
        SPUtil.putAndApply(getActivity(), Constant.UserInfo.USERNAME, userName);
        SPUtil.putAndApply(getActivity(), Constant.UserInfo.PASSWORD, password);

    }

    @Override
    public void codeSuccess() {
        time.start();
        txtForget.setVisibility(View.GONE);

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        setTabLayout();
        code.setVisibility(View.GONE);

        time = new TimeCount(60000, 1000);

    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("账号密码登录"));

        tabLayout.addTab(tabLayout.newTab().setText("手机动态登录"));


        tabLayout.addOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getText().equals("账号密码登录")) {
            code.setVisibility(View.GONE);
            code_tv.setText(R.string.password);
            txtForget.setText("忘记密码？");
            mPassword.setHint(R.string.label_password);
            flag = 0;
        } else {
            code.setVisibility(View.VISIBLE);
            txtForget.setText(Html.fromHtml("<font color='#999999'>收不到短信？请使用</font><font color=’red'>语音验证码</font>"));
            mPassword.setHint(R.string.label_code);
            code_tv.setText(R.string.code);
            flag = 1;
        }
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @OnClick(R.id.code)
    void onCode() {
        String phone = mPhone.getText().toString();
        mPresenter.getCode(phone, qrcode, smstype);
    }


    @OnClick(R.id.forget)
    void forget() {
        if (flag == 1) {
            String phone = mPhone.getText().toString();
            mPresenter.getCode(phone, "1", "2");
        } else {
            ForgetActivity.show(getContext());
        }
    }

    @OnClick(R.id.register)
    void regist() {
        mAccountTrigger.triggerView();
        time.cancel();
    }

    @OnClick(R.id.back)
    void onBack() {


        time.cancel();
        getActivity().finish();
//        MainActivity.show(getActivity());
        listener.onLoginSuccess(false);

        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


    @OnClick(R.id.btn_submit)
    void onSubmit() {
        if (flag == 0) {
            String phone = mPhone.getText().toString();
            String password = mPassword.getText().toString();
            // 调用P层进行登录
            mPresenter.login(phone, password);
        } else {
            String phone = mPhone.getText().toString();
            String code = mPassword.getText().toString();
            // 调用P层进行登录
            mPresenter.loginByCode(phone, code);
        }

    }

    @Override
    public void showError(@StringRes int str) {
        createDialog(str);
        // 停止Loading
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);

        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        btn_submit.setEnabled(true);
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


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            code.setText("获取验证码");
            code.setTextColor(getResources().getColor(R.color.textPrimary));
            code.setClickable(true);
            txtForget.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            code.setClickable(false);//防止重复点击
            code.setTextColor(getResources().getColor(R.color.textThird));
            code.setText("重新发送" + millisUntilFinished / 1000 + "s");
        }
    }

    @Override
    public boolean onBackPressed() {
//        MainActivity.show(getActivity());
        listener.onLoginSuccess(false);
        time.cancel();
        return super.onBackPressed();
    }
}
