package com.smileflowpig.money.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.presenter.account.ForgetContract;
import com.smileflowpig.money.factory.presenter.account.ForgetPresenter;
import com.umeng.analytics.MobclickAgent;

public class ForgetActivity extends PresenterActivity<ForgetContract.Presenter>
implements ForgetContract.View{

    @BindView(R.id.txt_go_sound)
    TextView goSound;

    @BindView(R.id.edit_phone)
    EditText mPhone;

    @BindView(R.id.edit_password)
    EditText mPassword;

    @BindView(R.id.edit_code)
    EditText mCode;

    @BindView(R.id.code)
    TextView code;
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    TimeCount time;
    private SelfDialog selfDialog;

    public static void show(Context context){
        Intent intent = new Intent(context, ForgetActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_forget;
    }



    @BindView(R.id.fragment_register_delete_pwdimg)
    ImageView register_check_imge;
    /**
     * 显示隐藏密码
     */
    boolean isCheck;
    @OnClick(R.id.fragment_register_delete_pwdimg)
    void onCheck(){
        if (!isCheck) {
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isCheck = true;
        } else {
            mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isCheck = false;
        }
    }
    @Override
    protected void initWidget() {
        super.initWidget();
        goSound.setText(Html.fromHtml("<font color='#999999'>收不到短信？请使用</font><font color=’red'>语音验证码</font>"));
        time = new TimeCount(60000, 1000);
    }

    @Override
    public void updateSuccess() {
        time.cancel();
        finish();
    }

    @Override
    public void codeSuccess() {
        time.start();
        goSound.setVisibility(View.GONE);
    }

    @Override
    protected ForgetContract.Presenter initPresenter() {
        return new ForgetPresenter(this);
    }

    @OnClick(R.id.btn_submit)
    void submit(){
        String phone = mPhone.getText().toString();
        String password= mPassword.getText().toString();
        String code = mCode.getText().toString();
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)||TextUtils.isEmpty(code)){
            selfDialog = new SelfDialog(ForgetActivity.this);
            selfDialog.setTitle("温馨提示");
            selfDialog.setMessage(R.string.data_account_login_invalid_parameter);
            selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    selfDialog.dismiss();
                }
            });
            selfDialog.show();
        }else {
            if(password.length()<6){
                selfDialog = new SelfDialog(ForgetActivity.this);
                selfDialog.setTitle("温馨提示");
                selfDialog.setMessage(R.string.data_account_register_invalid_parameter_password);
                selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();

            }else {
                mPresenter.update(phone,password,code);

            }
        }

    }

    @OnClick(R.id.code)
    void onCode(){
        String phone = mPhone.getText().toString();
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$");
        Matcher m = p.matcher(phone);
        boolean matches = m.matches();
        if(TextUtils.isEmpty(phone)||matches==false){
            selfDialog = new SelfDialog(ForgetActivity.this);
            selfDialog.setTitle("温馨提示");
            selfDialog.setMessage(R.string.the_cell_phone_number_format_is_wrong);
            selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    selfDialog.dismiss();
                }
            });
            selfDialog.show();
        }else {
            mPresenter.getCode(phone,"1");
        }

    }
    @OnClick(R.id.txt_go_sound)
    void soundCode(){
        String phone = mPhone.getText().toString();
        mPresenter.getCode(phone,"2");
    }

    @OnClick(R.id.back)
    void back(){
        hideSoftKeyboard();
        finish();
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            code.setText("获取");
            code.setClickable(true);
            goSound.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            code.setClickable(false);//防止重复点击
            code.setText("重新发送"+millisUntilFinished / 1000 + "s");
        }
    }
    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
