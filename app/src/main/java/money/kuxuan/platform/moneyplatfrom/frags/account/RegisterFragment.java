package money.kuxuan.platform.moneyplatfrom.frags.account;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.Loading;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.SecondEvent;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.presenter.account.RegisterContract;
import money.kuxuan.platform.factory.presenter.account.RegisterPresenter;
import money.kuxuan.platform.moneyplatfrom.BuildConfig;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.Webview_protocol;

import static money.kuxuan.platform.moneyplatfrom.R.id.btn_submit;

/**
 * 注册的界面
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {
    private AccountTrigger mAccountTrigger;

    @BindView(R.id.code)
    TextView code;

    @BindView(R.id.protocol)
    TextView protocol;

    @BindView(R.id.txt_go_sound)
    TextView goSound;

    @BindView(btn_submit)
    Button btnSubmit;

    @BindView(R.id.agree)
    CheckBox agree;

    @BindView(R.id.edit_password)
    EditText mPassword;

    @BindView(R.id.edit_code)
    EditText mCode;

    @BindView(R.id.edit_phone)
    EditText mPhone;

    @BindView(R.id.loading)
    Loading mLoading;

    private SelfDialog selfDialog;

    TimeCount time;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //拿到我们Activity的引用
        mAccountTrigger = (AccountTrigger) context;

        listener = (onRegistListener) context;
    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }


    @OnClick(R.id.txt_go_login)
    void onShowLoginClick() {
        //让AccountActivity进行界面切换
        mAccountTrigger.triggerView();
        time.cancel();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Webview_protocol.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        goSound.setText(Html.fromHtml("<font color='#999999'>收不到短信？请使用</font><font color=’red'>语音验证码</font>"));
        time = new TimeCount(60000, 1000);

    }

    @Override
    public void registerSuccess(User user) {
        time.cancel();
//
//        getActivity().finish();

//        MainActivity.show(getActivity());
        EventBus.getDefault().post(new SecondEvent(user.phone));
        listener.onRegistSuccess(false);
    }

    @OnClick(R.id.back)
    void onBack() {
        time.cancel();
//        getActivity().finish();
//        MainActivity.show(getActivity());
        listener.onRegistSuccess(true);

        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void codeSuccess() {
        time.start();
        goSound.setVisibility(View.GONE);

    }

    @OnClick(R.id.txt_go_sound)
    void OnSound() {
        String phone = mPhone.getText().toString();
        mPresenter.requestCode(phone, "2");
    }

    @Override
    public void showError(int str) {

        createDialog(str);
        // 停止Loading
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);

        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        btnSubmit.setEnabled(true);

    }

    @OnClick(R.id.code)
    void onCode() {
        String phone = mPhone.getText().toString();

        mPresenter.requestCode(phone, "1");
    }


    @Override
    public void showLoading() {

        super.showLoading();

        // 开始Loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);

        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        btnSubmit.setEnabled(false);

    }

    @OnClick(btn_submit)
    void onSubmit() {
        if (agree.isChecked()) {
            String phone = mPhone.getText().toString();
            String code = mCode.getText().toString();
            String password = mPassword.getText().toString();
            mPresenter.register(phone, password, code, BuildConfig.CHANNLE);
        } else {
            Toast.makeText(getActivity(), "请先同意用户协议", Toast.LENGTH_SHORT).show();
        }

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
            goSound.setVisibility(View.GONE);
            goSound.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            code.setClickable(false);//防止重复点击
            code.setTextColor(getResources().getColor(R.color.textThird));
            code.setText("重新发送" + millisUntilFinished / 1000 + "s");
            goSound.setEnabled(false);
        }
    }

    @Override
    public boolean onBackPressed() {
        listener.onRegistSuccess(true);
        time.cancel();
        return true;
    }

    onRegistListener listener;

    public interface onRegistListener {
        void onRegistSuccess(boolean isGoToLogin);
    }

}
