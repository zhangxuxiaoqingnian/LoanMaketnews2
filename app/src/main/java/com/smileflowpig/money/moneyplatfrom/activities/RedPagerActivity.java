package com.smileflowpig.money.moneyplatfrom.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.smileflowpig.money.factory.bean.PayoverBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RedPagerActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.money_back)
    LinearLayout back;
    @BindView(R.id.red_name)
    TextView name;
    @BindView(R.id.red_phone)
    TextView phone;
    @BindView(R.id.red_updata)
    TextView updata;
    @BindView(R.id.red_edit)
    EditText edit;
    @BindView(R.id.red_money)
    TextView money;
    @BindView(R.id.red_all)
    TextView all;
    @BindView(R.id.red_sum)
    TextView redsum;

    private boolean isshow=false;
    private String count;
    private String real_name;
    private String alipay_id;
    private double summoney;
    private int length;
    private String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();


        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if(!s1.equals("")){
                    double i = Double.parseDouble(s1);
                    if(i>summoney){
                        edit.setText(format);
                        edit.setSelection(length+3);
                    }
                }

            }
        });
    }
    public void initview(){
        back.setOnClickListener(this);
        updata.setOnClickListener(this);
        all.setOnClickListener(this);
        money.setOnClickListener(this);
    }

    public void getdata(){
        Observable<PayoverBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getdeposit().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<PayoverBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onNext(PayoverBean o) {

                real_name = o.rst.real_name;
                if(o.rst.is_bind_zfb==0){
                    updata.setText("去绑定");
                    name.setText("");
                    isshow=false;
                }else {
                    updata.setText("修改");
                    name.setText(real_name);
                    isshow=true;
                }

                if(o.rst.alipay_id!=null){
                    alipay_id = o.rst.alipay_id;
                    if(!alipay_id.equals("")){
                        String s = alipay_id.substring(0, 3) + "****" + alipay_id.substring(7, alipay_id.length());
                        phone.setText(s);
                    }
                }

                summoney = Double.parseDouble(o.rst.money);
                length = o.rst.money.length();
                DecimalFormat df = new DecimalFormat("#.00");
                if(summoney==0){
                    format="0.00";
                }else {
                    format = df.format(summoney);
                }
                redsum.setText("¥"+ format);
                count=o.rst.money;

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
    protected void onResume() {
        super.onResume();

        getdata();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_red_pager;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.money_back:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
                break;
                //修改支付宝或绑定
            case R.id.red_updata:
                if(isshow){
                    //进行修改信息
                    Intent intent2=new Intent(RedPagerActivity.this,PayEditActivity.class);
                    intent2.putExtra("myname",real_name);
                    intent2.putExtra("myphone",alipay_id);
                    startActivity(intent2);
                }else {
                    //进行绑定信息
                    Intent intent2=new Intent(RedPagerActivity.this,PayEditActivity.class);
                    startActivity(intent2);
                }
                break;
                //全部提现
            case R.id.red_all:
                edit.setText(format);
                edit.setSelection(length+3);
                break;
                //申请提现
            case R.id.red_money:
                if(isshow){
                    //进行提现操作
                    String s = edit.getText().toString();
                    if(!TextUtils.isEmpty(s)){
                        double i = Double.parseDouble(s);
                        if(i>=50){
                            getmoney(real_name,alipay_id,s);
                        }else {
                            Toast.makeText(RedPagerActivity.this,"提现金额至少为50元",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RedPagerActivity.this,"请输入提现金额",Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(RedPagerActivity.this,"请先去绑定支付宝",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void getmoney(String name,String phone,String money){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmoney(name, phone, money).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Toast.makeText(RedPagerActivity.this,"申请成功!",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
