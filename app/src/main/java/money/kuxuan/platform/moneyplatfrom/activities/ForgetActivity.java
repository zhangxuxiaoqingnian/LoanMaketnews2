package money.kuxuan.platform.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.presenter.account.ForgetContract;
import money.kuxuan.platform.factory.presenter.account.ForgetPresenter;
import money.kuxuan.platform.moneyplatfrom.R;

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



    TimeCount time;

    public static void show(Context context){
        Intent intent = new Intent(context,ForgetActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_forget;
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
        mPresenter.update(phone,password,code);
    }

    @OnClick(R.id.code)
    void onCode(){
        String phone = mPhone.getText().toString();
        mPresenter.getCode(phone,"1");
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
            code.setText("获取验证码");
            code.setTextColor(getResources().getColor(R.color.textPrimary));
            code.setClickable(true);
            goSound.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            code.setClickable(false);//防止重复点击
            code.setTextColor(getResources().getColor(R.color.textThird));
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
