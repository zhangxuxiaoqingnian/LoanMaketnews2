package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.OverCodeBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.account.ForgetContract;
import com.smileflowpig.money.factory.presenter.account.ForgetPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PayEditActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.pay_name)
    EditText name;
    @BindView(R.id.pay_baby)
    EditText baby;
    @BindView(R.id.pay_phone)
    TextView phone;
    @BindView(R.id.pay_code)
    EditText code;
    @BindView(R.id.pay_over)
    TextView over;
    @BindView(R.id.pay_phonecode)
    TextView phonecode;
    private CountDownTimer countDownTimer;
    private String payphone;
    private String myphone;
    private String myphonecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back.setOnClickListener(this);
        over.setOnClickListener(this);
        phonecode.setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("Logintype", Context.MODE_PRIVATE);
        myphonecode = sharedPreferences.getString("myphonecode", null);
        phone.setText(myphonecode);

        String myname = getIntent().getStringExtra("myname");
        myphone = getIntent().getStringExtra("myphone");
        name.setText(myname);
        baby.setText(myphone);
        initview();

    }
    public void initview(){
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changedata();
            }
        });
        baby.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                changedata();
            }
        });

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changedata();
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_pay_edit;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
                break;
                //提交按钮
            case R.id.pay_over:
                String s1 = name.getText().toString();
                String s2 = baby.getText().toString();
                String s3 = code.getText().toString();
                if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2)&&!TextUtils.isEmpty(s3)){
                    getdata(s2,myphonecode,s3,s1);
                }
                break;
                //获取验证码
            case R.id.pay_phonecode:
                //先判断手机号是否正确
                String s = phone.getText().toString();
                if(isPhoneNumber(s)){
                    //获取验证码
                    getphonecode();
                    phonecode.setClickable(false);
                    countDownTimer = new CountDownTimer(60*1000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            phonecode.setText("("+millisUntilFinished / 1000+"秒)");
                            if(millisUntilFinished / 1000==0){
                                phonecode.setText("获取验证码");
                                phonecode.setClickable(true);
                            }
                        }
                        @Override
                        public void onFinish() {

                        }
                    };
                    countDownTimer.start();
                }else {
                    Toast.makeText(PayEditActivity.this,"手机号码有误",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void getdata(String alipay,String phone,String code,String name){

        Observable<OverCodeBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getpaytext(alipay, phone, code, name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<OverCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OverCodeBean o) {
                if(o.errno.equals("10023")){
                    Toast.makeText(PayEditActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                }else if(o.errno.equals("0")){
                    finish();
                }else if(o.errno.equals("10025")){
                    Toast.makeText(PayEditActivity.this,"账号格式错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getphonecode(){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getpay().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Toast.makeText(PayEditActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer!=null){
            countDownTimer.onFinish();
        }

    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    private void changedata() {

        if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(baby.getText().toString())|| TextUtils.isEmpty(phone.getText().toString())|| TextUtils.isEmpty(code.getText().toString())) {
            //不可点击确认
            over.setClickable(false);
            over.setBackground(getResources().getDrawable(R.drawable.buttonnoshap));
        } else {
            over.setClickable(true);
            over.setBackground(getResources().getDrawable(R.drawable.buttonshap));
        }

    }

    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }

}
